package com.battleship.persistence;

public interface EnemyShipDao {
	

	String getEnemyShip(int level);

	boolean checkEnemyShipBearing(String answer, int level);

	String getEnemyshipLocationHint(Integer level);

}
