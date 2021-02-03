package question1;

public class Main{
    
    static public void main(String[] args) {
        
        Voiture voiture = new Voiture();
        CarElementVisitor<String> checkVisitor = new CarElementCheckVisitor();
        CarElementVisitor<String> maintenanceVisitor = new CarElementMaintenanceVisitor();

        System.out.println(checkVisitor.visitCar(voiture));
        System.out.println(maintenanceVisitor.visitCar(voiture));
    }
}