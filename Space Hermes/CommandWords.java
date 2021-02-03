 


/**
 * This class is part of the "Space Hermes" application. 
 * "Space Hermes" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration table of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kolling and David J. Barnes + D.Bureau + J.Gavalda
 * @version 2008.03.30 + 2013.09.15 + 2019.10.29
 */
public class CommandWords
{
    // tableau constant qui contient tous les mots de commande valides
    private static final String[] sValidCommands = {
        "go", "quit", "help", "look", "use", "back", "test", "take", "drop", "Inventory", "charge", "warp", "alea"
    };

    /**
     * Default constructor.
     */
    public CommandWords()
    {
        // rien a faire pour le moment...
    } // CommandWords()

    /**
     * Checks if a String is part of the validCommands.
     * @param pString The String to test
     * @return True if pString is a valid command, neither returns false.
     */
    public boolean isCommand( final String pString )
    {
        // pour chacune des commandes valides (du tableau constant)
        for ( int i=0; i<sValidCommands.length; i++ ) {
            // si elle est egale a pString
            if ( sValidCommands[i].equals( pString ) )
                return true;
        } // for
        // si nous arrivons la, c'est que la commande
        // n'a pas ete trouvee dans le tableau
        return false;
    } // isCommand()
    
    /**
     * Gets all the valid commands
     * @return A String containing all the valid commands.
     */
    public String getCommandList()
    {   
        String vString="";
        for(String vCommand : sValidCommands)
        {
            vString = vString + vCommand + " ";
        }
        return vString;
    } // getCommandList()
} // CommandWords