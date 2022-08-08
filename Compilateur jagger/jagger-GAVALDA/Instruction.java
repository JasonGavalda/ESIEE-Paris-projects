
public class Instruction extends Statement
{
    private Expression aExp;
    
    public Instruction(Expression pExp) {
        this.aExp = pExp;
    }
    
    public Expression getExpression()   {
        return this.aExp;
    }
    
    @Override
    public void accept(Visitor v)    {
        v.visit(this);
    }
}
