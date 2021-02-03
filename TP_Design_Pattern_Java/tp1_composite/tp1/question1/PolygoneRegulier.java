package question1;

public class PolygoneRegulier
{
  private int nombreDeCotes ;
  private int longueurDuCote ;

  /** Creation d'un polygone regulier
   * @param nombreDeCotes  (nombreDeCotes >=1 && nombreDeCotes <100)
   * @param longueurDuCote (longueurDuCote>=1 && longueurDuCote < 10000)
   */
  public PolygoneRegulier( final int pNombreDeCotes , final int pLongueurDuCote )
  {
    assert 1 <= pNombreDeCotes && pNombreDeCotes <100;
    assert 1 <= pLongueurDuCote && pLongueurDuCote < 10000;
    this.nombreDeCotes = pNombreDeCotes ;
    this.longueurDuCote = pLongueurDuCote ;
  } // PolygoneRegulier(.,.)
    
  public int perimetre()
  { 
    return this.nombreDeCotes * this.longueurDuCote ;   
  } // perimetre()

  public int surface()
  {    
    return (int)( 1/4.0 * this.nombreDeCotes * Math.pow( this.longueurDuCote , 2.0 )
                  * cotg( Math.PI / this.nombreDeCotes ) ) ; 
  } // surface()

  private static double cotg( final double pX )
  {    
    return Math.cos( pX ) / Math.sin( pX ) ;   
  } // cotg(.)
    
  public boolean equals ( final Object pObj )
  {
    if ( (pObj == null) || (pObj.getClass() != this.getClass()) )
      return false ;
    
    PolygoneRegulier vPoly = (PolygoneRegulier)pObj;
    return vPoly.nombreDeCotes == this.nombreDeCotes && vPoly.longueurDuCote == this.longueurDuCote;
  } //equals(.)

  public int hashCode()
  {
    return this.nombreDeCotes*10000 + this.longueurDuCote;
    // ou bien return toString().hashCode();
  } // hashCode()
  
  public String toString()
  {
    return "<"+ this.nombreDeCotes + "," + this.longueurDuCote + ">" ;
  } // toString()
} // PolygoneRegulier
