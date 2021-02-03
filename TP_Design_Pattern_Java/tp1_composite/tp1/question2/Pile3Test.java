package question2;

/**
 * Classe-test Pile3Test.
 *
 * @author  (votre nom)
 * @version (un numero de version ou une date)
 *
 * Les classes-test sont documentees ici :
 * http://junit.sourceforge.net/javadoc/junit/framework/TestCase.html
 * et sont basees sur le document © 2002 Robert A. Ballance intitule
 * «JUnit: Unit Testing Framework».
 *
 * Les objets Test (et TestSuite) sont associes aux classes a tester
 * par la simple relation yyyTest (e.g. qu'un Test de la classe Name.java
 * se nommera NameTest.java); les deux se retrouvent dans le meme paquetage.
 * Les "engagements" (anglais : "fixture") forment un ensemble de conditions
 * qui sont vraies pour chaque methode Test a executer.  Il peut y avoir
 * plus d'une methode Test dans une classe Test; leur ensemble forme un
 * objet TestSuite.
 * BlueJ decouvrira automatiquement (par introspection) les methodes
 * Test de votre classe Test et generera la TestSuite consequente.
 * Chaque appel d'une methode Test sera precede d'un appel de setUp(),
 * qui realise les engagements, et suivi d'un appel a tearDown(), qui les
 * detruit.
 */

public class Pile3Test extends junit.framework.TestCase
{
    public void test_Pile3_estPleine()
    {
        question2.Pile3 p = new question2.Pile3(3);

        try{
            p.empiler(3);  assertEquals(p.taille(),1);
            p.empiler(2);  assertEquals(p.taille(),2);
            p.empiler(1);  assertEquals(p.taille(),3);
        }catch(Exception e){
            fail();
        }
        assertEquals(true, p.estPleine());
        assertEquals(p.taille(),p.capacite());
        try{
            p.empiler(0);
            fail();
        }catch(Exception e){
            assertTrue(e instanceof question1.PilePleineException);
        }
    }

    public void test_Pile3_estVide()
    {
        question2.Pile3 p = new question2.Pile3(3);
        assertEquals(true, p.estVide());
        try{
            Object r = p.depiler();
            fail();
        }catch(Exception e){
            assertTrue(e instanceof question1.PileVideException);
        }
    }

    public void test_Pile3_toString() throws Exception
    {
        question2.Pile3 pile1 = new question2.Pile3(3);
        assertEquals("toString incorrect ? ","[]", pile1.toString());
        pile1.empiler(4);
        assertEquals("toString incorrect ? ","[4]", pile1.toString());
        pile1.empiler(5);
        assertEquals("toString incorrect ? ","[5, 4]", pile1.toString());
        pile1.empiler(3);
        assertEquals("toString incorrect ? ","[3, 5, 4]", pile1.toString());
    }

    public void test_Pile3_sommet() throws Exception
    {
        question2.Pile3 p = new question2.Pile3(3);
        assertEquals(true, p.estVide());
        p.empiler(new Integer(3));
        assertEquals(" sommet ?? ",new Integer(3), p.sommet());
        assertEquals(p.taille(),1);
        p.empiler(new Integer(5));
        assertEquals(" sommet ?? ",new Integer(5), p.sommet());
        assertEquals(" depiler ?? ",new Integer(5), p.depiler());
        assertEquals(" depiler ?? ",new Integer(3), p.depiler());
        assertEquals(p.taille(),0);
    }

    public void test_Pile3_TailleNegative()
    {
        try{
            question2.Pile3 p = new question2.Pile3(-3);
            assertEquals(p.CAPACITE_PAR_DEFAUT,p.capacite());
        }catch(Exception e){
            fail();
        }
    }

    public void test_Pile3_equals() throws Exception
    {
        question2.Pile3 p1 = new question2.Pile3(PileI.CAPACITE_PAR_DEFAUT);
        assertEquals(PileI.CAPACITE_PAR_DEFAUT, p1.capacite());
        p1.empiler(3); 
        p1.empiler(2); 
        p1.empiler(1);

        question2.Pile3 p2 = new question2.Pile3(PileI.CAPACITE_PAR_DEFAUT);
        assertEquals(PileI.CAPACITE_PAR_DEFAUT, p2.capacite());
        p2.empiler(3); 
        p2.empiler(2); 
        p2.empiler(1); 

        assertTrue("egalite de deux piles ? ", p1.equals(p2));
        assertTrue("egalite de deux piles ? ", p2.equals(p1));
        assertTrue("egalite de deux piles ? ", p1.equals(p1));

        p2.empiler(1); 
        assertFalse("egalite de deux piles ? ", p1.equals(p2));
        assertFalse("egalite de deux piles ? ", p2.equals(p1));
    }
} // Pile3Test
