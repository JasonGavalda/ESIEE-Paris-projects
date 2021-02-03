package question2;

import java.util.*;
import question1.*;

public class TestsDesVisiteurs extends junit.framework.TestCase
{

  public void testACompleter()
  {
      try{
          GroupeDeContributeurs g = new GroupeDeContributeurs("g");
          g.ajouter(new Contributeur("g_a",100));
          g.ajouter(new Contributeur("g_b",200));
          g.ajouter(new Contributeur("g_c",300));
          assertTrue(" Ce composite est valide, revoyez CompositeValide !!!", g.accepter(new CompositeValide()));
          assertEquals(" Revoyez DébitMaximal !!!", new Integer(100), g.accepter(new DebitMaximal()));
          GroupeDeContributeurs g1 = new GroupeDeContributeurs("g1");
          g.ajouter(g1);
          assertFalse(" Ce composite n'est pas valide, revoyez CompositeValide !!!", g1.accepter(new CompositeValide()));
      }catch(Exception e){
          fail("exception inattendue !!! " + e.getMessage());
      }
  }
  
   public void testTroisContributeurs()
   {
    try{
      GroupeDeContributeurs g = new GroupeDeContributeurs("g");
      g.ajouter(new Contributeur("g_a",100));
      g.ajouter(new Contributeur("g_b",200));
      g.ajouter(new Contributeur("g_c",300));
      assertTrue(" Ce composite est valide, revoyez CompositeValide !!!", g.accepter(new CompositeValide()));
  }catch(Exception e){
        fail("exception inattendue !!! " + e.getMessage());
      }
  } // testTroisContributeurs()
  
  public void testCompositeValide()
  {
    try{
      GroupeDeContributeurs g = new GroupeDeContributeurs("g");
      assertFalse(" Ce composite n'est pas valide, revoyez CompositeValide !!!", g.accepter(new CompositeValide()));
      
      GroupeDeContributeurs g1 = new GroupeDeContributeurs("g1");
      g.ajouter(g1);
      assertFalse(" Ce composite n'est pas valide, revoyez CompositeValide !!!", g.accepter(new CompositeValide()));

      g1.ajouter(new Contributeur("c",100));
      assertTrue(" Ce composite est valide, revoyez CompositeValide !!!", g.accepter(new CompositeValide()));

   }catch(Exception e){
        fail("exception inattendue !!! " + e.getMessage());
      }
  } // testCompositeValide()
  
    public void testTroisContributeursUnGroupe()
    {
    try{
      GroupeDeContributeurs g = new GroupeDeContributeurs("g");
      g.ajouter(new Contributeur("g_a",100));
      g.ajouter(new Contributeur("g_b",200));
      g.ajouter(new Contributeur("g_c",300));
      assertTrue(" Ce composite est valide, revoyez CompositeValide !!!", g.accepter(new CompositeValide()));
      assertEquals(" Revoyez DébitMaximal !!!", new Integer(100), g.accepter(new DebitMaximal()));
      GroupeDeContributeurs g1 = new GroupeDeContributeurs("g1");
      g.ajouter(g1);
      assertFalse(" Ce composite n'est pas valide, revoyez CompositeValide !!!", g1.accepter(new CompositeValide()));
   }catch(Exception e){
        fail("exception inattendue !!! " + e.getMessage());
      }
  } // testTroisContributeursUnGroupe()
} // TestsDesVisiteurs