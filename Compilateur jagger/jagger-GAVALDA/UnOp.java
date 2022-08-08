    
/**
 * Décrivez votre classe UnOp ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class UnOp extends Expression
{
    Expression aExp;
    String aSign;
    
    public UnOp(Expression pExp, String pSign)  {
        this.aExp = pExp;
        this.aSign = pSign;
    }
    
    public Expression getExpression(){
        return this.aExp;
    }
    
    public String getSign(){
        return this.aSign;
    }
    
    public void accept(Visitor v)   {
        v.visit(this);
    }
}
