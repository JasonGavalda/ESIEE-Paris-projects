package question3;


import tp4.question1.*;
import tp4.question2.*;
import tp4.question3.*;
import question2.*;
import java.io.*;
import org.jdom.*;
import org.jdom.output.*;

/**
 * Décrivez votre classe XML2AST ici.
 * 
 * @author (votre nom) 
 * @version (un numéro de version ou une date)
 */
public class XML2AST
{

      public static Instruction  xmlInst2ast(Contexte m, Element element) throws Exception{
         
         
         String nomInstruction=element.getName();
         if(nomInstruction=="Affectation"){
             Element el1 = (Element)element.getChildren().get(0);
             Element el2 = (Element)element.getChildren().get(1);
             Variable var=new Variable(m ,el1.getText());
             Expression exp=xmlExp2ast(m,el2);
             return new Affectation(var , exp);
            }
         if(nomInstruction=="Sequence"){
             Element el1 = (Element)element.getChildren().get(0);
             Element el2 = (Element)element.getChildren().get(1);
             Instruction i1=xmlInst2ast(m,el1);
             Instruction i2=xmlInst2ast(m,el2);
              return new Sequence(i1 , i2);
            }
         if(nomInstruction=="Selection"){
             Element el1 = (Element)element.getChildren().get(0);
             Element el2 = (Element)element.getChildren().get(1);
             ExpressionBooleenne b1=xmlExpBool2ast(m,el1);
             Instruction i1=xmlInst2ast(m,el2);
             if(element.getChildren().size()==3){
                 Element el3 = (Element)element.getChildren().get(2);
                 Instruction i2=xmlInst2ast(m,el3);
                 return new Selection(b1 , i1 , i2);
                }
              return new Selection(b1 , i1);
            }
         if(nomInstruction=="TantQue"){
             Element el1 = (Element)element.getChildren().get(0);
             Element el2 = (Element)element.getChildren().get(1);
             ExpressionBooleenne b1=xmlExpBool2ast(m,el1);
             Instruction i1=xmlInst2ast(m,el2);
              return new TantQue(b1 , i1);
            }

         if(nomInstruction=="Pour"){
             Element el1 = (Element)element.getChildren().get(0);
             Element el2 = (Element)element.getChildren().get(1);
             Element el3 = (Element)element.getChildren().get(2);
             Element el4 = (Element)element.getChildren().get(3);
             ExpressionBooleenne b1=xmlExpBool2ast(m,el2);
             Instruction init=xmlInst2ast(m,el1);
             Instruction i1=xmlInst2ast(m,el3);
             Instruction inc=xmlInst2ast(m,el4);
              return new Pour(init , b1 , i1 , inc);
            }
         if(nomInstruction=="Afficher"){
             Element el1 = (Element)element.getChildren().get(0);
             Expression e1=xmlExp2ast(m,el1);
             return new Afficher(e1);
            }

            throw new RuntimeException("Erreur dans instruction ...");
        }
        
         public static Expression  xmlExp2ast(Contexte m, Element element) throws Exception{
             
                String nomExpr=element.getName();
// a completer
// a completer
// a completer
// a completer
            throw new RuntimeException("Erreur dans expression ...");
           
                }
                
                
              public static ExpressionBooleenne  xmlExpBool2ast(Contexte m,Element element) throws Exception{
                String nomExprBool=element.getName();
                
// a completer
// a completer
// a completer
// a completer
            throw new RuntimeException("Erreur dans expression booleenne...");

                }
}

