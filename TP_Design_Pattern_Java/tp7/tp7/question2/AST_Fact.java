package question2;


import tp4.question1.*;
import tp4.question2.*;
import tp4.question3.*;


/**
 * Décrivez votre classe AST_Fact ici.
 * 
 * @author (votre nom) 
 * @version (un numéro de version ou une date)
 */
public class AST_Fact extends AST_Progr implements java.io.Serializable
{
      
/* construire l'AST du programme "WhileL style"  est à construire ci dessous
    xxx:=n ; 
    fact := 1 ; 
    while (~(xxx=0) ) do
       fact := fact * xxx ; xxx:= xxx-1; 
       ftp;
    afficher(fact);
remarque : on calcule ici fact(n) i.e. n est une donnée fournie au moment de l'exécution, d'où le constructeur suivant
 */
               public AST_Fact(int n){
                   m.ecrire("n" , n);
                }
        private Contexte m = new Memoire();
        private Variable n= new Variable(m , "n");    
      
        private Variable xxx = new Variable(m,"xxx");
        private Variable fact = new Variable(m,"fact",1);
               private Constante zero=new Constante(0);
               private Constante un=new Constante(1);
               
               private Instruction ast= new Sequence(new Sequence(
                                            new Affectation(xxx, n), new Affectation(fact, un)),
                                            new TantQue(
                                                new Non(new Egal(xxx, zero)), new Sequence(
                                                    new Affectation(fact, new Multiplication(fact, xxx)),
                                                    new Affectation(xxx, new Soustraction(xxx, un)))));
           
            public Contexte getMem(){
                return m;
            }
            
            public Instruction getAST(){
                return ast;
            }
           
}
