package question1;

public class CarElementMaintenanceVisitor implements CarElementVisitor<String> {

    public String visit(Pneu pneu) {
        if (!pneu.safetyCheck())    {
            pneu.setSafe();
            return "Pneu "+ pneu.getPosition() + " regonfle. Pression : "+pneu.getPression()+"\n";
        }
        else
            return "Pression du pneu "+ pneu.getPosition() + " convenable : "+pneu.getPression()+"\n";
    }

    public String visit(Moteur moteur) {
        if(!moteur.safetyCheck())   {
            moteur.setSafe();
            return "Ajout d'huile moteur. Pression d'huile: 3.0\n";// a completer
        }
        else
            return "Pression d'huile "+ moteur.getPressionHuile()+"\n";
    }

    public String visit(Reservoir reservoir) {
        if(!reservoir.safetyCheck())    {
            reservoir.setSafe();
            return "Le reservoir a ete rempli\n";
        }
        else
            return "Reservoir plein\n";
    }

    public String visitCar(Voiture voiture) {
        StringBuilder sb = new StringBuilder("Verification des niveaux du vehicule\n");
        for(CarElement element : voiture.getElements()) {
            sb.append(element.accept(this));
        }
        sb.append("Niveaux du vehicule verifies\n");
        return sb.toString();
    }
}