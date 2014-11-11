package com.ofertia.marsrover

import spock.lang.Specification

/**
 * Created by root on 09/11/14.
 */
class Input extends Specification {

    def movement = new Movement()


    def "validate perimeter"() {
        setup:
        movement.movements("7 8 4 3 N MMMMMMMMMRMMMMMMMMMMM")

        expect:
        result == movement.getLastPosition()

        where:

        result = "7 8 E"
    }

    def "validate negative perimeter"() {
        setup:
        movement.movements("4 3 S MMMMMMMMRMMMMMMMMM")

        expect:
        result == movement.getLastPosition()

        where:

        result = "0 0 W"
    }

    def "rover movements"() {
        setup:
        movement.movements("5 5 1 2 N LMLMLMLMM 3 3 E MMRMMRMRRM")

        expect:
        result == movement.getLastPosition()

        where:

        result = "1 3 N 5 1 E"

    }



}