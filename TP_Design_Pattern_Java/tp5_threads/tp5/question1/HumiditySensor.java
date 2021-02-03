package question1;

/**
 * Decrivez votre classe Humidity ici.
 * 
 * @author (votre nom) 
 * @version (un numero de version ou une date)
 */
public abstract class HumiditySensor
{
  /** valeur du capteur, precision de 0.1 */
  public abstract float value() throws Exception;

  /* periode minimale entre deux lectures */
  public abstract long minimalPeriod();
} // HumiditySensor
