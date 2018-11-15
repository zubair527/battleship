package com.battleship.service;

import java.util.List;

import com.battleship.model.Player;

public interface UIservice
{
    void printStory();

    void printInstructions();

    void displayFinishedGameMessage();

    void displayGameSaveMessage();

    void hitSeaBedMessage();

    void displayExploreError();

    String readUserInputString();

    int readUserInputInt();

    void destroy();

    void displayUsers(List<Player> users);

    void displayInvalidOptionMessage();

    void confirmUserDeletion();

    void userIntroMessage();

    void exitMessage();

    void displayWinMessage();

    void displayAnswerPrompt();

    void displayMonster();

    void displayLooseMessage();

    void displayNoGemsMessage();

    void displayCorrectAnswerMessage();

    void displayInCorrectAnswerMessage();

    void printHelp();
}
