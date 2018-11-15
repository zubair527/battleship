package com.battleship.service.impl;

import java.util.List;
import java.util.Scanner;

import com.battleship.model.Player;
import com.battleship.service.UIservice;
import com.battleship.utils.ConfigHelper;

public class UIserviceConsoleImpl implements UIservice
{

    Scanner scan = new Scanner(System.in);

    @Override
    public String readUserInputString()
    {
        String input = scan.nextLine();
        return input;
    }

    @Override
    public void printStory()
    {
    	System.out.println(ConfigHelper.getMessageForKey("msg.mission.breif"));
    	System.out.println(ConfigHelper.getMessageForKey("msg.dash.line"));
    }

    @Override
    public void printInstructions()
    {
        System.out.println(ConfigHelper.getMessageForKey("msg.instruction"));
    }

    @Override
    public void displayFinishedGameMessage()
    {
        System.out.println(ConfigHelper.getMessageForKey("msg.thanks"));
    }

    @Override
    public void displayGameSaveMessage()
    {
        System.out.println(ConfigHelper.getMessageForKey("msg.save.game"));
    }

    @Override
    public void hitSeaBedMessage()
    {
        System.out.println(ConfigHelper.getMessageForKey("msg.hit.sea.bed"));
    }

    @Override
    public void displayExploreError()
    {
        System.out.println(ConfigHelper.getMessageForKey("msg.invalid.input"));
    }

    @Override
    public int readUserInputInt()
    {
        int input = scan.nextInt();
        // takes care of \n character after number
        scan.nextLine();
        return input;
    }

    @Override
    public void destroy()
    {
        scan.close();
    }

    @Override
    public void displayUsers(List<Player> users)
    {
        printHeader();
        int i = 0;
        for (Player user : users) {
            System.out.println(++i + ")   " + user.getName() + "\t" + user.getGems() + "\t" + user.getHealth());
        }
        System.out.println(++i + ")   create New user");
        System.out.println(++i + ")   Delete A user");
        System.out.flush();
    }

    private void printHeader()
    {
        System.out.println(ConfigHelper.getMessageForKey("msg.select.user"));
        System.out.println(ConfigHelper.getMessageForKey("msg.dash.line"));
        System.out.println(ConfigHelper.getMessageForKey("msg.player.status"));
        System.out.println(ConfigHelper.getMessageForKey("msg.dash.line"));
        System.out.flush();
    }

    @Override
    public void displayInvalidOptionMessage()
    {
        System.out.println(ConfigHelper.getMessageForKey("msg.invalid.action"));
    }

    @Override
    public void confirmUserDeletion()
    {
        System.out.println(ConfigHelper.getMessageForKey("prompt.delete.user"));
    }

    @Override
    public void userIntroMessage()
    {
        System.out.println(ConfigHelper.getMessageForKey("msg.first.officer.welcome"));
    }

    @Override
    public void exitMessage()
    {
        System.out.println(ConfigHelper.getMessageForKey("msg.exit"));
    }

    @Override
    public void displayWinMessage()
    {
        System.out.println(ConfigHelper.getMessageForKey("msg.win"));
    }

    @Override
    public void displayAnswerPrompt()
    {
        System.out.println(ConfigHelper.getMessageForKey("prompt.answer.help"));
        System.out.flush();
    }

    @Override
    public void displayMonster()
    {
    	System.out.println(ConfigHelper.getMessageForKey("msg.enemy.sonar"));
    }

    @Override
    public void displayLooseMessage()
    {
    	System.out.println(ConfigHelper.getMessageForKey("msg.game.lost"));
    }

    @Override
    public void displayNoGemsMessage()
    {
    	System.out.println(ConfigHelper.getMessageForKey("msg.no.gems"));
    }

    @Override
    public void displayCorrectAnswerMessage()
    {
    	System.out.println(ConfigHelper.getMessageForKey("msg.destroy.enemyship"));
    }

    @Override
    public void displayInCorrectAnswerMessage()
    {
    	System.out.println(ConfigHelper.getMessageForKey("msg.wrong.bearing"));
    }

    @Override
    public void printHelp()
    {
    	System.out.println(ConfigHelper.getMessageForKey("msg.help"));
    }
}
