package com.battleship.model;

import java.io.Serializable;

public interface GeoMap extends Serializable
{

    static final long serialVersionUID = 1L;

    public default void print() {
        System.out.println("Its a default print() from interface GeoMap");
    }
}
