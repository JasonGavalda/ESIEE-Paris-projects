package question2;

import java.util.List;
import java.util.Map;

public class Memento {
    private List etatListe;
    private Map<String, Integer> etatMap;
            
        public Memento (List liste, Map<String, Integer> map)
        {
            etatListe = liste;
            etatMap = map;
        }
            
        public List getSavedList() {return etatListe;}
            
        public Map getSavedMap() {return etatMap;}
            
}
