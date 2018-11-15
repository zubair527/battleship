package com.battleship.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.battleship.persistence.EnemyShipDao;

public class EnemyShipDaoImpl implements EnemyShipDao {

	private Connection dbConnection;

	public EnemyShipDaoImpl() {
	    this.dbConnection = HsqlDBConnection.getConnection();
	}

	@Override
	public String getEnemyShip(int level) {
		try {
			String getRiddlesql = "select question from enemy_ship where difficulty=?";
			PreparedStatement stmt = dbConnection.prepareStatement(getRiddlesql);
			stmt.setInt(1, level);
			ResultSet results = stmt.executeQuery();
			if (results.next()) {
				return results.getString("question");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean checkEnemyShipBearing(String answer, int level) {
		return answer.equalsIgnoreCase(getAnswer(level));
	}

	private String getAnswer(int level) {
		try {
			String getRiddlesql = "select answer from enemy_ship where difficulty=?";
			PreparedStatement stmt = dbConnection.prepareStatement(getRiddlesql);
			stmt.setInt(1, level);
			ResultSet results = stmt.executeQuery();
			if (results.next()) {
				return results.getString("answer");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getEnemyshipLocationHint(Integer level) {
		String answer = getAnswer(level);
		StringBuilder hint = new StringBuilder();
		for (int i = 0; i < answer.length(); i++) {
			int seed = i+answer.length();
			if ((((int)Math.random()*100)+seed)%2==0) {
				hint.append(answer.charAt(i)+" ");
			} else {
				hint.append("_ ");
			}
		}
		return hint.toString();
	}

}
