package question1;


public class TestsALireEtValiderAvantSubmit extends junit.framework.TestCase{
    
    public void testCarElement() {
        Pneu pneu = new Pneu("avant droit");
        assertTrue(pneu.getPression()==1.8f);
        assertFalse(pneu.safetyCheck());
        pneu.setSafe();
        assertTrue(pneu.getPression()==2.5f);
        
        Moteur moteur = new Moteur();
        assertTrue(moteur.getPressionHuile()==2.0f);
        assertFalse(moteur.safetyCheck());
        moteur.setSafe();
        assertTrue(moteur.getPressionHuile()==3.0f);
        
        Reservoir reservoir = new Reservoir();
        assertFalse(reservoir.getFull());
        assertFalse(reservoir.safetyCheck());
        reservoir.setSafe();
        assertTrue(reservoir.getFull());
    }
    
    public void testVoiture() {
        
        Voiture v = new Voiture();
        assertTrue(v.getElements().length==6);
        int pneu=0, reservoir=0, moteur=0;
        for(int i=0;i<6;i++) {
            if(v.getElements()[i] instanceof Pneu)
                pneu++;
            else if(v.getElements()[i] instanceof Reservoir)
                reservoir++;
            else if(v.getElements()[i] instanceof Moteur)  
                moteur++;
        }
        assertTrue( pneu==4 && reservoir==1 && moteur==1);
    }

    public void testVisiteur() {
        Voiture voiture = new Voiture();

        CarElementVisitor<String> checkVisitor = new CarElementCheckVisitor();
        CarElementVisitor<String> maintenanceVisitor = new CarElementMaintenanceVisitor();

        String resultatDeLaVisite1 = checkVisitor.visitCar(voiture);
        assertTrue(resultatDeLaVisite1.startsWith("Compte rendu des niveaux du vehicule\n"+
	"DANGER !!! Pression du pneu avant gauche : 1.8\n"+
	"DANGER !!! Pression du pneu avant droit : 1.8\n"+
	"DANGER !!! Pression du pneu arriere gauche : 1.8\n"+
	"DANGER !!! Pression du pneu arriere droit : 1.8\n"+
	"ATTENTION !!! Le reservoir n'est pas plein\n"+
	"DANGER !!! Pression d'huile moteur : 2.0\n"+
	"Niveaux du vehicule transmis\n"));
        
        String resultatDeLaVisite2 = maintenanceVisitor.visitCar(voiture);
        assertTrue(resultatDeLaVisite2.startsWith("Verification des niveaux du vehicule\n"+
	"Pneu avant gauche regonfle. Pression : 2.5\n"+
	"Pneu avant droit regonfle. Pression : 2.5\n"+
	"Pneu arriere gauche regonfle. Pression : 2.5\n"+
	"Pneu arriere droit regonfle. Pression : 2.5\n"+
	"Le reservoir a ete rempli\n"+
	"Ajout d'huile moteur. Pression d'huile: 3.0\n"+
	"Niveaux du vehicule verifies\n"));
        
    }
}
