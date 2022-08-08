import java.util.LinkedList;
import java.util.ListIterator;
import java.util.HashMap;
import java.util.ArrayList;

public class TypeChecker extends Visitor
{
    public Type aType;
    
    public TypeChecker()    {
        this.aType = Type.T_Null;
    }
    
    public void visit (Number pNum) {
        this.aType = Type.T_Int;
    }
    
    public void visit (Strings pString) {
        this.aType = Type.T_String;
    }
    
    public void visit (Variable pVar)   {
        this.aType = Type.T_Int;
    }
    
    public void visit (ID pID)    {
        this.aType = Type.T_Int;   
    }
    
    public void visit (UnOp pU) {
        pU.getExpression().accept(this);
        Type vType = this.aType;
        
        if (vType != Type.T_String) {
            pU.setType(aType);
        }
        else
            throw new RuntimeException("A string isn't Unary !");
    }
    
    public void visit (BinOp pBin)  {
        pBin.getLHS().accept(this);
        Type vType1 = this.aType;
        pBin.getRHS().accept(this);
        Type vType2 = this.aType;
        
        if( vType1 == vType2 )
            pBin.setType(this.aType);
        else
            throw new RuntimeException("Types of this operation aren't the same !");
    }
    
    public void visit (BoolBinOp pBool) {
        pBool.getLHS().accept(this);
        Type vType1 = this.aType;
        pBool.getRHS().accept(this);
        Type vType2 = this.aType;
        
        if( vType1 == vType2 && vType1 == Type.T_Int)
            pBool.setType(Type.T_Int);
        else if ( vType1 == vType2 && vType1 == Type.T_String)
            pBool.setType(Type.T_String);
        else
            throw new RuntimeException("Types of this operation aren't the same !");
    }
    
    public void visit (Print pPrint)    {
        pPrint.getExpression().accept(this);
        this.aType = pPrint.getExpression().getType();
    }
    
    public void visit (ifThenElse pI)   {
        pI.getCond().accept(this);
        pI.getThen().accept(this);
        pI.getElse().accept(this);
        this.aType = Type.T_Void;
    }
    
    public void visit(Scope pScope) {
        this.aType = Type.T_Void;
    }
    
    public void visit(Instruction pIns) {
        pIns.getExpression().accept(this);
        this.aType = Type.T_Void;
    }
    
    public void visit(Initialization pI)    {
        pI.getID().accept(this);
        Type vType1 = this.aType;
        pI.getExpression().accept(this);
        Type vType2 = this.aType;
        String vName = pI.getID().getName();
        Type vType = pI.getID().getType();
        
        if(vType1 == vType2)
            this.aType = Type.T_Void;
        else
            throw new RuntimeException("Incompatible types");
    }
    
    public void visit (Assign pA)   {
        pA.getVariable().accept(this);
        Type vType1 = this.aType;
        pA.getVariable().accept(this);
        Type vType2 = this.aType;
        
        if(vType1==vType2)
            this.aType = Type.T_Void;
        else
            throw new RuntimeException("Types of this operation aren't the same !");
    }
    
    public void visit (WhileDo pWD) {
        pWD.getCond().accept(this);
        pWD.getDo().accept(this);
        this.aType = Type.T_Void;
    }
    
    public void visit (For pF)  {
        pF.getAssign().accept(this);
        Type vType1 = this.aType;
        pF.getDo().accept(this);
        Type vType2 = this.aType;
        if (vType1 == Type.T_Void && vType2 == Type.T_Int)
            this.aType = Type.T_Void;
        else
            throw new RuntimeException("Impossible to make a for statement with those types !");
    }
}
