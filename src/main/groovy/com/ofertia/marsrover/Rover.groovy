package com.ofertia.marsrover

import com.ofertia.marsrover.util.Commands
import com.ofertia.marsrover.util.Directions
import com.ofertia.marsrover.util.Moves

/**
 * Created by root on 10/11/14.
 */
class Rover extends Observable {
    private int perimeterX
    private int perimeterY
    private Directions currentDirection
    private Commands command

    int positionX
    int positionY

    private int roverId

    Rover(int roverId) {
        this.roverId = roverId;
    }

    List<Moves> movementsList = []

    List<Integer> numbers = []

    /**
     *  Set the ground limits for the rover.
     *
     * @param x - Coordinate X
     * @param y - Coordinate Y
     */
    void setUpperRightCoordinates(int x, int y) {
        this.perimeterX = x
        this.perimeterY = y
    }

    /**
     *  Set the first Position of the rover in the plateau
     *
     * @param x - Coordinate X
     * @param y - Coordinate Y
     */
    void firstPosition(int x, int y) {
        positionX = x;
        positionY = y;
    }

    /**
     *
     *  Changes the position of the rover in the plateau
     *
     */
    private void move() {
        switch (command) {
            case command.MINUSX: setMinusX(); break
            case command.PLUSX: setPlusX(); break
            case command.MINUSY: setMinusY(); break
            case command.PLUSY: setPlusY(); break
        }
    }
    /**
     *  Sets the first command
     *
     * @param direction initial direction
     */
    void initialCommand(Directions direction) {
        currentDirection = direction

        switch (currentDirection) {
            case currentDirection.N: setCommand(Commands.PLUSY)
                break
            case currentDirection.E: setCommand(Commands.PLUSX)
                break
            case currentDirection.S: setCommand(Commands.MINUSY)
                break
            case currentDirection.W: setCommand(Commands.MINUSX)
                break
        }
    }

    /**
     *  Sets the new move
     *
     * @param newMove the move
     *
     */
    void changeDirection(Moves newMove) {
        switch (currentDirection) {
            case currentDirection.N: if (newMove == Moves.L) {
                setCommand(Commands.MINUSX); setDirection(Directions.W)
            } else if (newMove == Moves.R) {
                setCommand(Commands.PLUSX); setDirection(Directions.E)
            } else {
                move()
            }
                break
            case currentDirection.E: if (newMove == Moves.L) {
                setCommand(Commands.PLUSY); setDirection(Directions.N)
            } else if (newMove == Moves.R) {
                setCommand(Commands.MINUSY); setDirection(Directions.S)
            } else {
                move()
            }
                break
            case currentDirection.S: if (newMove == Moves.L) {
                setCommand(Commands.PLUSX); setDirection(Directions.E)
            } else if (newMove == Moves.R) {
                setCommand(Commands.MINUSX); setDirection(Directions.W)
            } else {
                move()
            }
                break
            case currentDirection.W: if (newMove == Moves.L) {
                setCommand(Commands.MINUSY); setDirection(Directions.S)
            } else if (newMove == Moves.R) {
                setCommand(Commands.PLUSY); setDirection(Directions.N)
            } else {
                move()
            }
                break
        }
    }

    /**
     *
     * @param command new coomand
     * @return
     */
    private void setCommand(Commands command) {
        this.command = command
    }
    /**
     *
     * @param direction new direction
     */
    private void setDirection(Directions direction) {
        currentDirection = direction
    }

    /**
     * Movement to South
     */
    private void setMinusY() {
        positionY -= 1
        if (positionY < 0) positionY += 1
    }
    /**
     * Movement to North
     */
    private void setPlusY() {
        positionY += 1
        if (perimeterY > 0 && positionY > perimeterY) positionY -= 1
    }
    /**
     * Movement to East
     */
    private void setPlusX() {
        positionX += 1
        if (perimeterX > 0 && positionX > perimeterX) positionX -= 1
    }
    /**
     * Movement to West
     */
    private void setMinusX() {
        positionX -= 1
        if (positionX < 0) positionX += 1
    }
}
