package tp4.question3;

import java.io.*;
import org.jdom.*;
import org.jdom.output.*;
import tp4.question1.*;
import tp4.question2.*;
/**
 * Visisteur d'instruction, chaque classe concrète possède une implémentation de la visite
 * 
 */
public class VisiteurInstJDOM extends VisiteurInstruction<Element>{
  private VisiteurExpression<Element> vi;
  private VisiteurExpressionBooleenne<Element> vb;
  
  /** Création d'un visiteur d'instructions
   * @param vi le visiteur d'expressions arithmétiques
   * @param vb le visiteur d'expression booléennes
   */
  public VisiteurInstJDOM(VisiteurExpression<Element> vi, VisiteurExpressionBooleenne<Element> vb){
    this.vi = vi;
    this.vb = vb;
  }

  /** obtention du contexte, ici celui du visiteur d'expression arithmétiques 
   * @return le contexte ici de vi(le visiteur d'expression)
   */
  public Contexte contexte(){
    return this.vi.contexte();
  }

  /** Visite d'une instance de la classe Affectation.
   * 
   * 
   * @param a  une affectation
   * @return a := exp 
   */
  public Element visite(Affectation a){
      Element affectation = new Element("Affectation");
      affectation.addContent(a.v().accepter(this.vi));
      affectation.addContent(a.exp().accepter(this.vi));
      return affectation;
  }
  
  /** Visite d'une séquence seq(I1,I2) <br>
   * @param seq  une séquence
   * @return i1;i2
   */
  public Element visite(Sequence seq){
      Element sequence = new Element("Sequence");
      sequence.addContent(seq.i1().accepter(this));
      sequence.addContent(seq.i2().accepter(this));
    return sequence;
  }
  
  public Element visite(Selection sel){
      Element selection = new Element("Selection");
      selection.addContent(sel.cond().accepter(this.vb));
      selection.addContent(sel.i1().accepter(this));
      if(sel.i2() != null) selection.addContent(sel.i2().accepter(this));
    
      
    return selection;
  }

   public Element visite(TantQue tq){  
       Element tantque = new Element("TantQue");
       tantque.addContent(tq.cond().accepter(this.vb));
       tantque.addContent(tq.i1().accepter(this));
       return tantque;
    }
  


   public Element visite(Pour bouclepour){
       Element pour = new Element("Pour");
       pour.addContent(bouclepour.init().accepter(this));
       pour.addContent(bouclepour.cond().accepter(this.vb));
       pour.addContent(bouclepour.inc().accepter(this));
       pour.addContent(bouclepour.i1().accepter(this));
       return pour;
       
   }

   public Element visite(Afficher a){
      Element afficher = new Element("Afficher");
      afficher.addContent(a.exp().accepter(this.vi));
      return afficher;
   }
   
    public Element visite(Assertion a){
      Element assertion = new Element("Assertion");
      assertion.addContent(a.cond().accepter(this.vb));
      return assertion;
   }
  
//   public Element visite(Nulle n){
//       Element nulle = new Element("Nulle");
//        return nulle;
//   }

}


