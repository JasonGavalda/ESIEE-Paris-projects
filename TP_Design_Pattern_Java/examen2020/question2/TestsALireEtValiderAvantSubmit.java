package question2;

public class TestsALireEtValiderAvantSubmit extends junit.framework.TestCase{

  
    public void testGetParent() throws Exception {
        AtomicCarElement volant = new AtomicCarElement("volant");
        AtomicCarElement colonne =  new AtomicCarElement("colonne de direction");
        AtomicCarElement roues = new AtomicCarElement("roues");
        AtomicCarElement chassis = new AtomicCarElement("chassis");
        assertEquals(null, volant.getParent());
        CompositeCarElement direction =  new CompositeCarElement("Systeme de direction");
        direction.addCarElement(volant).addCarElement(colonne).addCarElement(roues);
        assertEquals(direction, volant.getParent());
        assertEquals(direction, colonne.getParent());
        assertEquals(direction, roues.getParent());
        CompositeCarElement voiture = new CompositeCarElement("Porsche 911");
        voiture.addCarElement(chassis);
        voiture.addCarElement(direction);
        assertEquals(voiture, direction.getParent());
        assertEquals(voiture, chassis.getParent());
        assertEquals(voiture, volant.getParent().getParent());
    }

    public void testVisitors() throws Exception {

        AtomicCarElement volant = new AtomicCarElement("volant");
        AtomicCarElement colonne =  new AtomicCarElement("colonne de direction");
        AtomicCarElement roue1 = new AtomicCarElement("roue avant droite");
        AtomicCarElement roue2 = new AtomicCarElement("roue avant gauche");
        AtomicCarElement roue3 = new AtomicCarElement("roue arriere gauche");
        AtomicCarElement roue4 = new AtomicCarElement("roue arriere droite");
        AtomicCarElement carbu = new AtomicCarElement("carburateur");
        AtomicCarElement cylindres = new AtomicCarElement("cylindres");

        CompositeCarElement roues = new CompositeCarElement("roues");
        roues.addCarElement(roue1).addCarElement(roue2).addCarElement(roue3).addCarElement(roue4);
        CompositeCarElement direction = new CompositeCarElement("Systeme de direction");
        direction.addCarElement(volant).addCarElement(colonne).addCarElement(roues);
        CompositeCarElement moteur = new CompositeCarElement("moteur");
        moteur.addCarElement(carbu).addCarElement(cylindres);
        CompositeCarElement voiture = new CompositeCarElement("Porsche 911");
        voiture.addCarElement(direction).addCarElement(moteur);

        String resultatDeLaVisite =voiture.accept(new CarElementMountingVisitor());
        assertTrue(resultatDeLaVisite.startsWith("Demarrage du montage de l'ensemble Porsche 911\n"+
                "\tDemarrage du montage de l'ensemble Systeme de direction\n"+
                "\t\tMontage de l'element : volant\n"+
                "\t\tMontage de l'element : colonne de direction\n"+
                "\t\tDemarrage du montage de l'ensemble roues\n"+
                "\t\t\tMontage de l'element : roue avant droite\n"+
                "\t\t\tMontage de l'element : roue avant gauche\n"+
                "\t\t\tMontage de l'element : roue arriere gauche\n"+
                "\t\t\tMontage de l'element : roue arriere droite\n"+
                "\t\tFin du montage de l'ensemble roues\n"+
                "\tFin du montage de l'ensemble Systeme de direction\n"+
                "\tDemarrage du montage de l'ensemble moteur\n"+
                "\t\tMontage de l'element : carburateur\n"+
                "\t\tMontage de l'element : cylindres\n"+
                "\tFin du montage de l'ensemble moteur\n"+
                "Fin du montage de l'ensemble Porsche 911\n"));

        assertTrue(voiture.accept(new CarElementCountingVisitor())==8);
    }

}
