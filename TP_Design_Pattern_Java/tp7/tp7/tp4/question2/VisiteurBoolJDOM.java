package tp4.question2;

import java.io.*;
import org.jdom.*;
import org.jdom.output.*;

import tp4.question1.VisiteurExpression;
import tp4.question2.*;
 
public class VisiteurBoolJDOM extends VisiteurExpressionBooleenne<Element>{
  
      private VisiteurExpression<Element> ve;
  
     public VisiteurBoolJDOM(VisiteurExpression<Element> ve){
          this.ve = ve;
     }
  
     public Element visite(Vrai v){
         return  new Element("Vrai");
        }
  
      public Element visite(Faux f){
          return  new Element("Faux");
        }
    
   public Element visite(Non n){
       Element non=new Element("Non");
       non.addContent(n.bop().accepter(this));
       return non;
   }
   
   public Element visite(Ou or){
       Element ou=new Element("Ou");
       ou.addContent(or.bop1().accepter(this));
       ou.addContent(or.bop2().accepter(this));
       return ou;
 
   }
   
   public Element visite(Et and){
       Element et=new Element("Et");
       et.addContent(and.bop1().accepter(this));
       et.addContent(and.bop2().accepter(this));
       return et;
   }
   
   public Element visite(Sup sup){
       Element superieur=new Element("Sup");
       superieur.addContent(sup.op1().accepter(this.ve));
       superieur.addContent(sup.op2().accepter(this.ve));
       return superieur;
    }
   
   public Element visite(Egal eg){
       Element egal=new Element("Egal");
       egal.addContent(eg.op1().accepter(this.ve));
       egal.addContent(eg.op2().accepter(this.ve));
       return egal;
   }

   public Element visite(Inf inf){
       Element inferieur=new Element("Inf");
       inferieur.addContent(inf.op1().accepter(this.ve));
       inferieur.addContent(inf.op2().accepter(this.ve));
       return inferieur;
   }
}
