package question3;


import tp4.question1.*;
import tp4.question2.*;
import tp4.question3.*;
import question2.*;

import org.jdom.*;
import org.jdom.output.*;
import org.jdom.input.*;

import java.util.Random;
import java.io.*;


public class testXML2AST extends junit.framework.TestCase
{
    private String nomDuFichier;
    
     protected void setUp(){
       nomDuFichier = "test_tp9q2_XML2AST"+ new Random().nextInt(100000)  + ".xml";
     }

     protected void tearDown(){
       try{new File(nomDuFichier).delete();}catch(Exception e){}
    }
    

  public void test_XML2AST(){

    try{
      
      SerialiseDeserialiseAST_XML.serialAst2xml(new AST_Fact(10),nomDuFichier);
      Element astXML=SerialiseDeserialiseAST_XML.deserialXml(nomDuFichier);
   
      Contexte m = new Memoire();
      Instruction inst=XML2AST.xmlInst2ast(m,astXML);
      
      
               //Contexte m=(Memoire)inst.getMem(); // récupération du contexte
           //construction des Visiteurs nécessaires
           // visteur d'évaluation
         m.ecrire("n",5);
         VisiteurExpression<Integer> ve = new VisiteurEvaluation(m);
  	    VisiteurExpressionBooleenne<Boolean> vb = new VisiteurBoolEvaluation(ve);
  	    VisiteurInstruction<Contexte> vi = new VisiteurInstEvaluation(ve,vb);
  	// Visiteur "toString()" pour la visualisation des résultats    
  	    VisiteurExpression<String> ves = new VisiteurInfixe(m);
  	    VisiteurExpressionBooleenne<String> vbs = new VisiteurBoolToString(ves);
  	    VisiteurInstruction<String> vs = new VisiteurInstToString(ves,vbs);
  	    inst.accepter(vi);
  //System.out.println(m + "  " + (String)inst.accepter(vs));
  
   	  assertTrue(inst.accepter(vs) + " ne donne pas le résultat attendu ...", m.lire("fact")== fact(5));
   	  
 	  
    }catch (Exception e){
     fail("exception inattendue !!! : " + e.getMessage()); 
   }
  }
  
     private static int fact(int n){
    if(n==0) return 1;
    else return n*fact(n-1);
  }
}
