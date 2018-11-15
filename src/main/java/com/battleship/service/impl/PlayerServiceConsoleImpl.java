package com.battleship.service.impl;

import java.util.List;

import com.battleship.model.Player;
import com.battleship.persistence.PlayerDao;
import com.battleship.persistence.impl.PlayerDaoImpl;
import com.battleship.service.PlayerService;
import com.battleship.service.UIservice;

public class PlayerServiceConsoleImpl implements PlayerService
{

    private PlayerDao playerdao;

    private UIservice ui;

    public PlayerServiceConsoleImpl(UIservice ui)
    {
        this.playerdao = new PlayerDaoImpl();
        this.ui = ui;
    }

    @Override
    public Player createPlayer()
    {
        ui.userIntroMessage();
        String name = ui.readUserInputString();
        Player selectedUser = new Player(name);
        playerdao.persistPlayer(selectedUser);
        return selectedUser;
    }

    @Override
    public void savePlayer(Player player)
    {
        playerdao.updatePlayer(player);
        ui.exitMessage();
    }

    //TODO: Need to optimize this method.  
    public Player selectPlayer()
    {
        List<Player> users = playerdao.getPlayers();
        if (users != null && users.size() > 0) {
            ui.displayUsers(users);

            int selected = ui.readUserInputInt();
            if (selected > 0 && selected <= users.size()) {
                return users.get(selected - 1);
            } else if (selected == users.size() + 2) {
                deletePlayer();
                return selectPlayer();
            } else if (selected == users.size() + 1) {
                return createPlayer();
            } else {
                ui.displayInvalidOptionMessage();
            }
        }
        return createPlayer();
    }

    private void deletePlayer()
    {
        ui.confirmUserDeletion();
        String name = ui.readUserInputString();
        playerdao.deletePlayer(name);
    }

}
