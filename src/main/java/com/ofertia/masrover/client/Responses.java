package com.ofertia.masrover.client;

import com.ofertia.marsrover.Movement;

/**
 * Created by root on 11/11/14.
 */
public class Responses {

    private static final int WAITING = 0;
    private static final int INPUT = 1;

    private int state = WAITING;

    public String processInput(String theInput) {
        String theOutput = null;

        if (theInput != null && theInput.equalsIgnoreCase("exit")) {
            theOutput = "exit";
        } else if (state == WAITING) {
            theOutput = "Write exit to shutdown!!! \nInsert Rovers commands";
            state = INPUT;
        } else if (state == INPUT) {
            try {
                Movement movement = new Movement();
                movement.movements(theInput);

                theOutput = "result " + movement.getLastPosition();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return theOutput;
    }
}