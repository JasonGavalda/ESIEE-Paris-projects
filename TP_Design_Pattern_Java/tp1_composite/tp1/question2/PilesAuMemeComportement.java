package question2;

public class PilesAuMemeComportement extends junit.framework.TestCase
{
	private question2.Pile2 p2;
	private question2.Pile3 p3;
  
    protected void setUp(){
		p2 = new question2.Pile2(PileI.CAPACITE_PAR_DEFAUT);
		p3 = new question2.Pile3(PileI.CAPACITE_PAR_DEFAUT);
	}

	public void test_meme_comportement() throws Exception{ 

		  p3.empiler("aze");
		  p2.empiler("aze");

		  assertEquals("p2.capacite() == p3.capacite() ??",p2.capacite(), p3.capacite());
		  assertEquals("[aze]", p2.toString());
		  assertEquals("p2.toString()!= p3.toString() ?? ",p2.toString(), p3.toString());
		  assertEquals("p2.sommet() != p3.sommet() ??? ", p2.sommet(), p3.sommet());
		  assertEquals("p2.estVide()!= p3.estVide() ??? ", p2.estVide(), p3.estVide());
		  assertEquals("p2.estPleine()!=  p3.estPleine() ??? ",p2.estPleine(), p3.estPleine());

		  String s = (String) p2.depiler();
		  assertEquals(" différence après avoir dépilé ?? ",s, (String) p3.depiler());
		  assertEquals("p2.estVide()!= p3.estVide() ??? ", p2.estVide(), p3.estVide());
		  assertEquals("p2.estPleine()!=  p3.estPleine() ??? ",p2.estPleine(), p3.estPleine());
		  assertEquals(" différence sur la taille() ?? ",p2.taille(), p3.taille());


		  p3.empiler("azerty");
		  p2.empiler("azerty");
		  assertEquals("p2.sommet() != p3.sommet() ??? ", p2.sommet(), p3.sommet());
		  assertEquals("p2.estVide()!= p3.estVide() ??? ", p2.estVide(), p3.estVide());
		  assertEquals("p2.estPleine()!=  p3.estPleine() ??? ",p2.estPleine(), p3.estPleine());
		  assertEquals(" différence sur la taille() ?? ",p2.taille(), p3.taille());

		  p3.empiler("azer");
		  p2.empiler("azer");
		  assertEquals("p2.toString() != p3.toString() ?? ",p2.toString(), p3.toString());
		  assertEquals("p2.sommet() != p3.sommet() ??? ", p2.sommet(), p3.sommet());
		  assertEquals("p2.estVide()!= p3.estVide() ??? ", p2.estVide(), p3.estVide());
		  assertEquals("p2.estPleine()!=  p3.estPleine() ??? ",p2.estPleine(), p3.estPleine());
		  assertEquals(" différence sur la taille() ?? ",p2.taille(), p3.taille());

		  p3.empiler("azer");
		  p2.empiler("azer");
		  assertEquals("p2.toString() != p3.toString() ?? ",p2.toString(), p3.toString());
		  assertEquals("p2.sommet() != p3.sommet() ??? ", p2.sommet(), p3.sommet());
		  assertEquals("p2.estVide()!= p3.estVide() ??? ", p2.estVide(), p3.estVide());
		  assertEquals("p2.estPleine()!=  p3.estPleine() ??? ",p2.estPleine(), p3.estPleine());
		  assertEquals(" différence sur la taille() ?? ",p2.taille(), p3.taille());

		  p3.depiler();
		  p2.depiler();
		  assertEquals("p2.toString() != p3.toString() ?? ",p2.toString(), p3.toString());
		  assertEquals("p2.sommet() != p3.sommet() ??? ", p2.sommet(), p3.sommet());
		  assertEquals("p2.estVide()!= p3.estVide() ??? ", p2.estVide(), p3.estVide());
		  assertEquals("p2.estPleine()!=  p3.estPleine() ??? ",p2.estPleine(), p3.estPleine());
		  assertEquals(" différence sur la taille() ?? ",p2.taille(), p3.taille());

	}
}
