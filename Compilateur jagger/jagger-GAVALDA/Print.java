
/**
 * Décrivez votre classe Print ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Print extends Statement
{
    Expression aExp;
    
    public Print(Expression pExp)   {
        this.aExp = pExp;    
    }
    
    public Expression getExpression()   {
        return this.aExp;
    }
    
    public void accept(Visitor v)   {
        v.visit(this);
    }
    
}
