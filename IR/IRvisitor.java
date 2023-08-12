package IR;

import IR.Instruction.*;;

public interface IRvisitor {
    abstract public void visit(Basicblock tmpBlock);
    abstract public void visit(Functionblock tmpBlock);
    abstract public void visit(Programblock tmpblock);

    abstract public void visit(Binary tmpnode);
    abstract public void visit(Alloca tmpnode);
    abstract public void visit(Load tmpnode);
    abstract public void visit(Store tmpnode);
    abstract public void visit(Getelementptr tmpnode);
    abstract public void visit(Br tmpnode);
    abstract public void visit(Ret tmpnode);
    abstract public void visit(Icmp tmpnode);
    abstract public void visit(Call tmpnode);
    abstract public void visit(Phi tmpnode);

    // abstract public void visit(Global_DeclareVar tmpnode);
    // abstract public void visit(Ptrtoint tmpnode);
    abstract public void visit(Bitcast tmpnode);
    
    abstract public void visit(Zext tmpnode);
    abstract public void visit(Trunc tmpnode);
    
    // abstract public void visit(Inttoptr tmpnode);
    
    
    
    // abstract public void visit(Global_Declare tmpnode);
    // abstract public void visit(Uncond_Br tmpnode);
    
    abstract public void visit(Classdef tmpnode);
    abstract public void visit(Funcdef tmpnode);
    
}
