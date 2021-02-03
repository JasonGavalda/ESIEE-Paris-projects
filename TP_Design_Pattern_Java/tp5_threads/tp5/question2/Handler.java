package question2;

public abstract class Handler<T>
{
  protected Handler<T> aSuccessor;

  public Handler()
  {
    this.aSuccessor = null;
  } // Handler()

  public Handler( final Handler<T> pSuccessor )
  {
    this.aSuccessor = pSuccessor;
  } // Handler(.)

  public void setSuccessor( final Handler<T> pSuccessor )
  {
    this.aSuccessor = pSuccessor;
  } // setSuccessor(.)

  public Handler<T> getSuccessor()
  {
    return this.aSuccessor;
  } // getSuccessor()

  public boolean handleRequest( final T pValue )
  {
    if ( this.aSuccessor == null )
      return false;
    return this.aSuccessor.handleRequest( pValue );
  } // handleRequest(.)
} // Handler
