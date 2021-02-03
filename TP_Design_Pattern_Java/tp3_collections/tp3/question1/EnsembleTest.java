package question1;

public class EnsembleTest extends junit.framework.TestCase
{
    // Definissez ici les variables d'instance necessaires a vos engagements;
    // Vous pouvez egalement les saisir automatiquement du presentoir
    // a l'aide du menu contextuel "Presentoir --> Engagements".
    // Notez cependant que ce dernier ne peut saisir les objets primitifs
    // du presentoir (les objets sans constructeur, comme int, float, etc.).

    Ensemble <Integer> E;
    Ensemble <Integer> F;
    
    /**
     * Il ne vous reste plus qu'a definir une ou plusieurs methodes de test.
     * Ces methodes doivent verifier les resultats attendus a l'aide d'assertions
     * assertTrue(<boolean>).
     * Par convention, leurs noms devraient debuter par "test".
     * Vous pouvez ebaucher le corps grâce au menu contextuel "Enregistrer une methode de test".
     */

    public void testAdd()
    {
        //fail( "testAdd() non ecrite !" ); // ligne a commenter quand la fonction sera ecrite
        assertEquals(true, E.add(2));
        assertEquals(false, E.add(2));
    }
       
    public void testUnion()
    {
        //fail( "testUnion() non ecrite !" ); // ligne a commenter quand la fonction sera ecrite
        //  A titre d'exemple :
        E = new question1.Ensemble<Integer>();
        assertEquals(true, E.add(2));  assertEquals(true, E.add(3));
        
        F = new question1.Ensemble<Integer>();
        assertEquals(true, F.add(3));  assertEquals(true, F.add(4));
    
        question1.Ensemble<Integer> union = E.union(F);
        assertEquals(3, union.size());
        assertTrue(union.contains(2));
        assertTrue(union.contains(3));
        assertTrue(union.contains(4));
    }
    
    public void testInter()
    {
        //fail( "testInter() non ecrite !" ); // ligne a commenter quand la fonction sera ecrite
        E = new question1.Ensemble<Integer>();
        assertEquals(true, E.add(2));  assertEquals(true, E.add(3));
        
        F = new question1.Ensemble<Integer>();
        assertEquals(true, F.add(3));  assertEquals(true, F.add(4));
    
        question1.Ensemble<Integer> inter = E.inter(F);
        assertEquals(1, inter.size());
        assertFalse(inter.contains(2));
        assertTrue(inter.contains(3));
        assertFalse(inter.contains(4));
    }
    
    public void testDiff()
    {
        //fail( "testDiff() non ecrite !" ); // ligne a commenter quand la fonction sera ecrite
        E = new question1.Ensemble<Integer>();
        assertEquals(true, E.add(2));  assertEquals(true, E.add(3));
        
        F = new question1.Ensemble<Integer>();
        assertEquals(true, F.add(3));  assertEquals(true, F.add(4));
    
        question1.Ensemble<Integer> diff = E.diff(F);
        assertEquals(1, diff.size());
        assertTrue(diff.contains(2));
        assertFalse(diff.contains(3));
        assertFalse(diff.contains(4));
    }
    
    public void testDiffSym()
    {
        //fail( "testDiffSym() non ecrite !" ); // ligne a commenter quand la fonction sera ecrite
        E = new question1.Ensemble<Integer>();
        assertEquals(true, E.add(2));  assertEquals(true, E.add(3));
        
        F = new question1.Ensemble<Integer>();
        assertEquals(true, F.add(3));  assertEquals(true, F.add(4));
    
        question1.Ensemble<Integer> diffSym = E.diffSym(F);
        assertEquals(2, diffSym.size());
        assertTrue(diffSym.contains(2));
        assertFalse(diffSym.contains(3));
        assertTrue(diffSym.contains(4));
    }
 
    /**
     * Met en place les engagements.
     *
     * Methode appelee avant chaque appel de methode de test.
     */
    protected void setUp() throws java.lang.Exception
    {
        this.E = new question1.Ensemble<Integer>();
        this.F = new question1.Ensemble<Integer>();
    }

    /**
     * Supprime les engagements
     *
     * Methode appelee après chaque appel de methode de test.
     */
    protected void tearDown() throws java.lang.Exception
    {
        this.E = null;
        this.F = null;
    }

} // EnsembleTest
