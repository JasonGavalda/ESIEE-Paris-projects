package question3;
import java.util.Stack;

public class Gardien
{
    private Stack<Memento> aMementoStk;

    public Gardien(){
        this.aMementoStk = new Stack<Memento>();
    } // Gardien()

    public Memento getMemento()
    {
        return this.aMementoStk.pop();
    } // getMemento()

    public void setMemento( final Memento pMemento )
    {
        this.aMementoStk.push( pMemento );
    } // setMemento(.)
} // Gardien