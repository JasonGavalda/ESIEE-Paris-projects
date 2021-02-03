 

/** Class which deals with what a command contains
 * @author Jason GAVALDA
 * @version 29.10.19
 * 
 */
public class Command
{
    private String aCommandWord;
    private String aSecondWord;
    /**
     * Creates a command composed of the words pCommandWord and pSecondWord.
     * @param pCommandWord The first word of the command : the action.
     * @param pSecondWord The second word of the command : the complement of the action.
     */    
    public Command( final String pCommandWord, final String pSecondWord)
    {  
        this.aCommandWord=pCommandWord;
        this.aSecondWord=pSecondWord;
    } //Command

    /**
     * Returns the first word of the command.
     * @return The first word of the command.
     */
    public String getCommandWord()
    {
        return this.aCommandWord;
    } //getCommandWord

    /**
     * Returns the second word of a command.
     * @return The second word of a command.
     */
    public String getSecondWord()
    {
        return this.aSecondWord;
    } // getSecondWord

    /**
     * @return True if the command has a second word, neither false.
     */
    public boolean hasSecondWord ()
    {
        return (this.aSecondWord!=null);
    } //hasSecondWord

    /**
     * @return True if the command is unknown, neither false.
     */
    public boolean isUnknown ()
    {
        return (this.aCommandWord==null);
    } // isUnknown
} // Command
