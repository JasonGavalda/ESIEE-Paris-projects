import java.util.HashMap;
import java.util.Set;
import java.lang.StringBuilder;
/** Class which represents the systems the player travels through the game.
 * @author Jason GAVALDA
 * @version 22/11/2019
*/
public class Room
{
        private String aDescription;
        private HashMap<String, Room> exits;
        private String aImageName;
        private ItemList aRoomItems;
        
        /**
         * Creates a Room described by 'pDescription'
         * There is no exits at the creation of the Room.
         * @param pDescription The description of the created Room.
         * @param pImage The Room Image.
         */
        public Room (final String pDescription , final String pImage)
        {
            this.aDescription= pDescription;
            this.exits = new HashMap<String, Room>();
            this.aRoomItems = new ItemList();
            this.aImageName = pImage;
        } //Room

        /**
         * Accessor allowing to get the ItemList of the Room.
         * @return The ItemList of the Room.
         */
        public ItemList getRoomItems()
        {
            return this.aRoomItems;
        } // getRoomItems()
        
        /**
         * Accessor that returns the description of the Room.
         * @return A description of the Room.
         */
        public String getDescription()
        {
            return this.aDescription;
        } // getDescription()

        /**
         * Returns the reached Room in case of moving.
         * in the direction 'pDirection'.
         * If there is no Room in this direction, returns null.
         * @param pDirection The direction one wishes to know the exit.
         * @return The Room at the indicated direction, if present.
         */
        public Room getExit(final String pDirection)
        {
            return this.exits.get(pDirection);
        } // getExit()
        
        /** Returns a description of all the possible exits of the Room,
         * @return A description of all the possible exots of the Room.
         */
        public String getExitString()

        {
            String vReturnString="Exits :";
            Set<String> vKeys = exits.keySet();
            for(String exit : vKeys) {
                vReturnString += ' '+ exit;
            }
            return vReturnString;
        } // getExitString()

        /**
         * Defines the exits of a Room.
         * @param pDirection The direction of the exit.
         * @param pNeighbor The Room at the indicated direction.
         */
        public void setExits (String pDirection, Room pNeighbor)
        {
            this.exits.put(pDirection, pNeighbor);
        } // setExits()
        
        /**
         * Checks if the Room in parameter is an Exit of the CurrentRoom.
         * @param pRoom The Room to be checked as an Exit.
         * @return true if the Room in the parameter is an Exit, or false if it's not the case.
         */
        
        public boolean isExit (final Room pRoom)
        {
            return this.exits.containsValue(pRoom);
        }
        
        /**
         * Set an Item in the Room.
         * @param pItemName The name of the Item that has to be placed.
         * @param pItem The item that has to be placed.
         */
        public void setItem (String pItemName, Item pItem)
        {
            this.aRoomItems.addItem(pItemName, pItem);
        } // setItem()
        
        /**
         * Get the item specified by its name.
         * @param pItemName The name of the item one wants to get.
         * @return The Item specified.
         */
        public Item getRoomItem (final String pItemName)
        {
            return this.aRoomItems.getItem(pItemName);
        } // getRoomItem()
        
        /**
         * Removes the Item named pItemName from the Room.
         * @param pItemName The name of the item one wants to remove from the room.
         */
        public void removeRoomItem(final String pItemName)
        {
            this.aRoomItems.removeItem(pItemName);
        } // removeRoomItem()
        
        /**
         * Returns the Items contained in the Room.
         * @return A String of all the Items in the Room.
         */
        public String getItemsString()
        {
            return this.aRoomItems.getItemString();
        } // getItemsString()
        
        /**
         * Shares a detailed description of the Room, with its exits.
         *@return A detailed description of the Room, with its exits.
         */
        public String getLongDescription()
        {
            return "You are "+ this.aDescription + ".\n"+
            getExitString();
        } // getLongDescription()
        
        /**
         * Accessor getting the name of the image to be printed.
         * @return The name of the image to be printed
         */
        public String getImageName()
        {
            return this.aImageName;
        } // getImageName()
} // Room
