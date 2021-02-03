package question2;


import tp4.question1.*;
import tp4.question2.*;
import tp4.question3.*;

import java.io.*;


/**
 * Décrivez votre classe TestSerialJava ici.
 * 
 * @author (votre nom) 
 * @version (un numéro de version ou une date)
 */
public class TestFactoriel   extends junit.framework.TestCase{

   private static int fact(int n){
    if(n==0) return 1;
    else return n*fact(n-1);
  }
  
    public void testFactoriel(){
    try{     AST_Fact p = new AST_Fact(10);
         Contexte m=p.getMem(); // récupération du contexte
         //construction des Visiteurs nécessaires
         // visteur d'évaluation
         	    VisiteurExpression<Integer> ve = new VisiteurEvaluation(m);
	    VisiteurExpressionBooleenne<Boolean> vb = new VisiteurBoolEvaluation(ve);
	    VisiteurInstruction<Contexte> vi = new VisiteurInstEvaluation(ve,vb);
	// Visiteur "toString()" pour la visualisation des résultats    
	    VisiteurExpression<String> ves = new VisiteurInfixe(m);
	    VisiteurExpressionBooleenne<String> vbs = new VisiteurBoolToString(ves);
	    VisiteurInstruction<String> vs = new VisiteurInstToString(ves,vbs);

       //m.ecrire("n" , 10);// initialisation  de n à 10
       Instruction ast=p.getAST(); //récupération de l'AST
 	  ast.accepter(vi); //évaluation proprement dite de l'AST
 	   	  //System.out.println("contenu de la mémoire en fin  : " + m);
 	  assertTrue(ast.accepter(vs) + " ne donne pas le résultat attendu ...", m.lire("fact")== fact(10));
 	  //System.out.println("contenu de la mémoire en fin  : " + m);
 	   	 }catch (Exception e){
     fail("exception inattendue !!! : " + e.getMessage()); 
   }
	}   
}
