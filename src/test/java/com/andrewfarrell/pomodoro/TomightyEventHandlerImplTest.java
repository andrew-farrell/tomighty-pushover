package com.andrewfarrell.pomodoro;

import org.junit.Test;
import org.tomighty.Phase;
import org.tomighty.bus.messages.timer.TimerFinished;

/**
 * Created by andre_000 on 15/01/2017.
 */
public class TomightyEventHandlerImplTest {

    @Test
    public void testMessage(){
        TomightyEventHandlerImpl tom = new TomightyEventHandlerImpl();


        TimerFinished fin;
        fin = new TimerFinished(Phase.POMODORO);

        tom.handleTimerFinished(fin);
    }

}