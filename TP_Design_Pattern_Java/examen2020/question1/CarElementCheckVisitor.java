package question1;

public class CarElementCheckVisitor implements CarElementVisitor<String> {

    @Override
    public String visit(Pneu pneu) {
        if(!pneu.safetyCheck())
            return "DANGER !!! Pression du pneu "+ pneu.getPosition() + " : "+pneu.getPression()+"\n";
        else
            return "Pression du pneu "+ pneu.getPosition() + " : "+pneu.getPression()+"\n";
    }

    @Override
    public String visit(Moteur moteur) {
        if(!moteur.safetyCheck())   
            return "DANGER !!! Pression d'huile moteur : "+moteur.getPressionHuile()+"\n";// a completer
        else
            return "Pression d'huile "+ moteur.getPressionHuile()+"\n";
    }

    @Override
    public String visit(Reservoir reservoir) {
        if(!reservoir.safetyCheck())
            return "ATTENTION !!! Le reservoir n'est pas plein\n";
        else
            return "Reservoir plein";
    }

    @Override
    public String visitCar(Voiture voiture) {
        StringBuilder sb = new StringBuilder("Compte rendu des niveaux du vehicule\n");
        for(CarElement element : voiture.getElements()) {
            sb.append(element.accept(this));
        }
        sb.append("Niveaux du vehicule transmis\n");
        return sb.toString();
    }
}