import java.util.ArrayList;
/**
 * Décrivez votre classe PrettyPrinter ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class PrettyPrinter extends Visitor
{
    public String aString;
    
    public void visit(Number pNum) {
        this.aString = "NUMBER(" + Integer.toString(pNum.getValue())+")";
    }
    
    public void visit(Strings pString)  {
        this.aString = "STRING(" + pString.getValue() + ")";
    }
    
    public void visit(Variable pVar)    {
        this.aString = "VARIABLE(" + pVar.getName() + ":" + pVar.getValue() + ")";
    }
    
    public void visit(ID pID)    {
        this.aString = "ID(" + pID.getName() + ":" + pID.getValue() + ")";   
    }
    
    public void visit(UnOp pU)  {
        String vSign = pU.getSign();
        pU.getExpression().accept(this);
        String s = this.aString;
        if (vSign == "-")
            this.aString = "NEGATIVE(" + s + ")";
        else
            this.aString = "("+s+")";
    }
    
    public void visit(BinOp pBin)  {
        pBin.getLHS().accept(this);
        String s1 = this.aString;
        pBin.getRHS().accept(this);
        String s2 = this.aString;
        String vSign = pBin.getSign();
        this.aString = "("+ s1 + vSign + s2 + ")";
    }
    
    public void visit(BoolBinOp pBool)  {
        pBool.getLHS().accept(this);
        String s1 = this.aString;
        pBool.getRHS().accept(this);
        String s2 = this.aString;
        String vSign = pBool.getSign();
        this.aString = "("+ s1 + vSign + s2 + ")";
    }
    
    public void visit(Print pPrint) {
        pPrint.getExpression().accept(this);
        this.aString = "PRINT(" + this.aString + ")";
    }
    
    public void visit(ifThenElse pI) {
        pI.getCond().accept(this);
        String s1 = this.aString;
        pI.getThen().accept(this);
        String s2 = this.aString;
        pI.getElse().accept(this);
        String s3 = this.aString;
        this.aString = "IF(" + s1 + ") THEN(" + s2 + ") ELSE(" +s3 + ")";
    }
    
    public void visit(Scope pScope) {
        String s = "SCOPE{";
        ArrayList<Statement> vListOfStatements = pScope.getStatements();

        for(Statement vStatements : vListOfStatements){
            vStatements.accept(this);
            s += this.aString+ ", ";
        }
        s += "}";
        this.aString = s;
    }
    
    public void visit(Instruction pIns) {
        pIns.getExpression().accept(this);
        String s = this.aString;
        this.aString = "INSTRUCTION(" + s + ")";
    }
    
    public void visit (Initialization pI)   {
        pI.getID().accept(this);
        String s1 = this.aString;
        pI.getExpression().accept(this);
        String s2 = this.aString;
        this.aString = "INITIALIZATION(" + s1 + ", " + s2 + ")";
    }
    
    public void visit(Assign pA)    {
        pA.getVariable().accept(this);
        String s1 = this.aString;
        pA.getVariable().accept(this);
        String s2 = this.aString;
        this.aString = "ASSIGN(" + s1 + ", " + s2 + ")";
    }
    
    public void visit(WhileDo pWD)  {
       pWD.getCond().accept(this);
       String s1 = this.aString;
       pWD.getDo().accept(this);
       String s2 = this.aString;
       this.aString = "WHILE(" + s1 + ") DO (" +s2 + ")";
    }
    
    public void visit(For pF)   {
        pF.getAssign().accept(this);
        String s1 = this.aString;
        pF.getTo().accept(this);
        String s2 = this.aString;
        pF.getDo().accept(this);
        String s3 = this.aString;
        this.aString = "FOR(" + s1 + ") TO (" + s2 + ") DO (" + s3 + ")";
    }
}
