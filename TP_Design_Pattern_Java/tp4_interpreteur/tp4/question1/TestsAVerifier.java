package question1;

public class TestsAVerifier extends junit.framework.TestCase{
    private Memoire m;
    private Variable i,j;
    private VisiteurExpression<Integer> ve;
    private VisiteurExpression<String>  vp, vi;

    public void setUp(){
        m  = new Memoire();
        i  = new Variable(m,"i",3);
        j = new Variable(m,"j",5);
        ve = new VisiteurEvaluation( m);
        vi = new VisiteurInfixe( m);
        vp = new VisiteurPostfixe( m);
        assertNotNull(i);assertNotNull(j);
        assertNotNull(ve);assertNotNull(vp);assertNotNull(vi);
    }

    public void testUneConstante(){
        try{
            assertEquals(m + "lecture mémoire ?, i==3 ?", 3, m.lire("i").intValue());
            assertEquals(m  + "lecture mémoire ?, j==5 ?", 5, m.lire("j").intValue());

            Expression expr = new Constante(3);
            assertEquals(" une Constante : 3 ?", 3, expr.accepter(ve).intValue());
        }catch(Exception e){
            fail(" exception inattendue ???" + e.getClass().getName() + e.getMessage());
        }
    }

    public void testUneVariable(){
        try{
            assertEquals(m + "lecture mémoire ?, i==3 ?", 3, m.lire("i").intValue());
            assertEquals(m  + "lecture mémoire ?, j==5 ?", 5, m.lire("j").intValue());
            m.ecrire("z",10);
            assertEquals(m  + "écriture mémoire ?, z==10 ?", 10, m.lire("z").intValue());
        }catch(Exception e){
            fail(" exception inattendue ???" + e.getClass().getName() + e.getMessage());
        }
    }


    public void testUneAddition(){
        try{
            // i = 3; (i + 2) + i
            Expression expr = new Addition(new Addition(i, new Constante(2)),i);
            assertEquals(m + " (i + 2) + i != 8? ?? ",8,expr.accepter(ve).intValue());
        }catch(StackOverflowError e){
            fail(" StackOverflowError, appel récursif sans fin de " + m + " (i + 2) + i ???");
        }catch(Exception e){
            fail(" exception inattendue ???" + e.getClass().getName() + e.getMessage());
        }
    }

    public void testMultiplication(){
        try{
            assertEquals(m + " i * j != 15 ?",15,new Multiplication(i,j).accepter(ve).intValue());
        }catch(StackOverflowError e){
            fail(" StackOverflowError, appel récursif sans fin de " + m + " i * j ???");
        }catch(Exception e){
            fail(" exception inattendue ???" + e.getClass().getName() + e.getMessage());
        }
    }


    public void testSoustraction(){
        try{
            assertEquals(m + " i - j != -2 ?", -2, new Soustraction(i,j).accepter(ve).intValue());
        }catch(StackOverflowError e){
            fail(" StackOverflowError, appel récursif sans fin de " + m + " i - j ???");
        }catch(Exception e){
            fail(" exception inattendue ???" + e.getClass().getName() + e.getMessage());
        }	  
    }


    public void testDivision(){
        try{
            assertEquals(m.lire("j"),new Integer(5));
            assertEquals(m.lire("i"),new Integer(3));
            assertEquals(m + " i*j/i != 5 ???", 5, new Division(new Multiplication(i,j),i).accepter(ve).intValue());
        }catch(StackOverflowError e){
            fail(" StackOverflowError, appel récursif sans fin de " + m + " i*j/i ???");  	
        }catch(Exception e){
            fail(" exception inattendue ???" + e.getClass().getName() + e.getMessage());
        }
        try{
            new Division(i,new Constante(0)).accepter(ve);
            fail("division par zéro ? possible ");
        }catch(ArithmeticException ae){
        }catch(Exception e){
            fail(" exception inattendue ???" + e.getClass().getName() + e.getMessage());
        }
    }

   
    public void testVisiteurInfixe(){
        try{
            Expression expr = new Addition(new Constante(3), new Constante(2));
            assertEquals("(3 + 2)", expr.accepter(vi));
            expr = new Addition(expr, new Constante(2));
            assertEquals("((3 + 2) + 2)", expr.accepter(vi));
            assertEquals("{i=3, j=5}", m.toString());
            expr = new Soustraction(expr, expr);
            //System.out.println(expr.accepter(vi));
            assertEquals("(((3 + 2) + 2) - ((3 + 2) + 2))", expr.accepter(vi));
        }catch(Exception e){
            fail(" exception inattendue ???" + e.getClass().getName() + e.getMessage());
        }
    }

    public void testVisiteurPostfixe(){
        try{
            Expression expr = new Addition(new Constante(3), new Constante(2));
            assertEquals("(3,2)+", expr.accepter(vp));
            expr = new Addition(expr, new Constante(2));
            assertEquals("((3,2)+,2)+", expr.accepter(vp));
            assertEquals("{i=3, j=5}",m.toString());
            expr = new Soustraction(expr, expr);
            assertEquals("(((3,2)+,2)+,((3,2)+,2)+)-", expr.accepter(vp));
        }catch(Exception e){
            fail(" exception inattendue ???" + e.getClass().getName() + e.getMessage());
        }
    }
}
