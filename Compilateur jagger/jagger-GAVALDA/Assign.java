public class Assign extends Statement
{
    private Variable aVar;
    private Expression aExp;
    
    public Assign(Variable pVar, Expression pExp)   {
        this.aVar = pVar;
        this.aExp = pExp;
    }
    
    public Variable getVariable()   {
        return this.aVar;
    }
    
    public Expression getExpression()   {
        return this.aExp;
    }
    
    @Override
    public void accept(Visitor v)   {
        v.visit(this);
    }
}
