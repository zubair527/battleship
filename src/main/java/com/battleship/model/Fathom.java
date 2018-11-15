package com.battleship.model;

import java.io.Serializable;

public class Fathom implements Serializable
{

    private static final long serialVersionUID = 1L;

    public GameCharacter getOccupiedBy()
    {
        return occupiedBy;
    }

    public void setOccupiedBy(GameCharacter you)
    {
        this.occupiedBy = you;
    }

    GameCharacter occupiedBy;
}
