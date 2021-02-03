package question3;

import java.io.*;

import question1.*;
import question2.*;

/**
 * 
 */
public class VisiteurInstToJava extends VisiteurInstruction<String> {

	private final static int TAB = 2;
	private static final String lineSeparator = System.getProperties().getProperty("line.separator");

	private VisiteurExpression<String> vi;
	private VisiteurExpressionBooleenne<String> vb;

	private int tabulations;

	/**
	 * Création d'un visiteur d'instructions
	 * 
	 * @param vi
	 *            le visiteur d'expressions arithmétiques
	 * @param vb
	 *            le visiteur d'expression booléennes
	 * @param tabulations
	 *            tabulations initiales
	 */
	public VisiteurInstToJava(VisiteurExpression<String> vi, VisiteurExpressionBooleenne<String> vb, int tabulations) {
		this.vi = vi;
		this.vb = vb;
		this.tabulations = tabulations;
	}

	/**
	 * Création d'un visiteur d'instructions
	 * 
	 * @param vi
	 *            le visiteur d'expressions arithmétiques
	 * @param vb
	 *            le visiteur d'expression booléennes
	 */
	public VisiteurInstToJava(VisiteurExpression<String> vi, VisiteurExpressionBooleenne<String> vb) {
		this(vi, vb, 0);
	}

	/**
	 * obtention du contexte, ici celui du visiteur d'expression arithmétiques
	 * 
	 * @return le contexte ici de vi(le visiteur d'expression)
	 */
	public Contexte contexte() {
		return this.vi.contexte();
	}

	/**
	 * Visite d'une instance de la classe Affectation.
	 * 
	 * 
	 * @param a
	 *            une affectation
	 * @return a := exp
	 */
	public String visite(Affectation a) {
		return a.v().accepter(this.vi) + "=" + a.exp().accepter(this.vi);
	}

	/**
	 * Visiste d'une séquence seq(I1,I2) <br>
	 * 
	 * @param seq
	 *            une séquence
	 * @return i1;i2
	 */
	public String visite(Sequence seq) {
		return seq.i1().accepter(this) + " ; " + seq.i2().accepter(this);
	}

	public String visite(Selection sel) {
		return null; // à compléter
	}

	public String visite(TantQue tq) {
		return null; // à compléter
	}

	public String visite(Pour pour) {

		return null; // à compléter
	}

	public String visite(Afficher a) {
		return null; // à compléter
	}

	public String visite(Assertion a) {
		return null; // à compléter
	}

	private String tab(int n) {
		String str = new String();

		str = str + lineSeparator;
		for (int i = 0; i < this.tabulations + n; i++) {
			str = str + " ";
		}
		this.tabulations += n;
		return str;
	}

}
