import java.util.Stack;
/**
 * This class describes the Players objects.
 * Each player is defined by his name,
 * his inventory,
 * the maximum weight his vessel can carry,
 * the weight of his inventory,
 * the current Room he is placed and
 * the previous Rooms he visited,
 *
 * @author Jason GAVALDA
 * @version 10/12/2019
 */
public class Player
{
    private Room aCurrentRoom;
    private Stack aPreviousRoom;
    private String aName;
    private ItemList aInventory;
    private double aMaxWeight;
    private double aInventoryWeight;
    private int aFuel;
    private Stack aStarGate;
    
    /**
     * Constructor creating a player with a name and a starting Room chosen.
     * His inventory is empty when created.
     * One player can carry a maximum weight of 30.
     * @param pName The name of the player.
     * @param pCurrentRoom The starting Room of the player.
     */
    public Player (final String pName, final Room pCurrentRoom)
    {
        this.aName = pName;
        this.aPreviousRoom = new Stack();
        this.aCurrentRoom = pCurrentRoom;   
        this.aInventory = new ItemList();
        this.aMaxWeight = 30;
        this.aInventoryWeight = 0;
        this.aFuel = 5;
        this.aStarGate = new Stack();
    }
    
    /**
     * Accessor to get the current Room of a player.
     * @return The current Room of a player.
     */
    public Room getCurrentRoom()
    {
        return this.aCurrentRoom;
    } // getCurrentRoom()
    
    /**
     * Accessor to get the previous Rooms the player went in.
     * @return A Stack of the previous Room encountered by the player.
     */
    public Stack getPreviousRooms()
    {
        return this.aPreviousRoom;
    } // getPreviousRooms()
    
    /**
     * Get the aPreviousRoom Stack empty
     */
    public void getStackEmpty()
    {
        while (!(this.aPreviousRoom.empty()))
        {
            this.aPreviousRoom.pop();
        }
    } // getStackEmpty
    
    /**
     * Accessor to get the maximum weight a player can carry.
     * @return The maximum weight a player can carry on his vessel.
     */
    public double getMaxWeight()
    {
        return this.aMaxWeight;
    } // getMaxWeight()
    
    /**
     * Accessor to get the weight a player his currently carrying in his vessel.
     * @return The weight a player is carrying in his vessel.
     */
    public double getInventoryWeight()
    {
        return this.aInventoryWeight;
    } // getInventoryWeight()
    
    /**
     * Accessor which gets the remaining fuel of the player.
     * @return The remaining fuel of the player.
     */
    public int getFuel()
    {
        return this.aFuel;
    } // getFuel()
    
    /**
     * Accessor which gets the aStarGate stack.
     * @return The aStarGate stack.
     */
    public Stack getStarGate()
    {
        return this.aStarGate;
    } // getStarGate()
    
    /**
     * Modifier which modifies the maximum weight the player can carry.
     * @param pMaxWeight The new max weight a player is able to carry.
     */
    public void setMaxWeight(final double pMaxWeight)
    {
        this.aMaxWeight = pMaxWeight;
    } // setMaxWeight()
    
    /**
     * Modifier which modifies the remaining fuel of the player.
     * @param pFuel The new amount of fuel the player will have.
     */
    public void setFuel(final int pFuel)
    {
        this.aFuel = pFuel;
    } // setFuel()
    
    /**
     * The actual part of the "go" method where the player actually moves one Room to another.
     * @param pNextRoom The next Room where the player moves in.
     */
    public void move(final Room pNextRoom)
    {
        this.aPreviousRoom.push(this.aCurrentRoom);
        this.aCurrentRoom = pNextRoom;
        this.aFuel -= 1;
    } // move()
    
    /**
     * The actual part of the "back" method where the player actually moves back to the previous Room he went in.
     */
    public void moveBack()
    {
        this.aCurrentRoom =(Room) this.aPreviousRoom.pop();
        this.aFuel -= 1;
    } // moveBack()
    
    /**
     * This method checks if the stack aPreviousRoom is empty or not.
     * @return Whether the aPreviousRoom is empty or not.
     */
    public boolean isBackEmpty()
    {
        return this.aPreviousRoom.empty();
    }
    
    /**
     * A command that allows a player to take a specified item in a Room.
     * @param pItemName The name of the item to be taken.
     */
    public void take (final String pItemName)
    {
        Item vItem = this.aCurrentRoom.getRoomItem(pItemName);
        this.aCurrentRoom.removeRoomItem(pItemName);
        this.aInventory.addItem(pItemName, vItem);
        this.aInventoryWeight = this.aInventoryWeight + vItem.getWeight();
    } // take()
    
    /**
     * A command that allows a player to drop an item of his inventory in the Room he currently is.
     * @param pItemName The name of the item to be dropped.
     */
    public void drop (final String pItemName)
    {
        Item vItem = this.aInventory.getItem(pItemName);
        this.aInventory.removeItem(pItemName);
        this.aCurrentRoom.setItem(pItemName, vItem);
        this.aInventoryWeight = this.aInventoryWeight - vItem.getWeight();
    } // drop()
    
    /**
     * A command that allows the player to charge the StarGate with the Room he is in.
     */
    public void charge()
    {
        if (!(isStarGateEmpty()))
            this.aStarGate.pop();
        this.aStarGate.push(this.aCurrentRoom);
    } // charge()
    
    /**
     * A command that allows the player to teleport in the Room stocked in the StarGate.
     */
    public void warp()
    {
        Room vNextRoom = (Room) this.aStarGate.pop();
        this.move(vNextRoom);
    } // warp()
    
    /**
     * Checks whether the StarGate is empty or not
     * @return True if the StarGate is empty or false either.
     */
    public boolean isStarGateEmpty()
    {
        return (this.aStarGate.empty());
    } // isStarGateEmpty()
    
    /**
     * Accessor which gets the Inventory as an ItemList.
     * @return The Inventory as an ItemList.
     */
    public ItemList getInventory ()
    {
        return this.aInventory;
    } // getInventory ()
    
    /**
     * @return A String showing the Items the player's vessel is carrying.
     */
    public String getPlayerInventory()
    {
        return "In your hold, "+this.aInventory.getItemString();
    } // getPlayerInventory()
} // Player
