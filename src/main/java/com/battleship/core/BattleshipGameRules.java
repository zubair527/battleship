package com.battleship.core;

import com.battleship.model.GameCharacter;
import com.battleship.model.Player;
import com.battleship.model.Ocean;
import com.battleship.persistence.EnemyShipDao;
import com.battleship.persistence.impl.EnemyShipDaoImpl;
import com.battleship.service.UIservice;
import com.battleship.utils.Commands;
import com.battleship.utils.ConfigHelper;

public class BattleshipGameRules implements GameRules
{
    private EnemyShipDao riddlesDao;

    private UIservice ui;

    public BattleshipGameRules(UIservice ui)
    {
        riddlesDao = new EnemyShipDaoImpl();
        this.ui = ui;
    }

    @Override
    public boolean evaluateGameRule(Player player)
    {
        boolean isGameWon = false;
        if (((Ocean) player.getMap()).getCurrentRoom().getOccupiedBy() == GameCharacter.HEALTHY_PEOPLE) {
            ui.displayWinMessage();
            isGameWon = true;
        } else if (((Ocean) player.getMap()).getCurrentRoom().getOccupiedBy() == GameCharacter.ENEMY) {
            ui.displayMonster();

            System.out.println();
            System.out.println(riddlesDao.getEnemyShip(player.getLevel()));
            String userAnswer = "";
            do {
                ui.displayAnswerPrompt();
                userAnswer = ui.readUserInputString();
                if (userAnswer.equalsIgnoreCase(Commands.GIVEUP)) {
                    double healthLost = attachEnemy();
                    player.modifyHealth((int) healthLost);
                    player.IncLevel();
                    if (player.getHealth() == 0) {
                        ui.displayLooseMessage();
                        isGameWon = true;
                    }
                    break;
                } else if (userAnswer.equalsIgnoreCase(Commands.HELP)) {
                    ui.printHelp();
                } else if (userAnswer.equalsIgnoreCase(Commands.PAY)) {
                    if (player.getGems() == 0) {
                        ui.displayNoGemsMessage();
                    } else {
                        player.modifyGems(-1);
                        System.out.println(riddlesDao.getEnemyshipLocationHint(player.getLevel()));
                    }
                } else if (riddlesDao.checkEnemyShipBearing(userAnswer, player.getLevel())) {
                    player.IncLevel();
                    player.modifyGems(1);
                    ui.displayCorrectAnswerMessage();
                    break;
                } else {
                    ui.displayInCorrectAnswerMessage();
                }
            } while (true);
            System.out.println(player);
        }
        return isGameWon;
    }

    private double attachEnemy()
    {
        System.out.println(ConfigHelper.getMessageForKey("msg.attack.enemy"));
        return Math.random() * 25 - 40;
    }

}
