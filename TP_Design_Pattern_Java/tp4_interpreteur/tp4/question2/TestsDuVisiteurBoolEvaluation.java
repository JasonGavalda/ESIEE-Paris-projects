package question2;

import question1.*;

public class TestsDuVisiteurBoolEvaluation extends junit.framework.TestCase {

    protected Contexte m;
    protected VisiteurExpressionBooleenne<Boolean> veb;

    public void setUp() {
        m = new Memoire();
        veb = new VisiteurBoolEvaluation(new VisiteurEvaluation(m));
    }

    public void testVisiteurBoolEvaluation() {
        assertTrue(new Vrai().accepter(veb));
        assertFalse(new Faux().accepter(veb));
        assertTrue(new Sup(new Constante(5), new Constante(3)).accepter(veb));
        assertTrue(new Egal(new Addition(new Constante(5), new Constante(3)), new Constante(8)).accepter(veb));
        assertTrue(new Inf(new Constante(5), new Constante(7)).accepter(veb));
        assertTrue(new Inf(new Constante(5),
                    new Addition(new Constante(5), new Constante(1))).accepter(veb));
        assertTrue(new Ou(new Sup(new Constante(5), new Constante(6)),
                    new Inf(new Constante(5), new Constante(6))).accepter(veb));
        assertTrue(new Et(new Sup(new Constante(6), new Constante(5)),
                    new Inf(new Constante(5), new Constante(6))).accepter(veb));
        assertTrue(new Non(new Faux()).accepter(veb));
    }
    
}
