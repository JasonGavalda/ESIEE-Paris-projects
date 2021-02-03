package question2;


import tp4.question1.*;
import tp4.question2.*;
import tp4.question3.*;
/**
 * Décrivez votre classe AST_SOM_F ici.
 * 
 * @author (votre nom) 
 * @version (un numéro de version ou une date)
 */
public class AST_SOM_F extends AST_Progr  implements java.io.Serializable
{
/*
n=100;
som = 0 ; 
for(i=0 ; i<n ; i++){ (n> 0) {
           som := som+i ; }
 afficher(som);
 */
	    private Contexte m = new Memoire();
               public AST_SOM_F(int n){
                   m.ecrire("n" , n);
                }

      private Variable som=new Variable(m,"som") ;
      private Variable n=new Variable(m,"n");
      private Variable i=new Variable(m,"i");
      private Constante zero=new Constante(0);
      private Constante un=new Constante(1);
  
      private  Instruction ast=
                         new Sequence(
                                new Pour(
                                        new Affectation(i , zero),
                                        new  Inf(i , n),
                                        new Affectation(som , new  Addition(som , i)),
                                        new Affectation(i , un)
                                         ),                                                
                                new Afficher(som)
              );

            public Contexte getMem(){
                return m;
            }
            
            public Instruction getAST(){
                return ast;
            }
               
}
