package com.ofertia.marsrover

import com.ofertia.marsrover.util.Commands
import com.ofertia.marsrover.util.Directions
import com.ofertia.marsrover.util.Moves

/**
 * Created by root on 10/11/14.
 */
class Rover extends Observable{
    private int perimeterX
    private int perimeterY
    private Directions currentDirection
    private Commands command

    int positionX
    int positionY

    private int roverId

    Rover(int roverId){
        this.roverId = roverId;
    }

    List<Moves> movementsList = []

    List<Integer> numbers = []

    void setUpperRightCoordinates(int x, int y) {
        this.perimeterX = x
        this.perimeterY = y
    }

    void firstPosition(int x, int y) {
        positionX = x;
        positionY = y;
    }


    private void move() {
        switch (command) {
            case command.MINUSX: setMinusX(); break
            case command.PLUSX: setPlusX(); break
            case command.MINUSY: setMinusY(); break
            case command.PLUSY: setPlusY(); break
        }
    }

    void initialWay(Directions direction) {
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

    def changeWay(Moves newMove) {
        switch (currentDirection) {
            case currentDirection.N: if (newMove == Moves.L) {
                setCommand(Commands.MINUSX); setWay(Directions.W)
            } else if (newMove == Moves.R) {
                setCommand(Commands.PLUSX); setWay(Directions.E)
            } else {
                move()
            }
                break
            case currentDirection.E: if (newMove == Moves.L) {
                setCommand(Commands.PLUSY); setWay(Directions.N)
            } else if (newMove == Moves.R) {
                setCommand(Commands.MINUSY); setWay(Directions.S)
            } else {
                move()
            }
                break
            case currentDirection.S: if (newMove == Moves.L) {
                setCommand(Commands.PLUSX); setWay(Directions.E)
            } else if (newMove == Moves.R) {
                setCommand(Commands.MINUSX); setWay(Directions.W)
            } else {
                move()
            }
                break
            case currentDirection.W: if (newMove == Moves.L) {
                setCommand(Commands.MINUSY); setWay(Directions.S)
            } else if (newMove == Moves.R) {
                setCommand(Commands.PLUSY); setWay(Directions.N)
            } else {
                move()
            }
                break
        }
    }

    private def setCommand(Commands command) {
        this.command = command
    }

    private def setWay(Directions direction) {
        currentDirection = direction
    }

    private def setMinusY() {
        positionY -= 1
        if (positionY < 0) positionY += 1
    }

    private def setPlusY() {
        positionY += 1
        if (perimeterY > 0 && positionY > perimeterY) positionY -= 1
    }

    private def setPlusX() {
            positionX += 1
        if (perimeterX > 0 && positionX > perimeterX) positionX -= 1
    }

    private def setMinusX() {
        positionX -= 1
        if (positionX < 0) positionX += 1
    }



    public String getCurrentPosition(){
        "roverId:${roverId} X:${positionX} Y:${positionY} direction:${currentDirection}"
    }
}
