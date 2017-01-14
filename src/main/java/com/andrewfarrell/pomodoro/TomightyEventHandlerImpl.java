package com.andrewfarrell.pomodoro;

import org.tomighty.Phase;
import org.tomighty.bus.messages.timer.TimerFinished;
import org.tomighty.bus.messages.timer.TimerInterrupted;
import org.tomighty.bus.messages.timer.TimerStarted;
import org.tomighty.time.Time;
import net.pushover.client.*;

/**
 * Created by andre_000 on 14/02/2016.
 */
public class TomightyEventHandlerImpl implements TomightyEventHandler {
    public void handleTimerFinished(TimerFinished timerFinished) {
        //String format = String.format("Plugin > timer finished phase = %s, time = %s", timerFinished.getPhase(), timerFinished.getTime());
        toPushover(timerFinished.getPhase(), timerFinished.getTime(), "FINISHED");
    }

    public void handleTimerStarted(TimerStarted timerStarted) {
        //String format = String.format("Plugin > timer started phase = %s, time = %s", timerStarted.getPhase(), timerStarted.getTime());
        toPushover(timerStarted.getPhase(), timerStarted.getTime(), "STARTED");
    }

    public void handleTimerInterrupted(TimerInterrupted timerInterrupted) {
        //String format = String.format("Plugin > timer interrupted phase = %s, time = %s", timerInterrupted.getPhase(), timerInterrupted.getTime());
        toPushover(timerInterrupted.getPhase(), timerInterrupted.getTime(), "INTERRUPTED");
    }

    private void toPushover(Phase phase, Time time, String label) {
        System.out.println(label);

        PushoverClient client = new PushoverRestClient();

        try {
            client.pushMessage(PushoverMessage.builderWithApiToken("MY_APP_API_TOKEN")
                    .setUserId("USER_ID_TOKEN")
                    .setMessage(phase.toString())
                    .build());


// push a message with optional fields
/*            Status result = client.pushMessage(PushoverMessage.builderWithApiToken("MY_APP_API_TOKEN")
                    .setUserId("USER_ID_TOKEN")
                    .setMessage("testing!")
                    .setDevice("device")
                    .setPriority(MessagePriority.HIGH) // HIGH|NORMAL|QUIET
                    .setTitle("title")
                    .setUrl("https://github.com/sps/pushover4j")
                    .setTitleForURL("pushover4j github repo")
                    .setSound("magic")
                    .build());

            System.out.println(String.format("status: %d, request id: %s", result.getStatus(), result.getRequestId()));

// get and print out the list of available sounds:
            for (PushOverSound sound : client.getSounds()) {
                System.out.println(String.format("name: %s, id: %s", sound.getName(), sound.getId()));
            }*/
        } catch (PushoverException e) {
            e.printStackTrace();
        }
    }

    private String getPCount(String token, String label, Phase phase) {
        int count = Integer.parseInt(token.split("=")[1]);
        if (label.equals("FINISHED") && phase.equals(Phase.POMODORO)) {
            return Integer.toString(++count);
        } else {
            return token.split("=")[1];
        }
    }
}
