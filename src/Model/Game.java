package Model;

import java.util.ArrayList;
import java.util.List;

import Easy.EasyAIPlayer;

public class Game implements ShotDelegate {
    private HumanPlayer humanPlayer;
    private Player computerPlayer;
    private Player currentPlayer;
    private Player otherPlayer;
    private List<StatusListener> listeners = new ArrayList<>();

    public Game() {

        // Human Player
        humanPlayer = new HumanPlayer("Human", new AutomaticShipFactory(), this);
        computerPlayer = new EasyAIPlayer(this);
        humanPlayer.placeShips();
        computerPlayer.placeShips();

        // starting
        currentPlayer = humanPlayer;
        otherPlayer = computerPlayer;
        currentPlayer.takeShot();
    }

    public void addListener(StatusListener toAdd) {
        listeners.add(toAdd);
    }

    protected void notifyStatus(String message) {
        for (StatusListener listener : listeners) {
            listener.statusMessage(message);
        }
    }

    public TargetGrid getHumanTargetGrid() {
        return humanPlayer.getTargetGrid();
    }

    public OceanGrid getHumanOceanGrid() {
        return humanPlayer.getOceanGrid();
    }

    public void handleShot(Coordinate shot, Object sender) {
        // must be this player's turn
        if (sender != currentPlayer) {
            // send message
            notifyStatus(String.format("Not your turn...%n"));
            return;
        }
        // process the shot
        ShotResult result = otherPlayer.receiveShot(shot);
        currentPlayer.receiveShotResult(result);
        // send message in the process shot
        notifyStatus(String.format("%s fires at %s ", currentPlayer.getName(), shot.toString()));

        switch(result) {
            case HIT -> notifyStatus(String.format("--> HIT%n"));
            case MISS -> notifyStatus(String.format("--> MISS%n"));
            case SUNK -> notifyStatus(String.format("--> SUNK... You sunk my %s%n", result.getShipName()));
            case INVALID -> {
                notifyStatus(String.format("--> INVALID%n"));
                // prompt the same player to shoot again and do not swap turns
                currentPlayer.takeShot();
                return;
            }
        }

        // check for end of game
        if (otherPlayer.shipsAreSunk()) {
            // send status message
            notifyStatus(String.format("Game Over: The winner is --> %s%n,", currentPlayer.getName()));
            return;
        }
        // swap turns
        Player temp = currentPlayer;
        currentPlayer = otherPlayer;
        otherPlayer = temp;

        currentPlayer.takeShot();

        // prompt current player to shoot

    }
}
