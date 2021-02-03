package question1;

/**
 * classe Pile d'entiers
 * 
 * @author JMD/DB 
 * @version v2010
 */
public class Pile
{
  public static final int CAPACITE_PAR_DEFAUT = 6;
  
  private Object[] zone;
  private int ptr;
  
  public Pile( final int pTaille )
  {
    int vTaille;
    if ( pTaille<0 )
      vTaille = CAPACITE_PAR_DEFAUT;
    else
      vTaille = pTaille;
    this.zone = new Object[vTaille];
    this.ptr = 0;
  } // Pile(.)

  public Pile()
  {
    this( CAPACITE_PAR_DEFAUT );
  } // Pile()
  
  public void empiler( final Object pO ) throws PilePleineException
  { 
    if ( this.estPleine() )   throw new PilePleineException();
    this.zone[this.ptr] = pO;
    this.ptr++;
  } // empiler(.)

  public Object depiler() throws PileVideException
  {   
    if ( this.estVide() )   throw new PileVideException();
    this.ptr--;
    return this.zone[this.ptr];
  } // depiler()
 
  public boolean estVide()
  {
    return this.ptr == 0;
  } // estVide()

  public boolean estPleine()
  {
    return this.ptr == this.zone.length;
  } // estPleine()

  public String toString()
  {
    StringBuffer vSB = new StringBuffer( "[" );
    for ( int vI=this.ptr-1; vI>=0; vI-- ) {
      vSB.append( this.zone[vI].toString() );
      if (vI > 0)   vSB.append( ", " );
    } // for
    vSB.append( "]" );
    return vSB.toString();
  } // toString()
} // Pile
