
/**
 * Class of the Items of the game.
 * Each Item is defined by its name, its description and its weight.
 * @author Jason GAVALDA
 * @version 22/11/2019
 */
public class Item
{
    private String aName;
    private String aDescription;
    private double aWeight;
    
    /**
     * Constructor creating an item with its name, its description and its weight.
     * @param pName The name of the item.
     * @param pDescription The description of the Item.
     * @param pWeight The weight of the Item.
     */
    public Item (final String pName, final String pDescription, final double pWeight)  {
        this.aName = pName;
        this.aDescription = pDescription;
        this.aWeight = pWeight;
    } // Item()
    
    /**
     * Accessor which gets the Name of an Item.
     * @return The name of an Item.
     */
    public String getItemName()
    {
        return this.aName;
    } // getItemName()
    
    /**
     * Accessor which gets the description of an Item.
     * @return The description of an Item.
     */
    public String getItem_Description() {
        return this.aDescription;
    } // getItem_Description()
    
    /**
     * Accessor which gets the weight of an Item.
     * @return The weight of an Item.
     */
    public double getWeight()  {
        return this.aWeight;
    } // getWeight()
} // Item
