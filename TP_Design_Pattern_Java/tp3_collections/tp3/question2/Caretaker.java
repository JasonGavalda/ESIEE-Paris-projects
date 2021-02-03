package question2;

import java.util.Stack;

public class Caretaker
{
    public Stack<Memento> savedMementos;
    public Caretaker() {this.savedMementos = new Stack<Memento>();}
    
    public void addMemento(Memento m) {
        this.savedMementos.push(m);}
    
    public Memento getMemento() {return this.savedMementos.pop();}
}
