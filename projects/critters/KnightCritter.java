/*
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 * @author Cay Horstmann
 */

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

/**
 * A <code>ChameleonCritter</code> takes on the color of neighboring actors as
 * it moves through the grid. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class KnightCritter extends Critter {

    /**
     * Turns towards the new location as it moves.
     */
    public void makeMove(Location loc) {
        int row = super.getLocation().getRow();
        int col = super.getLocation().getCol();

        Grid<Actor> grid = super.getGrid();

        Location moveLocation = null;

        // Find the 8 possible locations that the Knight can move to from the current location
        Location[] possibleLocations = new Location[8];
        possibleLocations[0] = new Location(row - 2, col - 1);
        possibleLocations[1] = new Location(row - 2, col + 1);
        possibleLocations[2] = new Location(row - 1, col - 2);
        possibleLocations[3] = new Location(row - 1, col + 2);
        possibleLocations[4] = new Location(row + 1, col - 2);
        possibleLocations[5] = new Location(row + 1, col + 2);
        possibleLocations[6] = new Location(row + 2, col - 1);
        possibleLocations[7] = new Location(row + 2, col + 1);

        // Find the position of the black king and brute force if it is under check and if any of the surrounding
        // square. If the king is currently under check and can't reach any other square it is in checkmate. If the king
        // is currently under check but can reach any square it is in check. If the king is not currently under check
        // but can't move then it is a stalemate. If the king is not under check and can move then it is safe

        // Find the position that is closest to the target location and is valid and empty

        double minDistance = Integer.MAX_VALUE;
        for (Location possibleLocation : possibleLocations) {
            if (grid.isValid(possibleLocation) && grid.get(possibleLocation) == null) {
                double distance = Math.pow(possibleLocation.getRow() - loc.getRow(), 2) + Math.pow(possibleLocation.getCol() - loc.getCol(), 2);
                if (distance < minDistance) {
                    minDistance = distance;
                    moveLocation = possibleLocation;
                }
            }
        }

        super.makeMove(moveLocation);
    }
}
