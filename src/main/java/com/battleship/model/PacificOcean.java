package com.battleship.model;

import com.battleship.utils.Constants;

public class Ocean implements GeoMap
{

    private static final long serialVersionUID = 1L;

    Fathom[][] fathoms = new Fathom[Constants.rows][Constants.columns];

    Integer currentFathomX;

    Integer currentFathomY;

    public Ocean()
    {
        initializeFathoms();
        initializeUserPosition();
        initializeEnemyShips();
        initializeHealthyPeople();
    }

    private void initializeHealthyPeople()
    {
        // select a fathom for health people which is different from user's fathom
        int friendsX = (int) (Math.random() * 10);
        int friendsY = (int) (Math.random() * 10);
        if (friendsX == currentFathomX && friendsY == currentFathomY) {
            friendsX = (int) (Math.random() * 10);
            friendsY = (int) (Math.random() * 10);
        }
        fathoms[friendsX][friendsY].setOccupiedBy(GameCharacter.HEALTHY_PEOPLE);

        // at least four enemy ships are very near to healty people island
        fathoms[friendsX][friendsY == 0 ? friendsY + 1 : friendsY - 1].setOccupiedBy(GameCharacter.ENEMY);
        fathoms[friendsX == 0 ? friendsX + 1 : friendsX - 1][friendsY].setOccupiedBy(GameCharacter.ENEMY);
    }

    private void initializeEnemyShips()
    {
        // 20 - 30 enemy ships are deployed in ocean
        int monsters = (int) (Math.random() * 10 + 20);
        for (int i = 0; i < monsters; i++) {
            fathoms[(int) (Math.random() * 10)][(int) (Math.random() * 10)].setOccupiedBy(GameCharacter.ENEMY);
        }
    }

    private void initializeUserPosition()
    {
        // select a room for user randomly
        currentFathomX = (int) (Math.random() * 10);
        currentFathomY = (int) (Math.random() * 10);
    }

    private void initializeFathoms()
    {
        // initialize fathoms
        for (int i = 0; i < fathoms.length; i++) {
            for (int j = 0; j < fathoms[i].length; j++) {
                fathoms[i][j] = new Fathom();
            }
        }
    }

    @Override
    public void print()
    {
        for (int i = 0; i < fathoms.length; i++) {
            for (int j = 0; j < fathoms[i].length; j++) {
                String roomString = "|_ _|";
                // to print player position
                if (i == currentFathomX && j == currentFathomY) {
                    roomString = "|_U_|";
                    fathoms[i][j].setOccupiedBy(null);
                }
                // to print the position of people
                if (GameCharacter.HEALTHY_PEOPLE.equals(fathoms[i][j].getOccupiedBy())) {
                    roomString = "|_P_|";
                }
                System.out.print(roomString);
            }
            System.out.println();
        }
    }

    public Integer getCurrentFathomX()
    {
        return currentFathomX;
    }

    public Integer getCurrentFathomY()
    {
        return currentFathomY;
    }

    public Fathom getCurrentRoom()
    {
        return fathoms[currentFathomX][currentFathomY];
    }

    public void updateCurrentFathomY(int i)
    {
        currentFathomY += i;
    }

    public void updateCurrentFathomX(int i)
    {
        currentFathomX += i;
    }
}
