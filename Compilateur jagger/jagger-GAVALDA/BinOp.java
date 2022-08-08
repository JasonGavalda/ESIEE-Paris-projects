
/**
 * Décrivez votre classe BinOp ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class BinOp extends Expression
{
    private Expression Lhs;
    private Expression Rhs;
    private String Op; 
    
    public BinOp (Expression pLhs, Expression pRhs, String pOp)
    {
        this.Lhs = pLhs;
        this.Rhs = pRhs;
        if (pOp == "+" ^ pOp == "-" ^ pOp == "*" ^ pOp == "/")
            this.Op = pOp;
        else
            System.out.println("Not an arithmetic operation !");
    }
    
    public Expression getLHS () {
        return this.Lhs;
    }
    
    public Expression getRHS () {
        return this.Rhs;
    }
    
    public String getSign() {
        return this.Op;
    }
    
    public void accept(Visitor v)   {
        v.visit(this);
    }
}
