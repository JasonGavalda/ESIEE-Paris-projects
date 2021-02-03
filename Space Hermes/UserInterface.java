import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.net.URL;

/**
 * This class implements a simple graphical user interface with a text entry
 * area, a text output area and an optional image.
 * 
 * @author Michael Kolling + Jason Gavalda
 * @version 1.0 (Jan 2003) DB edited (2019) + 8/01/2020
 */
public class UserInterface implements ActionListener
{
    private GameEngine aEngine;
    private JFrame     aMyFrame;
    private JTextField aEntryField;
    private JTextArea  aLog;
    private JLabel     aImage;
    private JButton    aButton;
    private JButton    aButton1;
    private JButton    aButton2;
    private JButton    aButton3;
    private JButton    aButton4;
    private JButton    aButton5;
    private JButton    aButton6;
    private JButton    aButton7;
    private JButton    aButton8;
    private JButton    aButton9;
    private JButton    aButton10;
    private JButton    aButton11;

    /**
     * Construct a UserInterface. As a parameter, a Game Engine
     * (an object processing and executing the game commands) is
     * needed.
     * 
     * @param pGameEngine  The GameEngine object implementing the game logic.
     */
    public UserInterface( final GameEngine pGameEngine )
    {
        this.aEngine = pGameEngine;
        this.createGUI();
    } // UserInterface(.)

    /**
     * Print out some text into the text area.
     * @param pText The Text to be displayed.
     */
    public void print( final String pText )
    {
        this.aLog.append( pText );
        this.aLog.setCaretPosition( this.aLog.getDocument().getLength() );
    } // print(.)

    /**
     * Print out some text into the text area, followed by a line break.
     * @param pText The text to be displayed.
     */
    public void println( final String pText )
    {
        this.print( pText + "\n" );
    } // println(.)

    /**
     * Show an image file in the interface.
     * @param pImageName The Name of the image to be displayed.
     */
    public void showImage( final String pImageName )
    {
        String vImagePath = "" + pImageName; // to change the directory
        URL vImageURL = this.getClass().getClassLoader().getResource( vImagePath );
        if ( vImageURL == null )
            System.out.println( "Image not found : " + vImagePath );
        else {
            ImageIcon vIcon = new ImageIcon( vImageURL );
            this.aImage.setIcon( vIcon );
            this.aMyFrame.pack();
        }
    } // showImage(.)

    /**
     * Enable or disable input in the input field.
     * @param pOnOff A boolean determinating the authorization of the field to be displayed.
     */
    public void enable( final boolean pOnOff )
    {
        this.aEntryField.setEditable( pOnOff ); // enable/disable
        
        if ( ! pOnOff ) { // disable
            this.aEntryField.getCaret().setBlinkRate( 0 ); // cursor won't blink
            this.aEntryField.removeActionListener( this ); // won't react to entry
            this.aButton.removeActionListener ( this );
            this.aButton1.removeActionListener ( this );
            this.aButton2.removeActionListener ( this );
            this.aButton3.removeActionListener ( this );
            this.aButton3.removeActionListener ( this );
            this.aButton4.removeActionListener ( this );
            this.aButton5.removeActionListener ( this );
            this.aButton6.removeActionListener ( this );
            this.aButton7.removeActionListener ( this );
            this.aButton8.removeActionListener ( this );
            this.aButton9.removeActionListener ( this );
            this.aButton10.removeActionListener ( this );
            this.aButton11.removeActionListener ( this );            
        }
    } // enable(.)

    /**
     * Set up graphical user interface.
     */
    private void createGUI()
    {
        this.aMyFrame = new JFrame( "Hermes" );
        this.aEntryField = new JTextField( 34 );

        this.aLog = new JTextArea();
        this.aLog.setEditable( false );
        JScrollPane vListScroller = new JScrollPane( this.aLog );
        vListScroller.setPreferredSize( new Dimension(800, 300) );
        vListScroller.setMinimumSize( new Dimension(100,100) );

        JPanel vPanel = new JPanel();
        JPanel vPanel1 = new JPanel();
        JPanel vPanel2 = new JPanel();
        
        this.aImage = new JLabel();
        
        this.aButton = new JButton( "warp" );
        this.aButton1 = new JButton( "charge" );
        this.aButton2 = new JButton( "Inventory");
        this.aButton3 = new JButton( "back" );
        this.aButton4 = new JButton( "look" );
        this.aButton5 = new JButton( "go north" );
        this.aButton6 = new JButton( "go east" );
        this.aButton7 = new JButton( "go south" );
        this.aButton8 = new JButton( "go west" );
        this.aButton9 = new JButton( "go up" );
        this.aButton10 = new JButton( "go down" );
        this.aButton11 = new JButton( "quit" );
        
        vPanel.setLayout( new BorderLayout() ); // ==> only five places
        vPanel1.setLayout( new GridLayout(2,3) );
        vPanel2.setLayout( new GridLayout(2,3) );
        
        vPanel1.add(this.aButton3);
        vPanel1.add(this.aButton4);
        vPanel1.add(this.aButton2);
        vPanel1.add(this.aButton);
        vPanel1.add(this.aButton1);
        vPanel1.add(this.aButton11);
        
        vPanel2.add(this.aButton10);
        vPanel2.add(this.aButton5);
        vPanel2.add(this.aButton9);
        vPanel2.add(this.aButton8);
        vPanel2.add(this.aButton7);
        vPanel2.add(this.aButton6);
        
        vPanel.add( this.aImage, BorderLayout.NORTH );
        this.aImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        vPanel.add( vListScroller, BorderLayout.CENTER );
        vPanel.add( this.aEntryField, BorderLayout.SOUTH );
        vPanel.add(vPanel1, BorderLayout.EAST );
        vPanel.add(vPanel2, BorderLayout.WEST );

        this.aMyFrame.getContentPane().add( vPanel, BorderLayout.CENTER );

        // add some event listeners to some components
        this.aEntryField.addActionListener( this );
        this.aButton.addActionListener ( this );
        this.aButton1.addActionListener ( this );
        this.aButton2.addActionListener ( this );
        this.aButton3.addActionListener ( this );
        this.aButton4.addActionListener ( this );
        this.aButton5.addActionListener ( this );
        this.aButton6.addActionListener ( this );
        this.aButton7.addActionListener ( this );
        this.aButton8.addActionListener ( this );
        this.aButton9.addActionListener ( this );
        this.aButton10.addActionListener ( this );
        this.aButton11.addActionListener ( this );
        
        // to end program when window is closed
        this.aMyFrame.addWindowListener( new WindowAdapter() {
            public void windowClosing(WindowEvent e) { System.exit(0); }
        } );

        this.aMyFrame.pack();
        this.aMyFrame.setVisible( true );
        this.aEntryField.requestFocus();
    } // createGUI()

    /**
     * Actionlistener interface for entry textfield.
     * @param pE The action to be performed.
     */
    public void actionPerformed( final ActionEvent pE ) 
    {
        Object vSource = pE.getSource();
        
        if (vSource == this.aButton | vSource == this.aButton1 | vSource == this.aButton2 | vSource == this.aButton3 | vSource == this.aButton4 | vSource == this.aButton5 | vSource == this.aButton6 | vSource == this.aButton7 | vSource == this.aButton8 | vSource == this.aButton9 | vSource == this.aButton10 | vSource == this.aButton11)
            this.aEngine.interpretCommand(pE.getActionCommand());
        else
        this.processCommand();
    } // actionPerformed(.)

    /**
     * A command has been entered. Read the command and do whatever is 
     * necessary to process it.
     */
    private void processCommand()
    {
        String vInput = this.aEntryField.getText();
        this.aEntryField.setText( "" );

        this.aEngine.interpretCommand( vInput );
    } // processCommand()
} // UserInterface 
