package question3;


public abstract class A {

  public void m(int i) throws Exception{
    try {
    pre(i>=0);
    i = i + 1 ;
    post(i>0);
    }
    catch (Exception pE)
    {}
  }

  public void pre(boolean pB)
  {}
  
  public void post(boolean pB)
  {}
     // à compléter 
}