
/**
 * Décrivez votre classe BoolBinOp ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class BoolBinOp extends Expression
{
    private Expression LHS;
    private Expression RHS;
    private String aOp;

    public BoolBinOp (Expression pLHS, Expression pRHS, String pOp) {
        this.LHS = pLHS;
        this.RHS = pRHS;
        if (pOp == "<" ^ pOp == ">" ^ pOp == "<=" ^ pOp == ">=" ^ pOp == "=" ^ pOp == "<>")
            this.aOp = pOp;
        else
            System.out.println("Not a boolean operation !");
    }
    
    public Expression getLHS () {
        return this.LHS;
    }
    
    public Expression getRHS () {
        return this.RHS;
    }
    
    public String getSign() {
        return this.aOp;
    }
    
    public void accept(Visitor v)   {
        v.visit(this);
    }
}
