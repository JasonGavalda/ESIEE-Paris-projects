import java.util.LinkedList;
import java.util.ArrayList;
import java.util.ListIterator;
import static java.lang.System.out;

public class Evaluator extends Visitor
{
    public int aInt;
    public String aString;
    public Type aType;
    public Scope aScope;
    
    public Evaluator(Scope pS)    {
        aScope = pS;
    }
    
    public Type getType()    {
        return this.aType;
    }
    
    public void visit(Number pNum) {
        this.aInt = pNum.getValue();
        this.aString = Integer.toString(pNum.getValue());
    }
    
    public void visit(Strings pString)  {
        this.aString = pString.getValue().replace("\"", "");
    }
    
    public void visit(Variable pVar)    {
        this.aInt = pVar.getValue();
        this.aString = Integer.toString(this.aInt);
    }
    
    public void visit(ID pID)    {
        this.aScope.getVariable(pID.getName()).accept(this);
        this.aString = Integer.toString(this.aInt);
    }
    
    public void visit(UnOp pU) {
        pU.getExpression().accept(this);
        if (pU.getSign() == "-")
            this.aInt = -aInt;
        else
            this.aInt = +aInt;
        this.aString = Integer.toString(this.aInt);
    }
    
    public void visit(BinOp pBin)  {
        Type vType = pBin.getType();
        this.aType = vType;
        if(vType == Type.T_Int)   {
            pBin.getLHS().accept(this);
            int v1 = this.aInt;
            pBin.getRHS().accept(this);
            int v2 = this.aInt;
            String vSign = pBin.getSign();
            if (vSign == "+")
                this.aInt = v1 + v2;
            else if (vSign == "-")
                this.aInt = v1 - v2;
            else if (vSign == "*")
                this.aInt = v1*v2;
            else if (vSign == "/")
                this.aInt = v1 / v2;
            else
                System.out.println("Not a recognized arithmetic operation !");
        }
        else if (vType == Type.T_String)    {
            pBin.getLHS().accept(this);
            String v1 = this.aString.replace("\"", "");
            pBin.getRHS().accept(this);
            String v2 = this.aString.replace("\"", "");
            String vSign = pBin.getSign();
            if (vSign == "+")
                this.aString = v1 + v2;
            else
                System.out.println("Not a recognized arithmetic operation for strings !");
        }
    }
    
    public void visit(BoolBinOp pBool) {
        Type vType = pBool.getType();
        this.aType = vType;
        String vSign = pBool.getSign();
        if(vType == Type.T_Int)   {
            pBool.getLHS().accept(this);
            int v1 = this.aInt;
            pBool.getRHS().accept(this);
            int v2 = this.aInt;
            
            if (vSign == "<")   {
                if (v1 < v2)
                    this.aInt = 1;
                else
                    this.aInt = 0;
                
            }
            else if (vSign == ">")  {
                if (v1 > v2)
                    this.aInt = 1;
                else
                    this.aInt = 0;
            }
            else if (vSign == "<=")  {
                if (v1 <= v2)
                    this.aInt = 1;
                else
                    this.aInt = 0;
            }
            else if (vSign == ">=")  {
                if (v1 >= v2)
                    this.aInt = 1;
                else
                    this.aInt = 0;
            }
            else if (vSign == "=")  {
                if (v1 == v2)
                    this.aInt = 1;
                else
                    this.aInt = 0;
            }
            else if (vSign == "<>")  {
                if (v1 != v2)
                    this.aInt = 1;
                else
                    this.aInt = 0;
            }
        }
        else if (vType == Type.T_String)    {
            pBool.getLHS().accept(this);
            String v1 = this.aString.replace("\"", "");
            pBool.getRHS().accept(this);
            String v2 = this.aString.replace("\"", "");
            int comparison = v1.compareTo(v2);
            if (vSign == "<")   {
                if (comparison < 0)
                    this.aInt = 1;
                else
                    this.aInt = 0;
            }
            else if (vSign == ">")   {
                if (comparison > 0)
                    this.aInt = 1;
                else
                    this.aInt = 0;
            }
            else if (vSign == "<=")   {
                if (comparison <= 0)
                    this.aInt = 1;
                else
                    this.aInt = 0;
            }
            else if (vSign == ">=")   {
                if (comparison >= 0)
                    this.aInt = 1;
                else
                    this.aInt = 0;
            }
            else if (vSign == "=")   {
                if (comparison == 0)
                    this.aInt = 1;
                else
                    this.aInt = 0;
            }
            else if (vSign == "<>")   {
                if (comparison != 0)
                    this.aInt = 1;
                else
                    this.aInt = 0;
            }
            else
                throw new RuntimeException("Not a recognized arithmetic operation for strings !");
        }
        this.aString = "" + this.aInt + "\n";
    }
    
    public void visit(Print pPrint) {
        pPrint.getExpression().accept(this);
        
        if(pPrint.getExpression().getType() == Type.T_Int)
            this.aString = "" + this.aInt + "\n";
        else
            this.aString = "" + this.aString + "\n";
    }
    
    public void visit(ifThenElse pI)   {
        pI.getCond().accept(this);
        int v = this.aInt;
        if(v == 1)
            pI.getThen().accept(this);
        else
            pI.getElse().accept(this);
    }
    
    public void visit(Scope pScope) {
        this.aScope = pScope;
        for (Statement vStatements : pScope.getStatements())    {
            vStatements.accept(this);   
        }
        this.aScope = pScope.getParent();
    }
    
    public void visit(Instruction pIns) {
        pIns.getExpression().accept(this);
        this.aString = "";
    }
    
    public void visit(Initialization pI)    {
        String vName= pI.getID().getName();
        Type vType = pI.getID().getType();
        Scope vScope = pI.getScope();
        pI.getExpression().accept(this);
        int vValue = this.aInt;     
        pI.getID().setValue(vValue);
        Variable vVar = new Variable (vName);
        vVar.setValue(vValue);
        this.aScope = vScope;
        this.aScope.addVariable(vVar);
    }
    
    public void visit(Assign pA)    {
        pA.getVariable().accept(this);
        int vValue = this.aInt;
        pA.getExpression().accept(this);
        String vName = pA.getVariable().getName();
        this.aScope.changeValue(vName, vValue);
        this.aString = "";
    }
    
    public void visit(WhileDo pWD)  {
        pWD.getCond().accept(this);
        int v1 = this.aInt;
        String s = "";

        while(v1 == 1){
            pWD.getDo().accept(this);
            s += this.aString + "\n";
            pWD.getCond().accept(this);
            v1 = this.aInt;
        }
        this.aString = s;
    }
    
    public void visit(For pF)   {
        pF.getAssign().accept(this);
        int v1 = this.aInt;
        pF.getTo().accept(this);
        int v2 = this.aInt;
        String s = "";
        for(int i = v1; v1 < v2; v1++)  {
            pF.getDo().accept(this);
            s += this.aString + "\n";
        }
        this.aString = s;
    }
}
