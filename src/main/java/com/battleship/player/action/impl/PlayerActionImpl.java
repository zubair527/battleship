package com.battleship.player.action.impl;

import com.battleship.model.Player;
import com.battleship.model.Ocean;
import com.battleship.player.action.AbstractAction;
import com.battleship.service.UIservice;
import com.battleship.utils.Constants;

public class PlayerActionImpl implements AbstractAction<Player>
{
    @Override
    public void moveNorth(Player player, UIservice msgDecorator) {
        if (((Ocean) player.getMap()).getCurrentFathomX() == 0) {
            msgDecorator.hitSeaBedMessage();
        } else {
            ((Ocean)player.getMap()).updateCurrentFathomX(-1);
        }

    }

    @Override
    public void moveSouth(Player player, UIservice msgDecorator) {
        if (((Ocean) player.getMap()).getCurrentFathomY() == Constants.rows - 1) {
            msgDecorator.hitSeaBedMessage();
        } else {
            ((Ocean)player.getMap()).updateCurrentFathomX(1);
        }

    }

    @Override
    public void moveWest(Player player, UIservice msgDecorator) {
        if (((Ocean)player.getMap()).getCurrentFathomY() == 0) {
            msgDecorator.hitSeaBedMessage();
        } else {
            ((Ocean)player.getMap()).updateCurrentFathomY(-1);
        }

    }

    @Override
    public void moveEast(Player player, UIservice msgDecorator) {
        if (((Ocean)player.getMap()).getCurrentFathomY() == Constants.columns - 1) {
            msgDecorator.hitSeaBedMessage();
        } else {
            ((Ocean)player.getMap()).updateCurrentFathomY(1);
        }
    }
}
