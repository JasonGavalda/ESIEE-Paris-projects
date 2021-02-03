import java.util.HashMap;
import java.util.Set;
/**
 * Class describing the ItemLists, collections which contains Items.
 * Each of these are HashMaps.
 * @author Jason GAVALDA
 * @version 9/12/19
 */
public class ItemList
{
    private HashMap <String, Item> aItemList;
    
    /**
     * Constructor of an ItemList.
     * Each ItemList is created as an HashMap which contains a String containing the name of the item,
     * and the Item itself.
     */
    public ItemList ()
    {
        this.aItemList = new HashMap<String, Item>();
    } // ItemList()
    
    /**
     * @return A Set of the ItemList.
     */
    public Set getItemList()
    {
        return this.aItemList.keySet();
    } // getItemList()
    
    /**
     * Adds an Item in the ItemList specified by its name and itself.
     * @param pItemName The name of the Item to add to the ItemList.
     * @param pItem The Item to add in the ItemList.
     */
    public void addItem (final String pItemName, final Item pItem)
    {
        this.aItemList.put(pItemName, pItem);
    } // addItem ()
    
    /**
     * Returns whether a specified item is contained in an ItemList.
     * @param pItemName The name of the item to be checked.
     * @return Whether or not an Item is present in the ItemList.
     */
    public boolean checkItem (final String pItemName)
    {
        return this.aItemList.containsKey(pItemName);
    } // checkItem()
    
    /**
     * Returns an Item contained in the ItemList.
     * @param pItemName The name of the Item.
     * @return The Item contained in the ItemList or null if not present.
     */
    public Item getItem (final String pItemName)
    {
        return this.aItemList.get(pItemName);
    } // getItem()
    
    /**
     * Removes an Item specified by its name from the ItemList.
     * @param pItemName The name of the Item.
     */
    public void removeItem (final String pItemName)
    {
         this.aItemList.remove(pItemName);
    } // removeItem()
    
    /** Returns a String of the elements contained in an ItemList.
     * for example : " you manage to find a book ".
     * @return The Items which are in the ItemList.
     */
    public String getItemString()
    {
        String vReturnString= "you manage to find a ";
        Set<String> vKeys = aItemList.keySet();
        for(String item : vKeys)    {
            vReturnString += item+ " ; ";
        }
        if (vReturnString == "you manage to find a " )
            return "there is no item.";
        return vReturnString;
    } // getItemString()    
}
