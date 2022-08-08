
public class ID extends Expression
{
    private int aValue;
    private String aName;

    public ID(String pName) {
        this.aValue = 0;
        this.aName = pName;
        this.aType = Type.T_Int;
    }

    public int getValue() {
        return aValue;
    }

    public void setValue(int v){
        this.aValue = v;
    }

    public String getName() {
        return this.aName;
    }
    
    @Override
    public void accept(Visitor v)    {
        v.visit(this);   
    }
}
