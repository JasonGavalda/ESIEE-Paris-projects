import java.util.StringTokenizer;

/**
 * This class is part of the "Space Hermes" application. 
 * "Space Hermes" is a very simple, text based adventure game.  
 * 
 * This parser reads user input and tries to interpret it as an "Adventure"
 * command. Every time it is called it reads a line from the terminal and
 * tries to interpret the line as a two word command. It returns the command
 * as an object of class Command.
 *
 * The parser has a set of known command words. It checks user input against
 * the known commands, and if the input is not one of the known commands, it
 * returns a command object that is marked as an unknown command.
 * 
 * @author  Michael Kolling and David J. Barnes + D.Bureau + J.Gavalda
 * @version 2008.03.30 + 2013.09.15 + 2019.10.29
 */
public class Parser 
{
    private CommandWords aValidCommands;  // (voir la classe CommandWords)

    /**
     * Default Constructor.
     */
    public Parser() 
    {
        this.aValidCommands = new CommandWords();
        // System.in designe le clavier, comme System.out designe l'ecran
    } // Parser()

    /**
     * Accessor which gets an entered command.
     * @param pInputLine The entered command.
     * @return The next command from the user.
     */
    public Command getCommand( final String pInputLine ) 
    {
        String vWord1;
        String vWord2;

        StringTokenizer vTokenizer = new StringTokenizer( pInputLine );

        if ( vTokenizer.hasMoreTokens() ) {
            vWord1 = vTokenizer.nextToken();      // recupere le premier mot
        }
        else    {
            vWord1 = null;
        }

        if ( vTokenizer.hasMoreTokens() )    {
            vWord2 = vTokenizer.nextToken();      // recupere le deuxieme mot
        }
        else    {
            vWord2= null;
        }
        
        if ( this.aValidCommands.isCommand( vWord1 ) ) {
            return new Command( vWord1, vWord2 );
        }
        else {
            return new Command( null, vWord2 ); 
        }
    } // getCommand()
    
    /**
     * Prints the valid commands.
     * @return A string containing all the valid commands.
     */
    public String showCommands()
    {
        return this.aValidCommands.getCommandList();
    } // showCommands()
} // Parser