package question2;


import tp4.question1.*;
import tp4.question2.*;
import tp4.question3.*;


public class testDesSer extends junit.framework.TestCase{
  

	public void testSimple(){
  try{
		JAVASerialiseDeserialise.serialjava(new AST_Aff(), "test.ser");
		
		AST_Aff p = (AST_Aff)JAVASerialiseDeserialise.deserialjava("test.ser");
		assertNotNull(p);
		
    Contexte m = new Memoire();
    VisiteurExpression<Integer> ve = new VisiteurEvaluation(m);
    VisiteurExpressionBooleenne<Boolean> vb = new VisiteurBoolEvaluation(ve);
    VisiteurInstruction<Contexte> vi = new VisiteurInstEvaluation(ve,vb);
    
    Instruction ast=p.getAST(); //récupération de l'AST
    ast.accepter(vi); //évaluation proprement dite de l'AST
    assertTrue(" ce n'est pas le résultat attendu ...", m.lire("y")==12);
 	  }catch(Exception e){
        fail("exception inattendue : " + e.getMessage());
      }
	}
}

