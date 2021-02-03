import java.util.HashMap;
 /**
 * Class which creates the Game.
 * @author Jason GAVALDA
 * @version 29/10/2019
 */
public class Game
{
        private UserInterface aGui;
        private GameEngine aEngine;
        
        /**
         * Creates the game, its map and its interface.
         */
        public Game()
        {
            this.aEngine = new GameEngine();
            this.aGui = new UserInterface ( this.aEngine );
            this.aEngine.setGUI( this.aGui );
        }
}   // Game
