import java.util.ArrayList;
import java.util.Random;
/**
 * Class which allows the game to select a Room randomly
 *
 * @author Jason GAVALDA
 * @version 12.01.2020
 */
public class RoomRandomizer
{
    private ArrayList <Room> aRoomList;
    private Random aRNG;
    private int aAleaInt;
    
    /**
     * Constructor which creates a List of the possible Rooms accessed
     * by the Room Randomizer.
     */
    public RoomRandomizer()
    {
        this.aRoomList = new ArrayList<Room>();
        this.aRNG = new Random();
        this.aAleaInt = -1;
    } // RoomRandomizer
    
    /**
     * Modifies aAleaInt to the pInt value.
     * @param pInt The value aAleaInt will take.
     */
    public void setAleaInt(final int pInt)
    {
        this.aAleaInt=pInt;
    }
    
    /**
     * Adds a Room to the Array of possible Rooms.
     * @param pRoom The Room to add in the ArrayList attribute.
     */
    public void addRoom(final Room pRoom)
    {
        this.aRoomList.add(pRoom);
    } // addRoom()
    
    /**
     * Removes a Room to the field of possible Rooms selected by the randomizer.
     * @param pRoom The Room removed from the Randomizer.
     */
    public void removeRoom(final Room pRoom)
    {
        this.aRoomList.remove(pRoom);
    } // removeRoom()
    
    /**
     * Checks if the Room pRoom is in the aRoomList ArrayList.
     * @param pRoom The Room to be checked.
     * @return true whether Room pRoom is in the aRoomList ArrayList or false either.
     */
    public boolean roomCheck(final Room pRoom)
    {
        return this.aRoomList.contains(pRoom);
    } // isRandomizerEmpty()
    
    /**
     * Finds a RandomRoom within the ArrayList aRoomList.
     * @return A random Room within the ArrayList aRoomList or the Room at the 
     */
    public Room findRandomRoom()
    {
        if (this.aAleaInt == -1)
        {
            int a = this.aRNG.nextInt(this.aRoomList.size()-1);
            return this.aRoomList.get(a);
        }
        else
        {
            if(this.aAleaInt>=this.aRoomList.size() | this.aAleaInt<-1)
                return this.aRoomList.get(this.aRoomList.size()-1);
            return this.aRoomList.get(aAleaInt);
        }    
    } // findRandomRoom()
} // RoomRandomizer
