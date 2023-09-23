package backend;


import assembly.*;
import assembly.operand.*;
import IR.Value.*;
import IR.Type.*;
import IR.*;
import IR.Instructions.*;
import IR.Type.Void;
import java.util.HashMap;
import utils.*;
import assembly.inst.*;


public class Instselector implements Elements, IRVisitor{
    ASMModule module;
    ASMFunction curFunc;
    ASMBlock curBlock;
    int blockCnt = 0;

    HashMap<Basicblock, ASMBlock> blockMap = new HashMap<>();

    public Instselector(ASMModule module) {
        this.module = module;
    }

    

    public void visit(Program node) {
      for (var globalVar : node.globalVarList) {
        globalVar.asmreg = new GlobalValue(globalVar);
        module.globalValues.add((GlobalValue) globalVar.asmreg);
      }
      // add global strings
      for (var str : node.stringConst.values()) {
        str.asmreg = new GlobalString(".str." + String.valueOf(str.id), str.val);
        module.globalStrings.add((GlobalString)str.asmreg);
      }
      for (var func : node.funcList) {
        curFunc = new ASMFunction(func.name);
        module.functions.add(curFunc);
        func.accept(this);
      }
    }

    public void visit(Functionblock node) {
        blockMap.clear();
        VirtualReg.cnt = 0;
        int maxArgCnt = 0;
        for (Basicblock blk : node.blocks) {
            blockMap.put(blk, new ASMBlock(".L" + blockCnt++,blk.loopDepth));
            for (Ins inst : blk.insts)
                if (inst instanceof Call)
                    maxArgCnt = Math.max(maxArgCnt, ((Call) inst).args.size());
        }
        curFunc.paramUsed = (maxArgCnt > 8 ? maxArgCnt - 8 : 0) << 2;

        for (int i = 0; i < node.params.size(); ++i)
            if (i < 8)
                node.params.get(i).asmreg = new VirtualReg(node.params.get(i).type.size);//前8个参数放在a0-a7中，取出来
            else
                node.params.get(i).asmreg = new VirtualReg(4,i);//后面的参数放在虚拟寄存器中


        for (int i = 0; i < node.blocks.size(); ++i) {
            curBlock = blockMap.get(node.blocks.get(i));
            if (i == 0)//如果是第一个块
                storeReg(4, PhysicsReg.regMap.get("ra"), PhysicsReg.regMap.get("sp"), curFunc.paramUsed);
                //往sp+curFunc.paramUsed处存入ra
            node.blocks.get(i).accept(this);
            curFunc.addBlock(curBlock);
        }
        curFunc.entryBlock = curFunc.blocks.get(0);
    curFunc.exitBlock = curFunc.blocks.get(curFunc.blocks.size() - 1);
        for (int i = 0; i < node.params.size() && i < 8; ++i)
            curFunc.entryBlock.insts.addFirst(new MvInst(node.params.get(i).asmreg, PhysicsReg.get("a" + i)));
        curFunc.virtualRegCnt = VirtualReg.cnt;

        curFunc.totalStack = curFunc.paramUsed + curFunc.allocaUsed + curFunc.virtualRegCnt * 4;
        //计算整个函数执行过程中，栈帧占用的空间
        //给别的函数传参需要开的空间+自己的alloca需要的空间+总虚拟寄存器数量*4

        ASMBlock entryBlock = curFunc.blocks.get(0);
        ASMBlock exitBlock = curFunc.blocks.get(curFunc.blocks.size() - 1);
        for (var block : curFunc.blocks) {
            block.insts.addAll(block.phiConvert);
            block.insts.addAll(block.jumpOrBr);
          }
        entryBlock.insts.addFirst(new BinaryInst("add", PhysicsReg.regMap.get("sp"), PhysicsReg.regMap.get("sp"),new VirtualImm(-curFunc.totalStack)));//sp往上减，开辟栈帧
        exitBlock.insts.add(new BinaryInst("add", PhysicsReg.regMap.get("sp"), PhysicsReg.regMap.get("sp"),new VirtualImm(curFunc.totalStack)));//sp往下加，释放栈帧
        exitBlock.insts.add(new RetInst());
        
    }

    public void visit(Basicblock node) {
        for (var inst : node.insts){
              inst.accept(this);
          }
        node.terminalInst.accept(this);
    }

    public void visit(Alloca node) {
        curBlock.addInst(new BinaryInst("add", getReg(node.allocaReg), PhysicsReg.regMap.get("sp"),
            new VirtualImm(curFunc.paramUsed + curFunc.allocaUsed)));
            //把sp偏移以后的地址存入allocaReg
        curFunc.allocaUsed += 4;
    }

    public void visit(Br node) {
        curBlock.addInst(new BeqzInst(getReg(node.cond), blockMap.get(node.elseBlock)));
        curBlock.addInst(new JumpInst(blockMap.get(node.thenBlock)));
    }

    public void visit(Binary node) {
        curBlock.addInst(new BinaryInst(node.op, getReg(node.res), getReg(node.lhs), getReg(node.rhs)));
        //lhs和rhs相作用，结果存入res
    }

    public void visit(Call node) {
        for (int i = 0; i < node.args.size(); ++i) {
            Valu arg = node.args.get(i);
            if (i < 8)
                curBlock.addInst(new MvInst(PhysicsReg.regMap.get("a" + i), getReg(arg)));//前8个参数放在a0-a7中
            else
                storeReg(arg.type.size, getReg(arg), PhysicsReg.regMap.get("sp"), i - 8 << 2);
                //往PhysicsReg.regMap.get("sp")+((i - 8) << 2)处存入参数arg 大小为size
        }
        curBlock.addInst(new CallInst(node.funcName));
        if (node.call != null)
            curBlock.addInst(new MvInst(getReg(node.call), PhysicsReg.regMap.get("a0")));
            //把a0的值存入callReg
    }

    public void visit(Getelementptr node) {
        if (node.to_type == irBoolType || node.to_type == irCharType) {
            curBlock.addInst(new BinaryInst("add", getReg(node.res), getReg(node.ptr), getReg(node.indexList.get(0))));
            //如果指针指向的类型是1字节 node.ptr加上第一个参数以后存入node.res
        } else {
            Reg idx = node.to_type instanceof Classtype ? getReg(node.indexList.get(1)) : getReg(node.indexList.get(0));

            VirtualReg tmp = new VirtualReg(4);
            curBlock.addInst(new UnaryInst("slli", tmp, idx, new Imm(2)));
            //把idx左移2位，存入tmp
            curBlock.addInst(new BinaryInst("add", getReg(node.res), getReg(node.ptr), tmp));
            //把ptr加上tmp以后存入res
        }
    }

    public void visit(Icmp node) {
        VirtualReg tmp = new VirtualReg(4);
        switch (node.op) {
            case "eq" :
                curBlock.addInst(new BinaryInst("sub", tmp, getReg(node.lhs), getReg(node.rhs)));
                curBlock.addInst(new UnaryInst("seqz", getReg(node.cmp), tmp));
                break;
            
            case "ne" :
                curBlock.addInst(new BinaryInst("sub", tmp, getReg(node.lhs), getReg(node.rhs)));
                curBlock.addInst(new UnaryInst("snez", getReg(node.cmp), tmp));
                break;
            
            case "sgt" :
                    curBlock.addInst(new BinaryInst("slt", getReg(node.cmp), getReg(node.rhs), getReg(node.lhs)));
                    break;
            case "sge" :
                curBlock.addInst(new BinaryInst("slt", tmp, getReg(node.lhs), getReg(node.rhs)));
                curBlock.addInst(new UnaryInst("xori", getReg(node.cmp), tmp, new Imm(1)));
                break;
            
            case "slt" :
                    curBlock.addInst(new BinaryInst("slt", getReg(node.cmp), getReg(node.lhs), getReg(node.rhs)));
                    break;
            case "sle" :
                curBlock.addInst(new BinaryInst("slt", tmp, getReg(node.rhs), getReg(node.lhs)));
                curBlock.addInst(new UnaryInst("xori", getReg(node.cmp), tmp, new Imm(1)));
                break;
            
        }
    }

    public void visit(Jump node) {
        curBlock.addInst(new JumpInst(blockMap.get(node.toBlock)));
    }

    public void visit(Load node) {
        loadReg(node.type.size, getReg(node.destReg), getReg(node.storeptr), 0);
    }

    public void visit(Ret node) {
      
        if (!(node.val.type instanceof Void))
            curBlock.addInst(new MvInst(PhysicsReg.regMap.get("a0"), getReg(node.val)));//把val的值存入a0
        loadReg(4, PhysicsReg.regMap.get("ra"), PhysicsReg.regMap.get("sp"), curFunc.paramUsed);
        // 寄存器分配完再加 ret
    }

    public void visit(Store node) {
        //往rs1的地址处存入rs2
        storeReg(node.val.type.size, getReg(node.val), getReg(node.destAddr), 0);
    }
    public void visit(Phi node){
        VirtualReg tmp = new VirtualReg(node.dest.type.size);
        curBlock.addInst(new MvInst(getReg(node.dest), tmp));
        for (int i = 0; i < node.values.size(); ++i) {
        Valu val = node.values.get(i);
        if (val instanceof Const && !(val instanceof Stringconst)){
            Const constVal=(Const) val;
            blockMap.get(node.blocks.get(i)).phiConvert.add(new LiInst(tmp, new VirtualImm(constVal)));
        }
            
        else
            blockMap.get(node.blocks.get(i)).phiConvert.add(new MvInst(tmp, getReg(node.values.get(i))));
        }
    }
    Reg getReg(Valu entity) {
        if (entity.asmreg == null) {
            if (entity instanceof Register) {
                entity.asmreg = new VirtualReg(entity.type.size);
            } else if (entity instanceof Const) {
                entity.asmreg = new VirtualImm((Const) entity);
            }
        }
        return entity.asmreg;
    }
    void storeReg(int size, Reg value, Reg dest, int offset) {//往dest+offset处存入value
        if (offset < 1 << 11)
            curBlock.addInst(new StoreInst(size, dest, value, new Imm(offset)));
        else {
            VirtualReg tmp = new VirtualReg(4);//创建一个虚拟寄存器
            curBlock.addInst(new BinaryInst("add", tmp, dest, new VirtualImm(offset)));
            curBlock.addInst(new StoreInst(size, tmp, value));
        }
    }
    void loadReg(int size, Reg dest, Reg src, int offset) {
        if (offset < 1 << 11)
            curBlock.addInst(new LoadInst(size, dest, src, new Imm(offset)));
        else {
            VirtualReg tmp = new VirtualReg(4);
            curBlock.addInst(new BinaryInst("add", tmp, src, new VirtualImm(offset)));
            curBlock.addInst(new LoadInst(size, dest, tmp));
        }
    }
}