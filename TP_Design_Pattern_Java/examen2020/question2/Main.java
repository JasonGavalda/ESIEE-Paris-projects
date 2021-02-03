package question2;


public class Main{
    static public void main(String[] args) throws Exception {
        
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

        System.out.println(voiture.accept(new CarElementMountingVisitor()));  
        System.out.println("Nombre d'elements dans ce vehicule : "+voiture.accept(new CarElementCountingVisitor())); 

    }
}