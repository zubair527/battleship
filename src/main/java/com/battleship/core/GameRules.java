package com.battleship.core;

import com.battleship.model.Player;

public interface GameRules {

	boolean evaluateGameRule(Player player);
	
}
