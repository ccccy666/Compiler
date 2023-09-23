package IR;

import ast.*;
import ast.stmt.*;
import ast.expr.*;
import IR.Instructions.*;
import IR.Value.*;
import IR.Type.*;
import IR.Type.Void;
import utils.*;

import java.util.ArrayList;
import java.util.HashMap;


public class IrBuilder implements ASTvisitor,Elements{
    Program program=null;
    HashMap<String, Classtype> Classmap = new HashMap<>();
    Functionblock currentFunction = null;
    Classtype currentClass = null;
    Basicblock currentBlock = null;
    Globalscope Globalscope=null;
    Scope currentScope=null;
    
    
    int id=-1;
    public IrBuilder(Program program, Globalscope Globalscope) {
        this.program = program;
        this.Globalscope = Globalscope;
        this.currentScope = Globalscope;
    }
    public void visit(ProgramNode node){
        for(var ele:node.defList){
            if(ele instanceof ClassdefNode){
                ClassdefNode cl=(ClassdefNode)ele;
                Classmap.put(cl.name,new Classtype(cl.name,cl.varMember.size()*4));
            }
        }// 4 bytes each member
        for(var ele:node.defList){
            if(ele instanceof ClassdefNode){
                ele.accept(this);
            }
        }
        for(var ele:node.defList){
            if(ele instanceof VariabledefNode){
                ele.accept(this);
            }
        }
        for(var ele:node.defList){
            if(ele instanceof FuncdefNode){
                ele.accept(this);
            }
        }
        
        if (program.initBlock.insts.size() != 0) {
            program.initFunc.finish();//initFunc
            program.funcList.addFirst(program.initFunc);//函数列表的第一个

            Basicblock mainEntry = program.mainFunc.blocks.get(0);//
            mainEntry.insts.addFirst(new Call(mainEntry, irVoidType, "init"));
            //main函数的第一个基本块中添加调用指令
        } else {
            program.initFunc = null;
        }
        //program.mainFunc.finish();
    };

    // public void visit(NullNode node);
    public void visit(FuncdefNode node){

        Basicblock.blockCnt = 0;
        node.returnType.irType = change(true,node.returnType);
        

        currentScope = new Scope(currentScope, node.returnType);
        
        currentFunction = new Functionblock(currentClass == null ?  node.name :currentClass.name + "." + node.name, node.returnType.irType);
        program.funcList.add(currentFunction);
        
        
        
        currentBlock =new Basicblock(currentFunction, "entry", 0);
        currentFunction.entryBlock = currentBlock;
        currentFunction.blocks.add(currentBlock);
        
        if (currentClass != null) {  
            Ptr classPtrType = new Ptr(currentClass);
            Register thisVal = new Register("this", classPtrType);
            currentFunction.params.add(thisVal);//thisVal

            // store this pointer
            Register thisAddr = new Register("this.addr", new Ptr(classPtrType));//创建指针指向this
            currentBlock.addInst(new Alloca(currentBlock, classPtrType, thisAddr, 0));//第零个参数是this
            currentBlock.addInst(new Store(currentBlock, thisVal, thisAddr));//将thisVal存入thisAddr
            currentScope.addIRVar("this", thisAddr);//thisaddr就是storeptr
            /////////fuck
        }

        if (node.params != null){
            node.params.accept(this);

        }
        
        currentFunction.exitBlock = new Basicblock(currentFunction, "return", 0);
        currentBlock.terminalInst = new Jump(currentBlock, currentFunction.exitBlock);
        if (node.returnType.equals(new TypeNode("void"))) {
            currentFunction.exitBlock.terminalInst = new Ret(currentFunction.exitBlock, irVoidConst);
        } else {
            Basetype retValType = node.returnType.irType ;

            currentFunction.retAddr = new Register("retval", new Ptr(retValType));//创建一个返回值的地址
            currentFunction.exitBlock.addInst(new Alloca(currentBlock, retValType, currentFunction.retAddr));
            
            Register  retVal = new Register("ret", retValType);//创建一个返回值
            currentFunction.exitBlock.addInst(new Load(currentBlock, retVal, currentFunction.retAddr));
            // if (node.returnType.irType == irCondType) {

            //     Register tmp = new Register("", irCondType);
            //     currentFunction.exitBlock.addInst(new Trunc(currentBlock, tmp, retVal, irCondType));
            //     currentFunction.exitBlock.terminalInst = new Ret(currentFunction.exitBlock, tmp);
            // } else {
                currentFunction.exitBlock.terminalInst = new Ret(currentFunction.exitBlock, retVal);
            //}
        }

        if ((currentClass == null ?  node.name :currentClass.name + "." + node.name).equals("main"))
            program.mainFunc = currentFunction;
        for(var ele:node.stmts){
            ele.accept(this);
        }
        
        currentScope = currentScope.parentScope;
        //if (!(currentClass == null ?  node.name :currentClass.name + "." + node.name).equals("main")) 
                    currentFunction.finish();
        node.irFunc = currentFunction;
        currentFunction = null;
        currentBlock = null;
    };
    public void visit(TypeNode node){
        node.irType = change(false,node );
    };
    public void visit(ParametersNode node){
        id=0;
        while (id < node.units.size()) {//store in to re,in为参数列表，re是函数内alloca的寄存器
            (node.units.get(id)).accept(this);
            Register in = new Register("", (node.units.get(id)).type.irType);
            currentFunction.params.add(in);
            int para=id + (currentClass == null ? 0 : 1);
            Register re=currentScope.getIRVarPtr((node.units.get(id)).varName);
            currentBlock.addInst(new Store(currentBlock, in, re,para));
            id++;
          }
          id = -1;
    };
    public void visit(ClassdefNode node){
        
        currentScope = new Scope(currentScope, node);
        currentClass = Classmap.get(node.name); 
        program.structTypeList.add(currentClass);

        for(var ele:node.varDefList){
            ele.accept(this);
        }
        if (node.classBuild != null) {
            currentClass.hasBuild = true;
            node.classBuild.accept(this);
        }
        
        for(var ele:node.funcDefList){
            ele.accept(this);
        }
        currentScope = currentScope.parentScope;
        currentClass = null;
    };
    public void visit(ConstructordefNode node){
        FuncdefNode fd=node.transToFuncDef();
        fd.accept(this);
    };
    public void visit(VariabledefNode node){
        for(var ele:node.units){
            ele.accept(this);
        }
    };
    public void visit(Variable node){
        boolean fl1=id == -1,fl2=currentClass == null;
        int num;
        if(fl1)num=-1;
        else {
            num=id;
            if(!fl2)num+=1;
        }
        node.type.accept(this);
        
        if (currentFunction != null) {  // check if node's in a function first
            Register definingPtr = new Register(node.varName + ".addr", new Ptr(node.type.irType));
            //定义一个指针
            currentScope.addIRVar(node.varName, definingPtr);  // 建立变量名和具体指针的映射

            currentBlock.addInst(new Alloca(currentBlock, node.type.irType, definingPtr,num)); 
                //如果是-1说明不是函数参数定义，赋值为-1
          //如果不是-1说明是函数参数定义，还要考虑如果是类的成员函数，那么参数的下标要加1
            if (node.initVal != null) {
                node.initVal.accept(this);
                storein(definingPtr, node.initVal);
            } else if (id == -1 && node.type.Nbasic()) {//没有初始化，而且是引用类型
                currentBlock.addInst(new Store(currentBlock, new Nullconst(node.type.irType), definingPtr));
            }
        } else if (currentClass != null) {
            currentClass.addMember(node.varName, node.type.irType);
        
        } else {//全局变量
            Globalvar global = new Globalvar(node.varName, node.type.irType);
            // if(node.initVal instanceof BasicexprNode)System.out.print(node.initVal.str);
            boolean fl=(node.initVal != null && node.initVal instanceof BasicexprNode && !(node.initVal instanceof RecurexprNode)&& !node.initVal.type.equals(new TypeNode("string")) && !node.initVal.str.equals("this"));
            
            if (fl) {
                
                node.initVal.accept(this);
                
                global.initVal = loadvalue(node.initVal) ;
                // System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaa\n");instanceof Cond? new Boolconst(((Cond) node.initVal.value).val): node.initVal.value
                
                Globalscope.addIRVar(node.varName, global);
            } else {//进去的不是一个原子式
                
                global.initVal = node.type.irType.defaultValue();
                Globalscope.addIRVar(node.varName, global);
                if (node.initVal != null) {//有初始化
                    global.init = true;//需要调用init函数
                    Functionblock tmpFunc = currentFunction;
                    Basicblock tmpBlock = currentBlock;
                    currentFunction = program.initFunc;
                    currentBlock = program.initBlock;
                    node.initVal.accept(this);
                    storein(global, node.initVal);
                    currentFunction = tmpFunc;
                    currentBlock = tmpBlock;
                }
            }
            program.globalVarList.add(global);
        }
    };

    public void visit(BasicexprNode node){
        if (node.type.equals(new TypeNode("int"))) {
            node.value = new Intconst(Integer.parseInt(node.str));
        } else if (node.type.equals(new TypeNode("bool"))) {
            node.value = new Boolconst(node.str.equals("true"));
        } else if (node.str.equals("this")){ 
            node.storePtr = currentScope.getIRVarPtr("this");
            //System.out.print("ssssssssssssssssss\n");
        } else if (node.type.equals(new TypeNode("null"))) {
            node.value = new Nullconst();
        } else if (node.type.equals(new TypeNode("string"))) {
            //System.out.print(node.str);
            Stringconst strPtr = program.addStringConst(node.str.substring(1, node.str.length() - 1)); 
            node.value = strPtr;
            
        }
    };
    public void visit(RecurexprNode node){
    
    // if(node.funcDef!=null)return;
        node.storePtr = currentScope.getIRVarPtr(node.str);
        if (node.storePtr == null) {  // member or function
            Register thisAddr = (Register) currentScope.getIRVarPtr("this");
            if (thisAddr != null) {  // a member
                Basetype Ptrobj =  ((Ptr) thisAddr.type).pointToType();
                Basetype Realobj = ((Ptr) Ptrobj).pointToType();
                
                if (((Classtype) Realobj).hasMember(node.str)) {
                    Register thisVal = new Register("this", Ptrobj);
                    currentBlock.addInst(new Load(currentBlock, thisVal, thisAddr));
                    
                    var typ=((Classtype) Realobj).getMemberType(node.str);
                    Ptr pt=new Ptr(typ);
                    node.storePtr = new Register("this." + node.str,pt);
                    Classtype cl=(Classtype) Realobj;
                    int num=cl.memberOffset.get(node.str);
                    currentBlock.addInst(new Getelementptr(currentBlock, thisVal, node.storePtr, Intconst0,new Intconst(num)));
                }
            }
        }
    };
    public void visit(BinaryexprNode node){
        Register dest = null;
        Basetype operandType = null;
        String op = null;
        if (!node.op.equals("&&") && !node.op.equals("||")){
            node.lhs.accept(this);
            node.rhs.accept(this);
        }
        else {//短路求值
            node.lhs.accept(this);
            Register temp = new Register("binary_short", new Ptr(irBoolType));//为寄存器 temp 分配内存空间
            currentBlock.addInst(new Alloca(currentBlock, irBoolType, temp));
            Basicblock rhsBlock = new Basicblock(currentFunction, "rhsBlock", currentBlock.loopDepth);
            Basicblock trueBlock = new Basicblock(currentFunction, "trueBlock", currentBlock.loopDepth);
            Basicblock falseBlock = new Basicblock(currentFunction, "falseBlock", currentBlock.loopDepth);
            Basicblock nextBlock = new Basicblock(currentFunction, "short.end", currentBlock.loopDepth);
            nextBlock.terminalInst = currentBlock.terminalInst;
            currentBlock.terminalInst = node.op.equals("&&") ? new Br(currentBlock, condition(node.lhs), rhsBlock, falseBlock) : new Br(currentBlock, condition(node.lhs), trueBlock, rhsBlock);
            currentBlock.isFinished = true;
            currentBlock = currentFunction.appendBlock(rhsBlock);
            
//更新基本块的终止指令
            node.rhs.accept(this);
            currentBlock.terminalInst = new Br(currentBlock, condition(node.rhs), trueBlock, falseBlock);
            currentBlock.isFinished = true;

            currentBlock = currentFunction.appendBlock(trueBlock);
            currentBlock.addInst(new Store(currentBlock, irBoolTrueConst, temp));
            currentBlock.terminalInst = new Jump(currentBlock, nextBlock);
            currentBlock.isFinished = true;
            currentBlock = currentFunction.appendBlock(falseBlock);
            currentBlock.addInst(new Store(currentBlock, irBoolFalseConst, temp));
            currentBlock.terminalInst = new Jump(currentBlock, nextBlock);
            currentBlock.isFinished = true;

            currentBlock = currentFunction.appendBlock(nextBlock);
            Register result = new Register("cond", irBoolType);
            currentBlock.addInst(new Load(currentBlock, result, temp));
            node.value = result;
            // currentBlock.addInst(new Trunc(currentBlock, (Register) node.value, result, irCondType));
            return;
            
             //Trunc 指令截断为条件类型，并将结果存到 node.value 
           
        }
        
        if ( !node.lhs.type.equals(new TypeNode("string")) && !node.rhs.type.equals(new TypeNode("string"))) {
            Valu lhs = loadvalue(node.lhs), rhs = loadvalue(node.rhs);
            switch (node.op) {
                case "+":
                    if (lhs instanceof Const && rhs instanceof Const)
                    node.value = new Intconst(((Intconst) lhs).val + ((Intconst) rhs).val);
                    op = "add"; 
                    break;
                case "-":
                    if (lhs instanceof Const && rhs instanceof Const)
                    node.value = new Intconst(((Intconst) lhs).val - ((Intconst) rhs).val);
                    op = "sub"; 
                    break;
                case "*":
                    if (lhs instanceof Const && rhs instanceof Const)
                    node.value = new Intconst(((Intconst) lhs).val * ((Intconst) rhs).val);
                    op = "mul"; 
                    break;
                case "/":
                    if (lhs instanceof Const && rhs instanceof Const && ((Intconst) rhs).val != 0)
                    node.value = new Intconst(((Intconst) lhs).val / ((Intconst) rhs).val);
                    op = "sdiv"; 
                    break;
                case "%":
                    if (lhs instanceof Const && rhs instanceof Const)
                    node.value = new Intconst(((Intconst) lhs).val % ((Intconst) rhs).val);
                    op = "srem"; 
                    break;
                case "<":
                    if (lhs instanceof Const && rhs instanceof Const)
                    node.value = new Boolconst(((Intconst) lhs).val < ((Intconst) rhs).val);
                    op = "slt"; 
                    break; 
                case "<=":
                    if (lhs instanceof Const && rhs instanceof Const)
                    node.value = new Boolconst(((Intconst) lhs).val <= ((Intconst) rhs).val);
                    op = "sle"; 
                    break;
                case ">":
                    if (lhs instanceof Const && rhs instanceof Const)
                    node.value = new Boolconst(((Intconst) lhs).val > ((Intconst) rhs).val);
                    op = "sgt"; 
                    break;
                case ">=":
                    if (lhs instanceof Const && rhs instanceof Const)
                    node.value = new Boolconst(((Intconst) lhs).val >= ((Intconst) rhs).val);
                    op = "sge"; 
                    break;
                case "==":
                    if (lhs instanceof Intconst && rhs instanceof Intconst)
                    node.value = new Boolconst(((Intconst) lhs).val == ((Intconst) rhs).val);
                    op = "eq"; 
                    break;
                case "!=":
                    if (lhs instanceof Intconst && rhs instanceof Intconst)
                    node.value = new Boolconst(((Intconst) lhs).val != ((Intconst) rhs).val);
                    op = "ne"; 
                    break;
                case "<<":
                    if (lhs instanceof Const && rhs instanceof Const)
                    node.value = new Intconst(((Intconst) lhs).val << ((Intconst) rhs).val);
                    op = "shl"; 
                    break;
                case ">>":
                    if (lhs instanceof Const && rhs instanceof Const)
                    node.value = new Intconst(((Intconst) lhs).val >> ((Intconst) rhs).val);
                    op = "ashr"; 
                    break;
                case "&":
                    if (lhs instanceof Const && rhs instanceof Const)
                    node.value = new Intconst(((Intconst) lhs).val & ((Intconst) rhs).val);
                    op = "and"; 
                    break;
                case "|":
                    if (lhs instanceof Const && rhs instanceof Const)
                    node.value = new Intconst(((Intconst) lhs).val | ((Intconst) rhs).val);
                    op = "or"; 
                    break; 
                case "^":
                    if (lhs instanceof Const && rhs instanceof Const)
                    node.value = new Intconst(((Intconst) lhs).val ^ ((Intconst) rhs).val);
                    op = "xor"; 
                    break;
                
            }
            if (node.value != null) return;//如果计算的结果可以在编译时确定，则直接创建相应类型的常量并返回。
            switch (node.op) {
                case "+":
                case "-": 
                case "*": 
                case "/": 
                case "%": 
                case "<<":
                case ">>":
                case "&": 
                case "|": 
                case "^": 
                    operandType = irIntType;
                    dest = new Register("", irIntType);
                    currentBlock.addInst(new Binary(currentBlock, operandType, dest, lhs, rhs, op));
                    break;
                case "==":
                case "!=":
                    // if (lhs.type instanceof Int && lhs.type != irIntType) {
                    //     Register tmp = new Register("", irIntType);
                    //     currentBlock.addInst(new Zext(currentBlock, tmp, lhs, irIntType));
                    //     lhs = tmp;
                    // }
                    // if (rhs.type instanceof Int && rhs.type != irIntType) {
                    //     Register tmp = new Register("", irIntType);
                    //     currentBlock.addInst(new Zext(currentBlock, tmp, rhs, irIntType));
                    //     rhs = tmp;
                    // }
                    operandType = node.lhs.type.equals(new TypeNode("null")) ? node.rhs.getBasetype() : node.lhs.getBasetype();
                    dest = new Register("tmp", irBoolType);
                    currentBlock.addInst(new Icmp(currentBlock, operandType, dest, lhs, rhs, op));
                    break;
                case "<": 
                case "<=":
                case ">": 
                case ">=":
                    operandType = irIntType;
                    dest = new Register("", irBoolType);
                    currentBlock.addInst(new Icmp(currentBlock, operandType, dest, lhs, rhs, op));
                    break;
                
            }
            node.value = dest;
            
        } else {
            switch (node.op) {
            case "==":
                node.value = stringCmp("streq", loadvalue(node.lhs), loadvalue(node.rhs));
                break;
            case "!=":
                node.value = stringCmp("strne", loadvalue(node.lhs), loadvalue(node.rhs));
                break;
            case "+":
                node.value = new Register("", irStringType);
                currentBlock.addInst(new Call(currentBlock, (Register) node.value, irStringType, "stradd",loadvalue(node.lhs), loadvalue(node.rhs)));
                break;
            case ">":
                node.value = stringCmp("strgt", loadvalue(node.lhs), loadvalue(node.rhs));
                break;
            case ">=":
                node.value = stringCmp("strge", loadvalue(node.lhs), loadvalue(node.rhs));
                break;    
            case "<":
                node.value = stringCmp("strlt", loadvalue(node.lhs), loadvalue(node.rhs));
                break;
            case "<=":
                node.value = stringCmp("strle", loadvalue(node.lhs), loadvalue(node.rhs));
                break;
            
            
            }
            
        }
    };
    public void visit(TernaryexprNode node){
        node.lhs.accept(this);

        // var res = TypeToIRPtr(node.type);
        Basetype bt=change(false,node.type);
        Register temp = new Register("ternary_short", new Ptr(bt));
        currentBlock.addInst(new Alloca(currentBlock,bt,temp));
        // Register alloc = new Register("res", res);
        // nowScope.entities.put("res", alloc); // 变量名对应寄存器
        // System.out.println("*" + res.baseType);
        // Alloca order = new Alloca(currentBlock, irBoolType, temp);
        // System.out.println(order.regDest);
        // nowFunc.init.add(order); // 加入开头的allocation
        // new Basicblock(currentFunction, null, currentBlock, id)
        Basicblock thenBranch = new Basicblock(currentFunction,"mhs",currentBlock.loopDepth);
        Basicblock elseBranch = new Basicblock(currentFunction,"rhs",currentBlock.loopDepth);
        Basicblock endStmt = new Basicblock(currentFunction,"end",currentBlock.loopDepth);
        // Basicblock prev = currentBlock;
        endStmt.terminalInst=currentBlock.terminalInst;
        currentBlock.terminalInst = new Br(currentBlock, condition(node.lhs), thenBranch, elseBranch);
        currentBlock.isFinished=true;
        currentBlock=currentFunction.appendBlock(thenBranch);// nowFunc.blockList.add(thenBranch);
        //currentBlock = thenBranch;
        //nowScope = new Scope(nowScope);

        node.mhs.accept(this);
        if(! (node.mhs.type.equals(new TypeNode("void")) )){
            //System.out.print("sudsdsssssssssss");
            currentBlock.addInst(new Store(currentBlock, node.mhs.value, temp));
        }
        

        // nowScope = nowScope.parentScope;
        currentBlock.terminalInst = new Jump(currentBlock, endStmt);
        currentBlock.isFinished = true;
        currentBlock=currentFunction.appendBlock(elseBranch);
        // nowFunc.blockList.add(elseBranch);
        // currentBlock = elseBranch;
        // nowScope = new Scope(nowScope);

        node.rhs.accept(this);
        if(! (node.rhs.type.equals(new TypeNode("void")))){
            currentBlock.addInst(new Store(currentBlock, node.rhs.value, temp));
        }
        

        // nowScope = nowScope.parentScope;
        currentBlock.terminalInst = new Jump(currentBlock, endStmt);
        currentBlock.isFinished = true;
        // prev.terminalInst = new Br(prev, condition(node.lhs), thenBranch, elseBranch);
        // prev.isFinished = true;
        currentBlock=currentFunction.appendBlock(endStmt);

        // Phi phi=new Phi(currentBlock,temp);
        // phi.add(node.mhs.value, thenBranch);
        // phi.add(node.rhs.value, elseBranch);
        // currentBlock.addInst(phi);

        // nowFunc.blockList.add(endStmt);
        // currentBlock = endStmt;
        Register result = new Register("", bt);
            currentBlock.addInst(new Load(currentBlock, result, temp));
            node.value = result;
        // node.value = temp;
    };
    public void visit(SufexprNode node){
        node.expr.accept(this);
        Register dest = null;
        String op = null;
        switch (node.op) {
            case "++":
                op = "add";
                node.value = loadvalue(node.expr);
                dest = new Register("", irIntType);
                currentBlock.addInst(new Binary(currentBlock, irIntType, dest, node.value, Intconst1, op));
                currentBlock.addInst(new Store(currentBlock, dest, node.expr.storePtr));
                break;
            case "--":
                op = "sub";
                node.value = loadvalue(node.expr);
                dest = new Register("", irIntType);
                currentBlock.addInst(new Binary(currentBlock, irIntType, dest, node.value, Intconst1, op));
                currentBlock.addInst(new Store(currentBlock, dest, node.expr.storePtr));
                break;

        }
    };
    public void visit(PreexprNode node){
        node.expr.accept(this);
        Register dest = null;
        String op = null;
        switch (node.op) {//前++--可做左值
            case "++":
                op = "add";
                dest = new Register("", irIntType);
                currentBlock.addInst(new Binary(currentBlock, irIntType, dest, loadvalue(node.expr), Intconst1, op));
                currentBlock.addInst(new Store(currentBlock, dest, node.expr.storePtr));
                node.storePtr = node.expr.storePtr;
                node.value = dest;
                
                break;
            case "--":
                op = "sub";
                dest = new Register("", irIntType);
                currentBlock.addInst(new Binary(currentBlock, irIntType, dest, loadvalue(node.expr), Intconst1, op));
                currentBlock.addInst(new Store(currentBlock, dest, node.expr.storePtr));
                node.storePtr = node.expr.storePtr;
                node.value = dest;
                
                break;


        
            case "-":
                op = "sub";
                dest = new Register("", irIntType);
                currentBlock.addInst(new Binary(currentBlock, irIntType, dest, Intconst0, loadvalue(node.expr), op));
                node.value = dest;
                break;
            case "~":  // x ^ -1
                op = "xor";
                dest = new Register("", irIntType);
                currentBlock.addInst(new Binary(currentBlock, irIntType, dest, loadvalue(node.expr), Intconstn1, op));
                node.value = dest;
                break;
            case "!":
                // assert node.expr.type.equals(new TypeNode("bool"));
                op = "xor";
                dest = new Register("", irBoolType);
                currentBlock.addInst(new Binary(currentBlock, irBoolType, dest, condition(node.expr), irBoolTrueConst, op));
                node.value = dest;
        }


    };
    public void visit(AssignexprNode node){
        node.rhs.accept(this);
        node.lhs.accept(this);
        node.storePtr = node.lhs.storePtr;
        node.value = loadvalue(node.rhs);
        storein(node.storePtr, node.rhs);
    };
    public void visit(FuncexprNode node){
        node.funcName.accept(this);
        
        FuncdefNode funcDef = node.funcName.funcDef;
        
        funcDef.returnType.irType = change(true,funcDef.returnType );
        Call call = new Call(currentBlock, funcDef.returnType.irType, funcDef.className == null ? funcDef.name : funcDef.className + "." + funcDef.name);
    
        
        if (funcDef != ArraySizeFunc) {
            if (funcDef == StringLengthFunc) call.funcName = "strlen";
            else if (funcDef == StringSubStringFunc) call.funcName = "substring";
            else if (funcDef == StringParseIntFunc) call.funcName = "parseInt";
            else if (funcDef == StringOrdFunc) call.funcName = "ord";

            if (funcDef.className != null) {  // method
                if (node.funcName instanceof MemberexprNode)
                    call.args.add(((MemberexprNode) node.funcName).objAddr);//对象地址 objAddr 添加到 call.args 中
                else {  
                    Register thisPtr = currentScope.getIRVarPtr("this");
                    Register thisVal = new Register("", ((Ptr) thisPtr.type).pointToType());
                    currentBlock.addInst(new Load(currentBlock, thisVal, thisPtr));
                    call.args.add(thisVal);
                }
            }//获取当前作用域中的 this 指针地址，并创建一个新的寄存器 thisVal
           
            if (node.args != null) {
                node.args.accept(this);
                for(var ele:node.args.exprs){
                    call.args.add(loadvalue(ele));
                }
               
            }
            if (funcDef.returnType.irType != irVoidType)
                call.call = new Register("", funcDef.returnType.irType);
            currentBlock.addInst(call);
            node.value = call.call;
            
        } else {
            call.funcName="array_size";
            // if (funcDef.returnType.irType != irVoidType){//返回类型不是void
            //     call.call = new Register("", funcDef.returnType.irType);
            // }
            // currentBlock.addInst(call);
            // node.value = call.call;
            MemberexprNode me=(MemberexprNode)node.funcName;
            Register array = me.objAddr;//数组地址
            Register tmp1;// tmp2 = new Register("", irIntPtrType);//tmp2 是指向整型指针的寄存器
            tmp1 = new Register("", irIntType);
            node.value=tmp1;
                // currentBlock.addInst(new Alloca(currentBlock,irIntType,tmp1));
            currentBlock.addInst(new Call(currentBlock,tmp1,irIntType,"array_size",array));
            // Basetype type=array.type;
            // if (type instanceof Ptr && ((Ptr)type).dim==1&&((Ptr)type).baseType instanceof Int)
            //     tmp1 = array;
            // else {//字节数不是4
                
            //     // currentBlock.addInst(new Bitcast(currentBlock, array, irIntPtrType, tmp1));
            // }
            // currentBlock.addInst(new Getelementptr(currentBlock, tmp1, tmp2, Intconstn1));
            // node.value = new Register("", irIntType);
            // currentBlock.addInst(new Load(currentBlock, (Register) node.value, tmp2)); 
            //数组0位之前一位存size
            
        }
    };
    public void visit(IndexexprNode node){
        // node.array.accept(this);
        // Valu Pre = loadvalue(node.array);
        // Register tmp = null;
        // for (var each : node.indexlist) {
        //     each.accept(this);
        //     tmp = new Register("", ((Ptr) Pre.type));
        //     Getelementptr tmpGep = new Getelementptr(currentBlock, Pre,tmp, loadvalue(each));
        //     currentBlock.addInst(tmpGep);
        //     // IndexexprNode no=new Index
        //     Pre = loadvalue(tmp);
        // }
        // node.storePtr = tmp;
        // return;
        node.array.accept(this);
        node.index.accept(this);
        Register dest = new Register("", loadvalue(node.array).type);
        currentBlock.addInst(new Getelementptr(currentBlock, loadvalue(node.array), dest, loadvalue(node.index)));
        node.storePtr = dest;
    };
    public void visit(MemberexprNode node){
        // node.
    // all class is pointer
    
        node.obj.accept(this);

        var objRealType = loadvalue(node.obj).type;//所有的类都是指针类型。
        // 转换为寄存器类型，并存储在 node.objAddr 中，后续的成员函数调用使用。
        node.objAddr = (Register) node.obj.value;  
        
        
        Ptr pt=(Ptr) objRealType;
        
        objRealType = pt.pointToType();
        
        
        
        
         
        if (objRealType instanceof Classtype) {
            Classtype cl=(Classtype) objRealType;
            
            
// System.out.println("884444444444444444");
            Basetype memberType = ((Classtype) objRealType).getMemberType(node.member);
            if (memberType != null) {
                int num=cl.memberOffset.get(node.member);
                node.storePtr = new Register("", new Ptr(memberType));
            
                currentBlock.addInst(new Getelementptr(currentBlock, loadvalue(node.obj), node.storePtr, Intconst0,new Intconst(num)));
            }
        }
    };  
    public void visit(NewexprNode node){
        Basetype type = change(false,node.type );
        if (node.dim == 0) {
            Classtype classType = (Classtype) ((Ptr) type).pointToType();
            Register callReg = new Register("", new Ptr(type));//指向8bit

            currentBlock.addInst(new Call(currentBlock, callReg, new Ptr(type), "newClass", new Intconst(classType.size)));
            // currentBlock.addInst(new Call(currentBlock, callReg, irStringType, "malloc", new Intconst(classType.size)));

            node.value =callReg;
            // currentBlock.addInst(new Bitcast(currentBlock, callReg, type, (Register) node.value));
            if (classType.hasBuild){
                currentBlock.addInst(new Call(currentBlock, null, irVoidType, classType.name + "." + classType.name, node.value));
            }
        } else {
            node.value = node.sizeList.size() > 0 ? newArray(type, 0, node.sizeList) : new Nullconst(type);
            
        }
    };
    public void visit(ExprlistNode node){
        for(var ele:node.exprs){
            ele.accept(this);
        }
    };

    public void visit(ContentstmtNode node){
        currentScope = new Scope(currentScope);
        for(var ele:node.stmts){
            ele.accept(this);
        }
        // node.stmts.forEach(stmt -> stmt.accept(this));
        currentScope = currentScope.parentScope;
    };
    public void visit(IfstmtNode node){

        node.cond.accept(this);
        Valu cond = condition(node.cond);
        Basicblock lastBlock = currentBlock;
        Basicblock nextBlock = new Basicblock(currentFunction, "ifend", currentBlock.loopDepth);
        nextBlock.terminalInst = currentBlock.terminalInst;
        Basicblock thenBlock = new Basicblock(currentFunction, "then", nextBlock, currentBlock.loopDepth);
            //then的下一个是nextBlock
        currentScope = new Scope(currentScope);
        currentBlock.isFinished = true;
        currentBlock = currentFunction.appendBlock(thenBlock);
        for(var ele:node.thenStmts){
            ele.accept(this);
        }
        
        currentScope = currentScope.parentScope;
        if (node.elseStmts != null && !node.elseStmts.isEmpty()) {
            Basicblock elseBlock = new Basicblock(currentFunction, "else", nextBlock, currentBlock.loopDepth);
                
            currentScope = new Scope(currentScope);
            currentBlock.isFinished = true;
            currentBlock = currentFunction.appendBlock(elseBlock);
            for(var ele:node.elseStmts){
                ele.accept(this);
        }
            
            currentScope = currentScope.parentScope;
            lastBlock.terminalInst = new Br(lastBlock, cond, thenBlock, elseBlock);
        } else {
            lastBlock.terminalInst = new Br(lastBlock, cond, thenBlock, nextBlock);
        }
            currentBlock.isFinished = true;
            currentBlock = currentFunction.appendBlock(nextBlock);
    };
    public void visit(WhilestmtNode node){
        node.condBlock = new Basicblock(currentFunction, "whilecond", currentBlock.loopDepth + 1);
        node.loopBlock = new Basicblock(currentFunction, "loop", currentBlock.loopDepth + 1);
        node.nextBlock = new Basicblock(currentFunction, "end", currentBlock.loopDepth);
        //开三个块
        node.nextBlock.terminalInst = currentBlock.terminalInst;
        currentBlock.terminalInst = new Jump(currentBlock, node.condBlock);
        currentBlock.isFinished = true;
        currentBlock = currentFunction.appendBlock(node.condBlock);
        node.cond.accept(this);
        currentBlock.terminalInst = new Br(currentBlock, condition(node.cond), node.loopBlock, node.nextBlock);
        currentBlock = currentFunction.appendBlock(node.loopBlock);
        currentScope = new Scope(currentScope, node);
        for(var ele:node.stmts){
            ele.accept(this);
        }
        currentScope = currentScope.parentScope;
        currentBlock.terminalInst = new Jump(currentBlock, node.condBlock);
        currentBlock.isFinished = true;
        currentBlock = currentFunction.appendBlock(node.nextBlock);
    };
    public void visit(ForstmtNode node){
        currentScope = new Scope(currentScope, node);
        if (node.varDef != null) node.varDef.accept(this);
        if (node.init != null) node.init.accept(this);
        node.condBlock = new Basicblock(currentFunction, "for.cond", currentBlock.loopDepth + 1);
        node.loopBlock = new Basicblock(currentFunction, "loop", currentBlock.loopDepth + 1);
        node.stepBlock = new Basicblock(currentFunction, "step", currentBlock.loopDepth + 1);
        node.nextBlock = new Basicblock(currentFunction, "end", currentBlock.loopDepth);
        //创建4个block
        node.nextBlock.terminalInst = currentBlock.terminalInst;
        currentBlock.terminalInst = new Jump(currentBlock, node.condBlock);
        currentBlock.isFinished = true;
        currentBlock = currentFunction.appendBlock(node.condBlock);
        if (node.cond != null) {
          node.cond.accept(this);
          currentBlock.terminalInst = new Br(currentBlock, condition(node.cond), node.loopBlock, node.nextBlock);
            
        } else {
          currentBlock.terminalInst = new Jump(currentBlock, node.loopBlock);
        }
        currentBlock.isFinished = true;
        currentScope = new Scope(currentScope);
        currentBlock = currentFunction.appendBlock(node.loopBlock);
        for(var ele:node.stmts){
            ele.accept(this);
        }
       
        currentBlock.terminalInst = new Jump(currentBlock, node.stepBlock);
        currentBlock.isFinished = true;
        currentBlock = currentFunction.appendBlock(node.stepBlock);
       
        currentScope = currentScope.parentScope;
        if (node.step != null) node.step.accept(this);
        currentBlock.terminalInst = new Jump(currentBlock, node.condBlock);
        currentBlock.isFinished = true;
        currentBlock = currentFunction.appendBlock(node.nextBlock);
        currentScope = currentScope.parentScope;       
    };
    public void visit(ContinuestmtNode node){
        if (currentScope.incircle instanceof WhilestmtNode)
            currentBlock.terminalInst = new Jump(currentBlock, currentScope.incircle.condBlock);
        else
            currentBlock.terminalInst = new Jump(currentBlock, ((ForstmtNode) currentScope.incircle).stepBlock);
        currentBlock.isFinished = true;
    };
    public void visit(BreakstmtNode node){
        currentBlock.terminalInst = new Jump(currentBlock, currentScope.incircle.nextBlock);
        currentBlock.isFinished = true;
    };
    public void visit(ReturnstmtNode node){
        if (node.expr != null) {
            node.expr.accept(this);
            storein(currentFunction.retAddr, node.expr);
          }
        currentBlock.terminalInst = new Jump(currentBlock, currentFunction.exitBlock);
        currentBlock.isFinished = true;
    };
    public void visit(ExprstmtNode node){
        if (node.expr != null) node.expr.accept(this);
    };
    public Basetype change(boolean flag,TypeNode tp){
        Basetype bt;
        if(tp.type.equals("int")){
            bt=irIntType;
        }else if(tp.type.equals("bool")){
            // if(flag){
            //     bt=irCondType;
            // }else{
                bt=irBoolType;
            //}
        }else if(tp.type.equals("void")){
            bt=irVoidType;
        }else if(tp.type.equals("string")){
            bt=irStringType;
        }else{
            Ptr pt = new Ptr(Classmap.get(tp.type), 1);
            pt.flag=false;
            bt=(Basetype)pt;
        }
        if(tp.dim>0){
            Ptr pt = new Ptr(bt, tp.dim);
            pt.flag=false;
            bt=(Basetype)pt;
        }
        return bt;
    };
    
  public Valu loadvalue(ExprNode node) {
    
    if (node.value == null){
        //if(node.storePtr==null)System.out.print(node.str+"\n");
        Ptr pt=(Ptr) node.storePtr.type;
        
        Basetype bt=pt.pointToType();
        
        Register val = new Register("",bt );//创建这个实体
        // if(currentBlock==null)System.out.println("82223747475"+'\n');
        currentBlock.addInst(new Load(currentBlock, val, node.storePtr));//
        node.value=val;
        return node.value ;
        
    }

        
    else {
        return node.value;
        // assert node.storePtr != null;//断言node.storePtr不是null
        
        
    }
  }
//   static public boolean is_Bool_Type(Basetype type) {
//     if (type instanceof Int) {
//         return ((Int) type).size==8 || ((Int) type).size == 1;
//     }
//     return false;
// }
//   public Valu Get_Value(Valu tmpValue) {
//     if (tmpValue.type instanceof Ptr) {
//         if (is_Bool_Type((((Ptr) tmpValue.type).pointToType()))) {
//             Register tmpvalue = new Register("", new Int(1));
//             Register ToBool = new Ir_Reg(Find_Available_Name(".ToBool", 0), new Int_Type(32, true));
//             currentBlock.add_inst(new Load(ToBool, tmpValue));
//             currentBlock.add_inst(new Trunc(tmpvalue, ToBool));
//             return tmpvalue;
//         }
//         Valu ret = new Ir_Reg(Find_Available_Name(tmpValue.Name, 0), ((Pointer_Type) tmpValue.Type).To_Type);
//         currentBlock.addInst(null);(new Load(ret, tmpValue));
//         return ret;
//     }
//     return tmpValue;
//}
    public Valu condition(ExprNode node) {
        Valu cond = loadvalue(node);//获得实体
        if (cond.type == irBoolType) {
            if (cond instanceof Boolconst ){
                Boolconst boolConst=(Boolconst)cond;
                return boolConst.val ? irBoolTrueConst : irBoolFalseConst;
            }
            //Register tmp = new Register("", irBoolType);
            // currentBlock.addInst(new Trunc(currentBlock, tmp, cond, irCondType));
            return cond;
        }
        else {
            return cond;
        }
    }
    public void storein(Register ptr, ExprNode rhs) {
        if (loadvalue(rhs).type != irBoolType) {
            if (rhs.value instanceof Nullconst){
                rhs.value = new Nullconst(((Ptr) ptr.type).pointToType());
        }
            currentBlock.addInst(new Store(currentBlock, rhs.value, ptr));
            
        } else {
            if (rhs.value instanceof Boolconst ){
                Boolconst condConst=(Boolconst)rhs.value;
                currentBlock.addInst(new Store(currentBlock, condConst.val ? irBoolTrueConst : irBoolFalseConst, ptr));//添加store指令
            }
            else {
                //Register tmp = new Register("", irBoolType);
                // currentBlock.addInst(new Zext(currentBlock, tmp, rhs.value, irBoolType));
                currentBlock.addInst(new Store(currentBlock, rhs.value, ptr));
            }
        }
    }

    public Register stringCmp(String cmpName, Valu lhs, Valu rhs) {
        Register tmp = new Register("", irBoolType), res = new Register("", irBoolType);
        currentBlock.addInst(new Call(currentBlock, tmp, irBoolType, cmpName, lhs, rhs));
        // currentBlock.addInst(new Trunc(currentBlock, res, tmp, irCondType));
        return tmp;
    }
    private Valu newArray(Basetype type, int pos, ArrayList<ExprNode> sizeList) {
 // step 1: calculate the size to malloc: layer_size * sizeof(innerType) + 4;
 // step 2: call malloc and store layer_size
 // step 3: use GetElementPtr to get the head of this array

        Register call = new Register("", new Ptr(type));//保存 malloc 函数的返回值。
        sizeList.get(pos).accept(this);
        Valu cnt = loadvalue(sizeList.get(pos));// size;
        // int sizeOfType = ((Ptr) type).pointToType().size;
        // if (cnt instanceof Intconst) {
        //     size = new Intconst(((Intconst) cnt).val * sizeOfType + 4);
        // } else {
        //     Intconst typeSize = new Intconst(sizeOfType);
        //     Register midsize = new Register("", irIntType);
        //     currentBlock.addInst(new Binary(currentBlock, irIntType, midsize, cnt, typeSize, "mul"));
        //     size = new Register("", irIntType);
        //     currentBlock.addInst(new Binary(currentBlock, irIntType, (Register) size, midsize, Intconst4, "add"));
        // }
        currentBlock.addInst(new Call(currentBlock, call, new Ptr(type), "newPtrArray", cnt));
        // currentBlock.addInst(new Call(currentBlock, call, new Ptr(irCharType), "malloc", size));
        Register head=call;
        // store the size of array
        //存储数组大小信息到分配的内存空间中。首先，创建一些临时寄存器变量 ptr、tmp1 和 tmp2。
        //然后，使用 Bitcast 指令将 callReg 转换为 irIntPtrType 类型，并将结果存储到 tmp1 中。
        //接着，使用 Store 指令将 cnt 存储到 tmp1 中。再使用 Getelementptr 指令计算 tmp2 的值，用于存储数组元素的起始地址。
        //最后，根据数组元素类型的不同，使用 Bitcast 指令将 tmp2 转换为对应类型的指针，并将结果存储到 ptr 中
        // Register head, tmp1 = new Register("", irIntPtrType), tmp2 = new Register("", irIntPtrType);
        // currentBlock.addInst(new Bitcast(currentBlock, call, irIntPtrType, tmp1));
        // currentBlock.addInst(new Store(currentBlock, cnt, tmp1));
        // currentBlock.addInst(new Getelementptr(currentBlock, tmp1, tmp2, Intconst1));
        
        // if (type instanceof Ptr && ((Ptr)type).dim==1&&((Ptr)type).baseType instanceof Int) {
        //     head = tmp2;
        // }
        // else {
        //     head = new Register("", type);
        //     currentBlock.addInst(new Bitcast(currentBlock, tmp2, type, head));
        // }

        if (pos + 1 < sizeList.size()) {
            Register idx = new Register("", irIntPtrType);
            currentBlock.addInst(new Alloca(currentBlock, irIntType, idx));
            currentBlock.addInst(new Store(currentBlock, Intconst0, idx));
            Basicblock condBlock = new Basicblock(currentFunction, "forcond", currentBlock.loopDepth + 1);
            Basicblock loopBlock = new Basicblock(currentFunction, "loop", currentBlock.loopDepth + 1);
            Basicblock stepBlock = new Basicblock(currentFunction, "step", currentBlock.loopDepth + 1);
            Basicblock nextBlock = new Basicblock(currentFunction, "end", currentBlock.loopDepth);
            nextBlock.terminalInst = currentBlock.terminalInst;
            currentBlock.terminalInst = new Jump(currentBlock, condBlock);
            currentBlock.isFinished = true;

            currentBlock = currentFunction.appendBlock(condBlock);
            Register cond = new Register("", irBoolType);
            Register iVal = new Register("", irIntType);
            currentBlock.addInst(new Load(currentBlock, iVal, idx));
            currentBlock.addInst(new Icmp(currentBlock, irIntType, cond, iVal, cnt, "slt"));
            currentBlock.terminalInst = new Br(currentBlock, cond, loopBlock, nextBlock);
            currentBlock.isFinished = true;

            currentBlock = currentFunction.appendBlock(loopBlock);
            Valu iPtrVal = newArray(((Ptr) type).pointToType(), pos + 1, sizeList);
            Register iPtr = new Register("", type);
            // should load in every block!
            Register cn = new Register("", irIntType);
            currentBlock.addInst(new Load(currentBlock, cn, idx));
            currentBlock.addInst(new Getelementptr(currentBlock, head, iPtr, cn));
            currentBlock.addInst(new Store(currentBlock, iPtrVal, iPtr));
            currentBlock.terminalInst = new Jump(currentBlock, stepBlock);
            currentBlock.isFinished = true;

            currentBlock = currentFunction.appendBlock(stepBlock);
            Register result = new Register("", irIntType);
            Register step = new Register("", irIntType);
            currentBlock.addInst(new Load(currentBlock, step, idx));
            currentBlock.addInst(new Binary(currentBlock, irIntType, result, step, Intconst1, "add"));
            currentBlock.addInst(new Store(currentBlock, result, idx));
            currentBlock.terminalInst = new Jump(currentBlock, condBlock);
            currentBlock.isFinished = true;

            currentBlock = currentFunction.appendBlock(nextBlock);
        }
        return head;
    }
//   public Ptr TypeToIRPtr(TypeNode type) {
//     // ExprNode ex=new ExprNode(null);
//     var IRType = change(false,type);
//     if (IRType instanceof Ptr) {
//         return new Ptr(((Ptr) IRType).baseType, ((Ptr) IRType).dim, false);
//     } else {
//         return new Ptr(IRType, 0, false);
//     }
// }
    // private static final Basetype
    //         intType = new Int(32), boolType = new Int(8), condType = new Int(1),
    //         ptrType = new Ptr(intType, 0), charStar = new Ptr(boolType, 0),
    //         voidType = new Void(), nullType = new Ptr(new Void()), stringType = new Ptr(boolType, 0);

    // private static final Const
    //         intOne = new Intconst(1), intZero = new Intconst(0), minusOne = new Intconst(-1), nullValue = new Nullconst();
    

    
}
