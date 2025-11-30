import Controller.StatusController;
import Controller.TargetGridController;
import Controller.OceanGridController;
import Model.Game;
import View.GameWindow;

public class App {
    public static void main(String[] args) throws Exception {
        //1. build out the view layer

        GameWindow gameWindow = new GameWindow("Battleship");

        // 2. build out the model layer
        Game game = new Game();

        // 3. Connect Models and Views with Controllers
        TargetGridController tgc = new TargetGridController(gameWindow.getTargetPanel(), game.getHumanTargetGrid());
        OceanGridController ogc = new OceanGridController(gameWindow.getOceanPanel(), game.getHumanOceanGrid());
        StatusController sc = new StatusController(gameWindow.getStatusPane(), game);

        gameWindow.setVisible(true);
        // Tells the Layout manager to do all the layout
        gameWindow.pack();
    }
}
   