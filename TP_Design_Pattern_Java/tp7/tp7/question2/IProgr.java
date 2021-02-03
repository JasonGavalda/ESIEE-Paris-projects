package question2;


import tp4.question1.*;
//import tp6.question2.*;
import tp4.question3.*;

/**
 * Décrivez votre interface IProgr ici.
 * 
 * @author  (votre nom) 
 * @version (un numéro de version ou une date)
 */

public interface IProgr extends java.io.Serializable{
      Contexte getMem();
      Instruction getAST();
}
