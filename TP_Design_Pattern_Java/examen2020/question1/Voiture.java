package question1;



public class Voiture {
    private Pneu aPneuAvG;
    private Pneu aPneuAvD;
    private Pneu aPneuArG;
    private Pneu aPneuArD;
    private Moteur aMoteur;
    private Reservoir aReservoir;

    public CarElement[] getElements() {
         CarElement[] CarElements = new CarElement[]    {
         aPneuAvG,
         aPneuAvD,
         aPneuArG,
         aPneuArD,
         aReservoir,
         aMoteur
         };
         return CarElements;
    }

    public Voiture() {
        this.aPneuAvG = new Pneu("avant gauche");
        this.aPneuAvD = new Pneu("avant droit");
        this.aPneuArG = new Pneu("arriere gauche");
        this.aPneuArD = new Pneu("arriere droit");
        this.aMoteur = new Moteur();
        this.aReservoir = new Reservoir();
    }
}