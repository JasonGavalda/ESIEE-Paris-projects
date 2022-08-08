
/**
 * Décrivez votre classe abstraite Visitor ici.
 *
 * @author  (votre nom)
 * @version (un numéro de version ou une date)
 */
public abstract class Visitor
{
    public void visit(Statement pStatement)  {
        pStatement.accept(this);
    }
    
    public abstract void visit(Number pNum);
    public abstract void visit(Strings pS);
    public abstract void visit(UnOp pU);
    public abstract void visit(BinOp pBin);
    public abstract void visit(BoolBinOp pBool);
    public abstract void visit(Print pPrint);
    public abstract void visit(ifThenElse pI);
    public abstract void visit(Scope pScope);
    public abstract void visit(Variable pVar);
    public abstract void visit(ID pID);
    public abstract void visit(Initialization pIni);
    public abstract void visit(Assign pA);
    public abstract void visit(WhileDo pWD);
    public abstract void visit(For pF);
}
