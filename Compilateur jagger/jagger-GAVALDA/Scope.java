import java.util.ArrayList;
import java.util.HashMap;

public class Scope extends Statement
{
    private Scope aParent;
    private HashMap<String, Variable> aVariables;
    private ArrayList<Statement> aStatementsList;

    public Scope()    {
        this.aVariables = new HashMap<String, Variable>();
        this.aStatementsList = new ArrayList<>(); 
    }
    
    public Scope(Scope pS)  {
        this.aParent = pS;
        this.aVariables = new HashMap<String, Variable>();
        this.aStatementsList = new ArrayList<>();
    }

    public Scope getParent()    {
        return this.aParent;   
    }
    
    public void addStatement(Statement pSt) {
        this.aStatementsList.add(pSt);
    }

    public ArrayList<Statement> getStatements() {
        return aStatementsList;
    }

    public void addVariable(Variable pVar)   {
        String vName = pVar.getName();
        if (this.getVariable(vName) == null)
            this.aVariables.put(vName, pVar);
        else
            throw new RuntimeException("Variable already declared !");
    }

    public void changeValue(String pName, int pValue)    {
        this.aVariables.get(pName).setValue(pValue);
    }
    
    public boolean isInScope(String pName)    {
        if (aVariables.containsKey(pName))
            return true;
        else if (this.aParent != null)
            return this.aParent.isInScope(pName);
        else
            return false;   
    }
    
    public Variable getVariable(String pName){
        if (aVariables.containsKey(pName))
            return this.aVariables.get(pName);
        else if (this.aParent != null)
            return this.aParent.getVariable(pName);
        else
            return null;   
    }

    @Override
    public void accept (Visitor v)  {
        v.visit(this);
    }
}
