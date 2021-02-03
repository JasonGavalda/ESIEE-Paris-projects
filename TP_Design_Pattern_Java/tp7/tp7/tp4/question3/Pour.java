package tp4.question3;

import tp4.question2.ExpressionBooleenne;

public class Pour extends Iteration{
   private Instruction init,inc;
   
   public Pour(Instruction init, ExpressionBooleenne cond, Instruction i1, Instruction inc){
    super(cond,i1);
    this.init = init;
    this.inc  = inc;
  }
  
	public <T> T  accepter(VisiteurInstruction<T> vi){
    return vi.visite(this);
  }
  
  public Instruction init(){return init; }
  public Instruction inc(){return inc; }
}
