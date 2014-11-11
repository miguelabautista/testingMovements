package com.ofertia.marsrover

import com.ofertia.marsrover.util.Directions
import spock.lang.Ignore
import spock.lang.Specification


/**
 * Created by root on 09/11/14.
 */
class Input extends Specification {

    def movement = new Movement()

    @Ignore
    def "current position"() {
        setup:
        movement.setParams("5 5 1 2 N LMLMLMLMM 5 5 3 3 E MMRMMRMRRM")

        when:
        def result = movement.getRovers()[1]

        then:

        result.getCurrentWay() == Directions.E
    }

    def "rover movements"() {
        setup:
        movement.movements("5 5 1 2 N LMLMLMLMM 3 3 E MMRMMRMRRM 6 6 4 3 W MLRMRRMMLMRLMMM")

        expect:
        result == movement.getLastPosition()

        where:

        result = "1 3 N 5 1 E 4 7 N"

    }



}