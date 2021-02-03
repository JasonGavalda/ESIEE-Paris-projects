package question1;

public interface CarElement {
    // Méthode à définir par les classes implémentant CarElements
    public <T> T accept(CarElementVisitor<T> visitor);
    // Vérifie que les élément sont en état de sécurité
    // (pression adequat ...)
    public boolean safetyCheck ();   
    // met les élément en éta de sécurité s'ils ne le sont pas.
    public void setSafe();
}
