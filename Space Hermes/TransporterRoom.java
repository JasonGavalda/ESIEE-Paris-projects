
/**
 * A precise type of Room used to teleport a player 
 * to another random selected Room.
 *
 * @author Jason GAVALDA
 * @version 12.01.2020
 */
public class TransporterRoom extends Room
{
    private RoomRandomizer aRoomRandomizer;
    
    /**
     * Constructor which creates a TransporterRoom using the Room constructor.
     * @param pDescription The Description of the TransporterRoom
     * @param pImage The image of the TransporterRoom.
     */
    public TransporterRoom (final String pDescription , final String pImage)
    {
        super(pDescription ,pImage);
        this.aRoomRandomizer = new RoomRandomizer();
    } // Transporter_Room
    
    /**
     * Accessor which gets the RoomRandomizer of the TransporterRoom.
     * @return The RoomRandomizer of the TransporterRoom.
     */
    public RoomRandomizer getRoomRandomizer()
    {
        return this.aRoomRandomizer;
    }
    
    /**
     * Overrides the previous getExit() method from Room to get a random exit
     * for the current TransporterRoom rather than a normal exit.
     * @param pDirection The Direction the player wants to moves in, 
     * useless in this method but used to be interpretated correctly by goRoom() in GameEngine.
     * @return A random exit Room among those attached to the TransporterRoom.
     */
    @Override
    public Room getExit(final String pDirection)
    {
        return this.aRoomRandomizer.findRandomRoom();
    } // getExit()
    
    /**
     * Adds a Room attached to the TransporterRoom.
     * @param pRoom The Room to add to the list of attached Rooms in this TransporterRoom.
     */
    public void addExit(final Room pRoom)
    {
        this.aRoomRandomizer.addRoom(pRoom);
    } // addExit()
    
    /**
     * Removes a Room attached to the TransporterRoom.
     * @param pRoom The Room to add to the list of attached Rooms in this TransporterRoom.
     */
    public void removeExit(final Room pRoom)
    {
        if (this.aRoomRandomizer.roomCheck(pRoom))
            this.aRoomRandomizer.removeRoom(pRoom);
    } // removeExit()
    
} // TransporterRoom
