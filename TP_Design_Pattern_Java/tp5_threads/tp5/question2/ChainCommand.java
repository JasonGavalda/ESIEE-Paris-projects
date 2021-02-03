package question2;

import question1.Command;

/**
 * Decrivez votre classe CommandToChain ici.
 * 
 * @author (votre nom) 
 * @version (un numero de version ou une date)
 */
public class ChainCommand<T> implements Command<T>
{
  private Handler<T> aChain;

  public ChainCommand( final Handler<T> pChain )
  {
    this.aChain = pChain;
  } // ChainCommand(.)

  public void make( final T pValue )
  {
    this.aChain.handleRequest( pValue );
  } // make(.)
} // ChainCommand
