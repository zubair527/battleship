package com.battleship;

import java.util.Properties;

import com.battleship.core.GameRules;
import com.battleship.core.BattleshipGameRules;
import com.battleship.model.Ocean;
import com.battleship.model.Player;
import com.battleship.persistence.impl.HsqlDBConnection;
import com.battleship.player.action.AbstractAction;
import com.battleship.player.action.impl.PlayerActionImpl;
import com.battleship.service.UIservice;
import com.battleship.service.PlayerService;
import com.battleship.service.impl.UIserviceConsoleImpl;
import com.battleship.service.impl.PlayerServiceConsoleImpl;
import com.battleship.utils.ConfigHelper;

/**
 * @author zubair
 * Class contains all the objects necessary to run the game. 
 */
public class GamePlay
{
    private UIservice ui = null;

    private Properties messages = null;

    private PlayerService usermanager = null;

    private GameRules gameRules = null;

    private Player player = null;

    private AbstractAction<Player> actions = null;

    public GamePlay()
    {

        this.ui = new UIserviceConsoleImpl();
        this.messages = ConfigHelper.loadGameMessages();
        this.usermanager = new PlayerServiceConsoleImpl(ui);
        this.gameRules = new BattleshipGameRules(ui);
        this.actions = new PlayerActionImpl();
    }

    /**
     * Method contains the Game loop.  
     */
    public void startGame()
    {

        try {
            ui.printStory();
            player = usermanager.selectPlayer();
            boolean succeeded = false;

            System.out.println(messages.getProperty("msg.welcome"));
            do {
                player.getMap().print();
                ui.printInstructions();
                // returns true if user wants to quit
                if (isQuitCommand()) {
                    break;
                }
                succeeded = gameRules.evaluateGameRule(player);
            } while (!succeeded);
            if (succeeded) {
                player.setMap(new Ocean());
                player.modifyHealth(100 - player.getHealth());
                ui.displayFinishedGameMessage();
            } else {
                ui.displayGameSaveMessage();
            }
            usermanager.savePlayer(player);
        } finally {
            HsqlDBConnection.closeConnection();
        }
    }

    private boolean isQuitCommand()
    {

        boolean shallExit = false;
        String cmd = ui.readUserInputString();
        if (cmd.equalsIgnoreCase("QUIT") || cmd.equalsIgnoreCase("Q")) {
            shallExit = true;
        } else {
            switch (cmd.toUpperCase()) {
                case "NORTH":
                case "N":
                    // moveNorth();
                    actions.moveNorth(player, ui);
                    break;
                case "SOUTH":
                case "S":
                    actions.moveSouth(player, ui);
                    break;
                case "WEST":
                case "W":
                    actions.moveWest(player, ui);
                    break;
                case "EAST":
                case "E":
                    actions.moveEast(player, ui);
                    break;
                default:
                    ui.displayExploreError();
            }
        }
        // flush the last input character
        System.out.flush();
        return shallExit;
    }
}
