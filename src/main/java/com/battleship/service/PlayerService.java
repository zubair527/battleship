package com.battleship.service;

import com.battleship.model.Player;

public interface PlayerService
{

    public Player createPlayer();

    public Player selectPlayer();

    public void savePlayer(Player player);
}
