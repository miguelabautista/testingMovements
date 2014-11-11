package com.ofertia.marsrover

import com.ofertia.marsrover.util.Directions
import com.ofertia.marsrover.util.Moves

/**
 * Created by root on 09/11/14.
 */
class Movement {

    private List<Rover> rovers

    void movements(String movements) {

        rovers = []

        setParams(movements)

        initiateRovers()
    }

    private void setParams(String movements) {
        def result = new Scanner(movements)

        def roverId = 1

        def rover = new Rover(roverId)

        while (result.hasNext()) {
            if (result.hasNextInt()) {
                rover.numbers << result.nextInt()
            } else {
                String value = result.next()

                rover = setValues(value, rover, roverId)


            }
        }
    }

    private Rover setValues(String value, Rover rover, int roverId) {
        if (value == Directions.N.name()) {
            rover.initialWay(Directions.N)
        } else if (value == Directions.E.name()) {
            rover.initialWay(Directions.E)
        } else if (value == Directions.S.name()) {
            rover.initialWay(Directions.S)
        } else if (value == Directions.W.name()) {
            rover.initialWay(Directions.W)
        } else if (value ==~ /\w*/) {
            def mo = value.collect({ it })

            mo.each {
                if (it == Moves.L.name()) {
                    rover.movementsList << Moves.L
                } else if (it == Moves.R.name()) {
                    rover.movementsList << Moves.R
                } else if (it == Moves.M.name()) {
                    rover.movementsList << Moves.M
                }
            }
            rovers << rover
            rover = new Rover(++roverId)
        }
        rover
    }

    private void initiateRovers() {
        if (!rovers.isEmpty()) {

            rovers.each { rover ->

                if (rover.numbers.size() == 4) {
                    rover.setUpperRightCoordinates(rover.numbers.get(0), rover.numbers.get(1))
                    rover.firstPosition(rover.numbers.get(2), rover.numbers.get(3))
                } else {
                    rover.firstPosition(rover.numbers.get(0), rover.numbers.get(1))
                }

                rover.movementsList.each { Moves move ->
                    rover.changeWay(move)
                }
            }
        }
    }

    String getLastPosition() {
        def result = ""

        rovers.each { rover ->
            result = result + "${rover.positionX} ${rover.positionY} ${rover.currentDirection} "
        }

        result.trim()
    }

}
