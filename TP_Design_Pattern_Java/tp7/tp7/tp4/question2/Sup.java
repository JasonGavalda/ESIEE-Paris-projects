package tp4.question2;

import tp4.question1.Expression;

public class Sup extends Relation{
  public Sup(Expression op1, Expression op2){
    super(op1,op2);
  }
  public <T> T  accepter(VisiteurExpressionBooleenne<T> v){
    return v.visite(this);
  }
}