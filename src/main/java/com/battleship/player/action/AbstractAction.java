package com.battleship.player.action;

import com.battleship.service.UIservice;

public interface AbstractAction<T>
{
    public void moveNorth(T player, UIservice messageDecorator);

    public void moveSouth(T player, UIservice messageDecorator);

    public void moveWest(T player, UIservice messageDecorator);

    public void moveEast(T player, UIservice messageDecorator);

}
