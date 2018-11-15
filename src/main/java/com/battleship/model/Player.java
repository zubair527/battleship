package com.battleship.model;

public class Player
{

    String name;

    GeoMap map;

    Integer level;

    Integer gems;

    Integer health;

    public Player(String name, int level, int gems, int health, Ocean palace)
    {
        this.name = name;
        this.level = level;
        this.gems = gems;
        this.health = health;
        this.map = palace;
    }

    public Player(String name)
    {
        this.name = name;
        this.level = 1;
        this.gems = 0;
        this.health = 100;
        this.map = new Ocean();
    }

    public void modifyHealth(int delta)
    {
        health += delta;
        if (health < 0)
            health = 0;
    }

    public void modifyGems(int number)
    {
        gems += number;
    }

    public String getName()
    {
        return name;
    }

    public GeoMap getMap()
    {
        return map;
    }

    public void setMap(GeoMap map)
    {
        this.map = map;
    }

    public Integer getLevel()
    {
        return level;
    }

    public void IncLevel()
    {
        this.level++;
    }

    public Integer getGems()
    {
        return gems;
    }

    public Integer getHealth()
    {
        return health;
    }
    
    @Override
    public String toString()
    {
        return this.getName() + "'s Health : " + this.getHealth() + " Gems: " + this.getGems();
    }

}
