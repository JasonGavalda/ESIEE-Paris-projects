package tp4.question1;


import java.io.*;
import org.jdom.*;
import org.jdom.output.*;

 
public class VisiteurJDOM extends VisiteurParDefaut<Element>{   

  private  Contexte c;
  
  public VisiteurJDOM(Contexte c){
    this.c =c;
  }
    public  Element visite(Constante co){
        Element cste=new Element("Constante");
        this.c.ecrire("Constante", co.valeur());
        cste.addContent(this.c.toString());
        return cste;
    }
    
  public Element visite(Variable v){
        Element var=new Element("Variable");
        int valeur = this.c.lire(v.nom());
        var.addContent(this.c.toString());
        return var;
  }
  
  public Element visite(Division d){
        Element div=new Element("Division");
        this.c.ecrire("Division.op1", Integer.parseInt(d.op1().toString()));
        this.c.ecrire("Division.op2", Integer.parseInt(d.op2().toString()));
        div.addContent(this.c.toString());
        return div;
  }
  
  public Element visite(Addition a){
        Element add=new Element("Addition");
        this.c.ecrire("Addition.op1", Integer.parseInt(a.op1().toString()));
        this.c.ecrire("Addition.op2", Integer.parseInt(a.op2().toString()));
        add.addContent(this.c.toString());
        return add;
  }
  public Element visite(Multiplication m){
        Element mul=new Element("Multiplication");
        this.c.ecrire("Multiplication.op1", Integer.parseInt(m.op1().toString()));
        this.c.ecrire("Multiplication.op2", Integer.parseInt(m.op2().toString()));
        mul.addContent(this.c.toString());
        return mul;
  }
  public Element visite(Soustraction s){
        Element sous=new Element("Soustraction");
        this.c.ecrire("Soustraction.op1", Integer.parseInt(s.op1().toString()));
        this.c.ecrire("Soustraction.op2", Integer.parseInt(s.op2().toString()));
        sous.addContent(this.c.toString());
        return sous;
  }
  
  public Contexte contexte(){return c;}
}
