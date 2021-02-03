package question1;

public interface CarElementVisitor<T> {
    public T visit(Pneu pneu);
    public T visit(Moteur moteur);
    public T visit(Reservoir reservoir);
    public T visitCar(Voiture voiture);
}