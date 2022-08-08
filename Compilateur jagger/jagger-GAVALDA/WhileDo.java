
public class WhileDo extends Statement
{
    private Expression aCond;
    private Statement aDo;
    
    public WhileDo(Expression pCond, Statement pDo) {
        this.aCond = pCond;
        this.aDo = pDo;
    }
    
    public Expression getCond() {
        return this.aCond;
    }
    
    public Statement getDo()    {
        return this.aDo;
    }
    
    @Override
    public void accept(Visitor v)   {
        v.visit(this);
    }
}
