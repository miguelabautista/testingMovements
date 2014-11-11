package com.ofertia.marsrover

import com.ofertia.marsrover.util.Commands
import com.ofertia.marsrover.util.Directions
import com.ofertia.marsrover.util.Moves

/**
 * Created by root on 10/11/14.
 */
class Rover {
    private int perimeterX
    private int perimeterY
    private Directions currentWay
    private Commands command

    int positionX
    int positionY

    List<Moves> movementsList = []

    List<Integer> numbers = []

    void setUpperRightCoodernates(int x, int y) {
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

    void initialWay(Directions way) {
        currentWay = way

        switch (currentWay) {
            case currentWay.N: setCommand(Commands.PLUSY)
                break
            case currentWay.E: setCommand(Commands.PLUSX)
                break
            case currentWay.S: setCommand(Commands.MINUSY)
                break
            case currentWay.W: setCommand(Commands.MINUSX)
                break
        }
    }

    def changeWay(Moves newSide) {
        switch (currentWay) {
            case currentWay.N: if (newSide == Moves.L) {
                setCommand(Commands.MINUSX); setWay(Directions.W)
            } else if (newSide == Moves.R) {
                setCommand(Commands.PLUSX); setWay(Directions.E)
            } else {
                move()
            }
                break
            case currentWay.E: if (newSide == Moves.L) {
                setCommand(Commands.PLUSY); setWay(Directions.N)
            } else if (newSide == Moves.R) {
                setCommand(Commands.MINUSY); setWay(Directions.S)
            } else {
                move()
            }
                break
            case currentWay.S: if (newSide == Moves.L) {
                setCommand(Commands.PLUSX); setWay(Directions.E)
            } else if (newSide == Moves.R) {
                setCommand(Commands.MINUSX); setWay(Directions.W)
            } else {
                move()
            }
                break
            case currentWay.W: if (newSide == Moves.L) {
                setCommand(Commands.MINUSY); setWay(Directions.S)
            } else if (newSide == Moves.R) {
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

    private def setWay(Directions way) {
        currentWay = way
    }

    private def setMinusY() {
        positionY -= 1
    }

    private def setPlusY() {
        positionY += 1
    }

    private def setPlusX() {
        positionX += 1
    }

    private def setMinusX() {
        positionX -= 1
    }
}
