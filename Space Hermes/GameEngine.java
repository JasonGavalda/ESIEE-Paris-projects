    import java.util.HashMap;
    import java.util.Stack;
    import java.util.Scanner;
    import java.io.File;
    import java.io.PrintWriter;
    import java.io.FileNotFoundException;
    import java.net.URL;
    /**
     * Class used as the "Engine" of the Game.
     *
     * @author Jason GAVALDA
     * @version 29/10/2019
     */
    public class GameEngine
    {
        private Room aCurrentRoom;
        private Parser aParser;
        private UserInterface aGui;
        private HashMap<String, Room> aRooms;
        private HashMap<String, Item> aItems;
        private Player aPlayer;
        private boolean aTestMode;
        
        /**
         * Constructor which initializes the different collections of the game (rooms and items),
         * creates the items, the rooms and the player,
         * who starts at the portal.
         */
        public GameEngine()
        {
            this.aRooms = new HashMap<String, Room>();
            this.aItems = new HashMap<String, Item>();
            this.createItems();
            this.createRooms();
            this.aParser=new Parser();
            this.aPlayer = new Player( "Commander", this.aRooms.get("Portal"));
            this.aTestMode = false;
        } // GameEngine()
        
        /**
         * Accessor which returns the HashMap where the Rooms are contained.
         * @return A HashMap containing all the Rooms of the game.
         */
        public HashMap<String, Room> getRooms()
        {
            return this.aRooms;
        } // getRooms()
        
        /**
         *  Accessor which return the HashMap where all the Items are contained.
         *  @return A HashMap containing all the Items of the game.
         */
        public HashMap<String, Item> getItems()
        {
            return this.aItems;
        } // getItems()
        
        /**
         * Creates the player interface and prints the welcome message on it.
         * @param pUserInterface The interface of the game to be displayed.
         */
        public void setGUI ( final UserInterface pUserInterface )
        {
            this.aGui = pUserInterface;
            this.printWelcome();
        } // setGUI()
        
        /**
         * Creates the Items in the Game
         * and put them into the aItems HashMap.
         */
        private void createItems()  {
            Item Map = new Item("Map", "Three stars are indicated, which one would you travel to ?", 0);
            aItems.put("Map", Map);
            Item Stone = new Item("Stone", "A piece of the wall with aliens drawn on it. ", 20);
            aItems.put("Stone", Stone);
            Item Hologram = new Item("Hologram", "A device which shows the hologram of an alien spieces.", 5);
            aItems.put("Hologram", Hologram);
            Item Leaf = new Item("Leaf", "A leaf which the DNA has been modified. ", 0.1);
            aItems.put("Leaf", Leaf);
            Item Holocron = new Item ("Holocron", "The holocron found on the futuristic vessel. It contains some knowledge about a certain artefact which the alien uses." , 5);
            aItems.put("Holocron", Holocron);
            Item Book = new Item ("Book", "You cannot understand what is written on the book, but the drawings represent some kind of sacrificial ritual.", 5);
            aItems.put("Book", Book);
            Item Shard = new Item("Shard", "On this shard is displayed a strange symbol", 0.1);
            aItems.put("Shard", Shard);
            Item Upgraded_Hold = new Item("Upgraded_Hold", "An upgrade to your hold which extends its maximum capacity to 50", 0);
            aItems.put("Upgraded_Hold", Upgraded_Hold);
            Item FuelTank_M = new Item("FuelTank_M", "Using this allows you to jump 3 more times", 0);
            aItems.put("FuelTank_M", FuelTank_M);
            Item FuelTank_L = new Item("FuelTank_L", "Using this allows you to jump one more time", 0);
            aItems.put("FuelTank_L", FuelTank_L);
            Item FuelTank_G = new Item("FuelTank_G", "Using this allows you to jump 5 more times", 0);
            aItems.put("FuelTank_G", FuelTank_G);
            Item StarGate = new Item("StarGate", "Charge it in a system, and use it on another one to go back on the system where you charged it.", 0);
            aItems.put("StarGate", StarGate);
        } // createItems()
        
        /**
         * Creates the Rooms and their exits.
         * Also puts the Items in the Room.
         */
        private void createRooms()
        {
            /* Creation des pièces
             * 
             */
            Room vPortal = new Room ("going forward as the mysterious portal closes behind you\n Searches on a nearby planet of the system reveals a map where three stars near the system are indicated.", "images/vPortal.png");
            aRooms.put("Portal", vPortal);
            
            Room vSystem1 = new Room ("in a red giant system. You can see a planet very close to the star with ruins.\n Some drawings on a broken wall show insectoïds fighting some kind of reptiles with a snail-like head.", "images/vSystem1.jpeg");
            aRooms.put("System1", vSystem1);
            
            Room vSystem2 = new Room ("in a blue giant system. You can see a planet very close to the star with ruins.\n You activate accidently a highly-advanced technological device showing you an hologram of a reptile-like alien speaking in an unknown language.", "images/vSystem2.jpg");
            aRooms.put("System2", vSystem2);
            
            Room vSystem3 = new Room ("in a system where the star looks like the sun.\n The only livable planet has no traces of civilization, but the animals leaving in this planet seem to have their DNA modified to suit some purpose", "images/vSystem3.png");
            aRooms.put("System3", vSystem3);
            
            Room vSystem1_1= new Room ("in a red dwarf system. Some debris of vessels are flying around near a planet.\n You are lucky enough to find the dead bodies of an insectoïd alien and a reptile-like alien, who look like to have fought each other.", "images/vSystem1_1.png");
            aRooms.put("System1_1", vSystem1_1);
            
            Room vSystem2_1= new Room ("near a black hole. You manage to keep yourself safe as you scanvenge the debris of some kind of futuristic vessel.\n There is some kind of holocron in it.", "images/vSystem2_1.jpg");
            aRooms.put("System2_1", vSystem2_1);
            
            Room vSystem3_1 = new Room ("in a system without a bright sun.\n You land on an icy planet and surprisingly find a frozen humanoid with a snail-like head holding an holocron.", "images/vSystem3_1.jpg");
            aRooms.put("System3_1", vSystem3_1);
            
            Room vSystem1_2 = new Room ("near a supernova. You manage to locate a temple on a planet about to be destroyed by its star.\n You manage to salvage a shard and a book which allow you to learn how religious the insectoïds are. They seem to call themselves Zaynites ", "images/vSystem1_2.jpg");
            aRooms.put("System1_2", vSystem1_2);
            
            Room vSystem2_2 = new Room ("in a double-star system. You discover some kind of laboratory in one of the planets, with some animals imprisoned there.\n But you are able to detect a vessel comming in your direction. It seems wiser to leave quickly. ", "images/vSystem2_2.jpg");
            aRooms.put("System2_2", vSystem2_2);
            
            Room vSystem3_2 = new Room ("in a really strange system.\n It seems the star brights only in one of its side.\n By moving closely you find out that a device covers the non bright side, with a space station being supplied by it. But you find out that it fires in your direction. You must flee!", "images/vSystem3_2.png");
            aRooms.put("System3_2", vSystem3_2);
            
            Room vSystem1_3 = new Room ("near a lone planet without sun.\n One of its moons hides a temple with a large beam directed to a specific star. You should check it out!", "images/vSystem1_3.png");
            aRooms.put("System1_3", vSystem1_3);
            
            TransporterRoom vSystem2_3 = new TransporterRoom("coming close to what it seems to be a black hole ! It is too late to get away from it but it seems you haven't been destroyed yet... Maybe you should try to move ?", "images/vSystem2_3.png");
            aRooms.put("System2_3", vSystem2_3);
            
            Room vSystem4 = new Room ("able to dodge a projectile which seems to not have been thrown at you.\n You find out that you just warped in the middle of a space battle!\n You should avoid being hurt by some 'lost bullets'! ", "images/vSystem4.png");
            aRooms.put("System4", vSystem4);
            
            Room vSystem5 = new Room ("arriving near a planet which seems to be surrounded by invaders. As they contact you, you discover the face of a insectoïd claiming to be a 'Zaynite'.\n You understand you have to go south to follow their path. As soon as the conversation finishes, the sieged calls you. They claim themselves as 'Tal'Xins' and offer you to share their technology with you if you follow their path, which is right north", "images/vSystem5.png");
            aRooms.put("System5", vSystem5);
            
            Room vSystem6_1 = new Room ("entering a system occupied by the Zaynites.\n They have been warned of your arrival and welcome you by some sort of ritual. One of them walks to you and surprisingly speaks in your language. He tells you his God spoke to him and learned him who you were. He also tells you you need to help them in their war against their ennemies if you wish to go back home.", "images/vSystem6_1.png");
            aRooms.put("System6_1", vSystem6_1);
            
            Room vSystem6_2 = new Room ("entering the system the Zaynites told you to go.\n It seems there is some sort of factory which produces Tal'Xins' vessels. ", "images/vSystem6_2.png");
            aRooms.put("System6_2", vSystem6_2);
            
            Room vSystem6_3 = new Room ("coming close to the main base of the Tal'Xin in the sector.\n Its destruction may help you find your way home... ", "images/vSystem6_3.jpg");
            aRooms.put("System6_3", vSystem6_3);
            
            Room vSystem7_1 = new Room ("entering a system occupied by the Tal'Xins.\n They have been warned of your arrival and welcome you in a very respectful way. One of them comes to you with some sort of device which scans you. A moment later, the Tal'Xin speaks to you in your language. 'If you want us to help you find your way home, you must help us defeating our ennemies.", "images/vSystem7_1.png");
            aRooms.put("System7_1", vSystem7_1);
            
            Room vSystem7_2 = new Room ("entering the system the Tal'Xins told you to go.\n On one of the planets you discover a big temple where the Zaynites are accomplishing some sort of ritual. Indeed, they are sacrificing Tal'Xin prisonners ! You must deliver them ! ", "images/vSystem7_2.png");
            aRooms.put("System7_2", vSystem7_2);
            
            Room vSystem7_3 = new Room ("coming close to the main base of the Zaynites in the sector.\n Its destruction may help you find your way home... ", "images/vSystem7_3.png");
            aRooms.put("System7_3", vSystem7_3);
            
            Room vSystem8 = new Room ("near home! As you get closer to the sun, you can see your long lost Earth ! Perhaps you should try to make contact... ", "images/vSystem8.jpg");
            aRooms.put("System8", vSystem8);
            
            Room vTerre = new Room ("Welcome home Commander ! We have done it ! Our fellows can't wait to hear our story ! ", "images/vTerre.jpg");
            aRooms.put("Earth", vTerre);
        
            /* Creation of each rooms' exits and items.
             * 
             */
            vPortal.setExits ("north", vSystem1);
            vPortal.setExits ("east", vSystem2);
            vPortal.setExits ("south", vSystem3);
            vPortal.setItem ("Map", aItems.get("Map"));
            vPortal.setItem ("StarGate",aItems.get("StarGate"));
            vPortal.setItem("FuelTank_G", aItems.get("FuelTank_G"));
        
            vSystem1.setExits ("east", vSystem1_1);
            vSystem1.setExits ("south", vPortal);
            vSystem1.setItem ("Stone", aItems.get("Stone"));
            
            vSystem2.setExits ("north", vSystem1_1);
            vSystem2.setExits ("east", vSystem2_1);
            vSystem2.setExits ("west", vPortal);
            vSystem2.setItem ("Hologram", aItems.get("Hologram"));
            
            vSystem3.setExits ("east", vSystem3_1);
            vSystem3.setExits ("west", vPortal);
            vSystem3.setItem ("Leaf", aItems.get("Leaf"));
        
            vSystem1_1.setExits ("east", vSystem1_2);
            vSystem1_1.setExits ("south", vSystem2);
            vSystem1_1.setExits ("west", vSystem1);
            vSystem1_1.setItem ("Upgraded_Hold", aItems.get("Upgraded_Hold"));
        
            vSystem2_1.setExits ("north", vSystem1_2);
            vSystem2_1.setExits ("east", vSystem2_2);
            vSystem2_1.setExits ("south", vSystem3_1);
            vSystem2_1.setExits ("west", vSystem2);
            vSystem2_1.setItem ("Holocron", aItems.get("Holocron"));
        
            vSystem3_1.setExits ("north", vSystem2_1);
            vSystem3_1.setExits ("east", vSystem3_2);
            vSystem3_1.setExits ("west", vSystem3);
            vSystem3_1.setItem ("Book", aItems.get("Book"));
            vSystem3_1.setItem ("Shard", aItems.get("Shard"));
            
            vSystem1_2.setExits ("east", vSystem1_3);
            vSystem1_2.setExits ("south", vSystem2_1);
            vSystem1_2.setExits ("west", vSystem1_1);
            vSystem1_2.setItem ("FuelTank_M", aItems.get("FuelTank_M"));
        
            vSystem1_3.setExits ("east", vSystem4);
            vSystem1_3.setExits ("south", vSystem2_2);
            vSystem1_3.setExits ("west", vSystem1_2);
        
            vSystem2_2.setExits ("north", vSystem1_3);
            vSystem2_2.setExits ("south", vSystem3_2);
            vSystem2_2.setExits ("west", vSystem2_1);
            vSystem2_2.setExits ("east", vSystem2_3);
            vSystem2_2.setItem ("FuelTank_G", aItems.get("FuelTank_G"));
            vSystem2_2.setItem ("FuelTank_L", aItems.get("FuelTank_L"));
            
            vSystem2_3.addExit (vSystem1);
            vSystem2_3.addExit (vSystem2);
            vSystem2_3.addExit (vSystem3);
            vSystem2_3.addExit (vSystem4);
        
            vSystem3_2.setExits ("north", vSystem2_2);
            vSystem3_2.setExits ("east", vSystem4);
            vSystem3_2.setExits ("west", vSystem3_1);
            vSystem3_2.setItem ("FuelTank_M", aItems.get("FuelTank_M"));
        
            vSystem4.setExits ("north", vSystem1_3);
            vSystem4.setExits ("east", vSystem5);
            vSystem4.setExits ("south", vSystem3_2);
            vSystem4.setItem ("FuelTank_L", aItems.get("FuelTank_L"));
        
            vSystem5.setExits ("north", vSystem6_1);
            vSystem5.setExits ("south", vSystem7_1);
            vSystem5.setItem ("FuelTank_G", aItems.get("FuelTank_G"));
        
            vSystem6_1.setExits ("east", vSystem6_2);
        
            vSystem7_1.setExits ("east", vSystem7_2);
        
            vSystem6_2.setExits ("east", vSystem6_3);
        
            vSystem7_2.setExits ("east", vSystem7_3);
        
            vSystem6_3.setExits ("south", vSystem8);
        
            vSystem7_3.setExits ("north", vSystem8);
        
            vSystem8.setExits ("down", vTerre);
        
            vTerre.setExits ("up", vSystem8);
            
        } // createRooms()
        
        /**
         * Allows the player to move from a Room to another
         * @param pCommand The command one has to enter in order to move.
         */
        private void goRoom( final Command pCommand)
        {
            if (pCommand.hasSecondWord()==false)
            {
                this.aGui.println("Go where ?");
                return;
            }
            String vDirection = pCommand.getSecondWord();
            Room vNextRoom = this.aPlayer.getCurrentRoom().getExit(vDirection);
        
            if (vNextRoom == null) {
               this.aGui.println ("There is nothing in this way...\n Or you misspelled your direction !");
            }
            else {
                if (this.checkFuel())
                {
                    this.aPlayer.move(vNextRoom);
                    Room vNewRoom = this.aPlayer.getCurrentRoom();
                    printLocationInfo();
                    if (vNewRoom.getImageName() != null )
                        this.aGui.showImage ( vNewRoom.getImageName() );
                }
            }
        } // goRoom()
    
        /**
         * Displays the message of the beginnig.
         */
        private void printWelcome()
        {
            this.aGui.println("As the Commander of the mission Hermes, your lieutenant hails you : ");
            this.aGui.println("Greetings Commander !");
            this.aGui.println("Our expedition to find the signal studied on Earth centuries ago has come close to drama.");
            this.aGui.println("Indeed, we have made contact with an extraterrestrial civilization. By going through this portal, you have saved our lives commander.");
            this.aGui.println("Our men are still shocked by the loss of our two other vessels...");
            this.aGui.println("But we need to keep moving ! It seems this portal has transported us far away from Earth...");
            this.aGui.println("But I'm sure with your command, we will be able to see Earth again and tell them about our discovery !");
            this.aGui.println("Remember if you encounter any trouble to type 'help' on your command board");
            this.aGui.println("Let's get started by looking around in this system !");
            printLocationInfo();
            if ( aRooms.get("Portal").getImageName() != null )
                {this.aGui.showImage ( aRooms.get("Portal").getImageName() );}
        } // printWelcome()
    
        /**
         * Displays the information of the current visited Room
         * and the possible destinations.
         */
        private void printLocationInfo()
        {
            Room vCurrentRoom = this.aPlayer.getCurrentRoom();
            this.aGui.println(vCurrentRoom.getLongDescription());
            this.aGui.println(vCurrentRoom.getItemsString());
            this.aGui.println("Jumps remaining : "+this.aPlayer.getFuel());
        } // printLocationInfo()
    
        /**
         * Displays the Help message the 'Help' command has to display.
         */
        private void printHelp()
        {
            this.aGui.println("You are lost. You are alone.");
            this.aGui.println(" You want to go home and rethink your life.");
            this.aGui.println("Your command words are: ");
            this.aGui.println(this.aParser.showCommands());
        } // printHelp()
    
        /**
         * Allows one to quit the Game.
         * @param pCommand The command to enter to quit the game.
         * @return If the game has been quitted or not.
         */
        private boolean quit(final Command pCommand)
        {
            if (pCommand.hasSecondWord()==true)
            {
                this.aGui.println("Go Where ?");
                return false;
            }
            return true;
        } // quit()
    
        /**
         * Allows the commands to be executed.
         * @param pCommandLine The command to be executed.
         */
        public void interpretCommand(final String pCommandLine)
        {
            this.aGui.println( "> " + pCommandLine );
            Command vCommand = this.aParser.getCommand (pCommandLine );
            
            if ( vCommand.isUnknown() ) 
            {
                this.aGui.println( "I don't know what you mean...");
                return;
            }   
            String vCommandWord = vCommand.getCommandWord();
            if ( vCommandWord.equals( "help" ))
                this.printHelp();
            else if ( vCommandWord.equals( "go" ))
            {
                if (this.aPlayer.getInventoryWeight() <= this.aPlayer.getMaxWeight())
                    {this.goRoom( vCommand );
                    this.checkWinCondition();}
                else
                    this.aGui.println( "Your ship is too heavy to move ! You should drop some of your items...");
            }
            else if ( vCommandWord.equals( "quit" ))
            {
                if ( vCommand.hasSecondWord() )
                    this.aGui.println( "Quit what?" );
                else
                    this.endGame();
            }
            else if (vCommandWord.equals( "look" ))
                this.look();
            else if (vCommandWord.equals( "back" ))
            {
                if (vCommand.hasSecondWord() == true)
                    this.aGui.println("This command doesn't have a second word!");
                else if (this.aPlayer.getInventoryWeight() > this.aPlayer.getMaxWeight())
                    this.aGui.println("Your ship is too heavy to move ! You should drop some of your items...");
                else if (this.aPlayer.isBackEmpty())
                    this.aGui.println("How can you back without having moved somewhere before?");
                else
                {
                    Stack vPreviousRooms = this.aPlayer.getPreviousRooms();
                    Room vPreviousRoom =(Room) vPreviousRooms.peek();
                    if (this.aPlayer.getCurrentRoom().isExit(vPreviousRoom))
                        this.back();
                    else
                        this.aGui.println("You cannot go back to the previous system !");
                }  
            }
            else if (vCommandWord.equals( "test" ))
            {
                if (vCommand.hasSecondWord())
                    this.test(vCommand.getSecondWord());
                else
                    this.aGui.println( "I don't know what you mean...");
            }
            else if (vCommandWord.equals( "take" ))
            {
                if (vCommand.hasSecondWord())
                {
                    String vItemString = vCommand.getSecondWord();
                    if (!(this.aPlayer.getCurrentRoom().getRoomItems().checkItem(vItemString)))
                        this.aGui.println( "This is not here !");
                    else
                    {   
                        this.aPlayer.take(vItemString);
                        this.aGui.println( "You took the "+vItemString);
                        this.aGui.println( "Inventory Weight : "+this.aPlayer.getInventoryWeight()+" / "+this.aPlayer.getMaxWeight()); 
                        if (this.aPlayer.getInventoryWeight() > this.aPlayer.getMaxWeight())
                            this.aGui.println( "Your ship is too heavy ! You should drop some of your items...");
                    }
                }
                else
                    this.aGui.println( "Take what?" );
            }
            else if (vCommandWord.equals( "drop" ))
            {
                if(vCommand.hasSecondWord())
                {
                    if (!(this.aPlayer.getInventory().checkItem(vCommand.getSecondWord())))
                        this.aGui.println ("You don't have this on you!");
                    else
                    {
                        this.aPlayer.drop(vCommand.getSecondWord());
                        this.aGui.println ("You dropped the "+vCommand.getSecondWord());
                    }
                }
                else
                    this.aGui.println( "Drop what?" );
            }
            else if (vCommandWord.equals( "Inventory" ))
            {
                this.aGui.println( this.aPlayer.getPlayerInventory());
                this.aGui.println( "Inventory Weight : "+this.aPlayer.getInventoryWeight()+" / "+this.aPlayer.getMaxWeight()); 
            }
            else if (vCommandWord.equals( "use" ))
            {
                if (vCommand.hasSecondWord())
                {
                    this.use(vCommand.getSecondWord());
                    this.aGui.println( "Inventory Weight : "+this.aPlayer.getInventoryWeight()+" / "+this.aPlayer.getMaxWeight()); 
                }
            }
            else if (vCommandWord.equals( "charge" ))
            {
                if (vCommand.hasSecondWord() == true)
                    this.aGui.println("This command doesn't have a second word !");
                else
                    if (this.aPlayer.getInventory().checkItem("StarGate"))
                    {
                        this.aPlayer.charge();
                        this.aGui.println("The current system has been saved in your stargate, erasing the previous one.");
                    }
                    else
                        this.aGui.println("You do not have a StarGate !");
            }
            else if (vCommandWord.equals( "warp" ))
            {
                if (vCommand.hasSecondWord() == true)
                    this.aGui.println("This command doesn't have a second word !");
                else
                    if (this.aPlayer.getInventory().checkItem("StarGate"))
                    {
                        if(this.aPlayer.isStarGateEmpty())
                            this.aGui.println("There is no system saved in your StarGate !");
                        else
                        {
                            if (this.checkFuel())
                            {
                                this.aPlayer.warp();
                                this.aPlayer.getStackEmpty();
                                printLocationInfo();
                                if (this.aPlayer.getCurrentRoom().getImageName() != null )
                                    this.aGui.showImage ( this.aPlayer.getCurrentRoom().getImageName());
                            }
                        }
                    }
            }
            else if (vCommandWord.equals("alea") & aTestMode)
            {
                if (this.aPlayer.getCurrentRoom() instanceof TransporterRoom)
                {
                    if (vCommand.hasSecondWord())
                    {
                        try{
                        int a = Integer.parseInt(vCommand.getSecondWord());
                        TransporterRoom vTransporterRoom = (TransporterRoom) this.aPlayer.getCurrentRoom();
                        vTransporterRoom.getRoomRandomizer().setAleaInt(a);
                        }
                        catch (final java.lang.NumberFormatException pNFE)
                        {
                            this.aGui.println(" Please enter an Integer after alea. ");
                        }
                    }
                    else
                    {
                        TransporterRoom vTransporterRoom = (TransporterRoom) this.aPlayer.getCurrentRoom();
                        vTransporterRoom.getRoomRandomizer().setAleaInt(-1);
                    }
                }
            }
                
    } // interpretCommand()
    
    /**
     *Allows one to look at the Room.
     */
    private void look()
    {
        this.aGui.println(this.aPlayer.getCurrentRoom().getLongDescription());
        this.aGui.println(this.aPlayer.getCurrentRoom().getItemsString());
    } // look()
    
    /**
     * Checks the fuel of the Player and end the Game if he is blocked because he lacks fuel to move
     * @return true if the player has enough fuel to move or false either.
     */
    private boolean checkFuel()
    {
       if (this.aPlayer.getFuel() <= 0)
            {
                if (this.aPlayer.getCurrentRoom().getRoomItems().checkItem("FuelTank_L") | this.aPlayer.getCurrentRoom().getRoomItems().checkItem("FuelTank_M") | this.aPlayer.getCurrentRoom().getRoomItems().checkItem("FuelTank_G"))
                    this.aGui.println ("You don't have enough fuel ! You should look around and pick some first.");
                else
                {
                    this.aGui.println ("You are condamned here as you do not have enough fuel to jump. Your engine broke because of it... The game will end now.");
                    this.endGame();
                }
                return false;
            }
       return true;
    } // checkFuel()
    
    /**
     * Checks the winning conditions.
     */
    private void checkWinCondition()
    {
        if (this.aPlayer.getCurrentRoom()==(this.aRooms.get("Earth")))
        {
            if(this.aPlayer.getInventory().checkItem("Stone") & this.aPlayer.getInventory().checkItem("Leaf") & (this.aPlayer.getInventory().checkItem("Holocron") | this.aPlayer.getInventory().checkItem("Hologram")) & (this.aPlayer.getInventory().checkItem("Book") | this.aPlayer.getInventory().checkItem("Shard")))
            {   this.aGui.println("Congratulations ! Your proofs of the extraterrestrial life are enough for the scientists to validate it ! Now, the humanity will seek to make contact with them in order to thank them for their help..."); }
            else
            {   this.aGui.println("You made it home, but the scientists are untrustful of your journey... Your 'proofs' weren't enough to convince them...");}
            this.endGame();
        }
    } // checkWinCondition()
    
    /**
     * Allows the player to use an item.
     * @param pItemName The name of the item the player wants to use.
     */
    private void use(String pItemName)
    {
        if (this.aPlayer.getInventory().checkItem(pItemName))
            if (pItemName.equals("Upgraded_Hold"))
            {
                this.aPlayer.setMaxWeight(50);
                this.aPlayer.getInventory().removeItem("Upgraded_Hold");
                this.aGui.println("Your Hold has been upgraded.");
            }
            else if (pItemName.equals("FuelTank_L"))
            {
                this.aPlayer.setFuel(this.aPlayer.getFuel()+1);
                this.aPlayer.getInventory().removeItem("FuelTank_L");
                this.aGui.println("Jumps remaining : "+this.aPlayer.getFuel());
            }
            else if (pItemName.equals("FuelTank_M"))
            {
                this.aPlayer.setFuel(this.aPlayer.getFuel()+3);
                this.aPlayer.getInventory().removeItem("FuelTank_M");
                this.aGui.println("Jumps remaining : "+this.aPlayer.getFuel());
            }
            else if (pItemName.equals("FuelTank_G"))
            {
                this.aPlayer.setFuel(this.aPlayer.getFuel()+5);
                this.aPlayer.getInventory().removeItem("FuelTank_G");
                this.aGui.println("Jumps remaining : "+this.aPlayer.getFuel());
            }
            else
                this.aGui.println( "This item cannot be used !" );
        else
            {
                this.aGui.println("You do not have this in your Inventory !");
            }
    } //eat()
    
    /**
     * Allows one to move back to a the previous Room he visited.
     */
    
    private void back()
    {
        Stack vPreviousRooms = this.aPlayer.getPreviousRooms();
        if (vPreviousRooms.empty())
            {this.aGui.println(" You should move first !");
            return;}
        if (this.checkFuel())
        {
        this.aPlayer.moveBack();
        printLocationInfo();
        if (this.aPlayer.getCurrentRoom().getImageName() != null )
            this.aGui.showImage ( this.aPlayer.getCurrentRoom().getImageName() );
        }
    } // back()
    
    /**
     * Tests the different commands specified in a .txt file
     * @param pFileName The name of the .txt File.
     */
    public void test(final String pFileName)
    {
        this.aTestMode = true;
         Scanner vSc;
        try {
            vSc = new Scanner( new File( pFileName+".txt" ) );
            while ( vSc.hasNextLine() ) {
                String vLine = vSc.nextLine();
                this.interpretCommand( vLine );
            }
        } // try
        catch ( final FileNotFoundException pFNFE ) {
        } // catch
        this.aTestMode = false;
    } // test()
    
    /**
     * Allows one to quit the game and prints the 'quit' message.
     */
    private void endGame()
    {
        this.aGui.println(" Thank you Commander, see you later !");
        this.aGui.enable( false );
    } // endGame()
    
} // GameEngine
