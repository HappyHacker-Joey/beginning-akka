package org.study.letcrash.actor;

import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import scala.Option;

public class GrandChildTwoActor extends UntypedAbstractActor {
    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public GrandChildTwoActor() {
        log.info("grandChild2 constructor...");
    }

    @Override
    public void preRestart(Throwable reason, Option<Object> message) throws Exception {
        log.info("grandChild2 pre-restart...");
    }

    @Override
    public void postRestart(Throwable reason) throws Exception {
        log.info("grandChild2 post-restart...");
    }

    @Override
    public void postStop() throws Exception {
        log.info("grandChild2 post-stop...");
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof String) {
            final String msg = (String) message;
            if ("good".equals(msg)) {
                goodWork();
                getSender().tell("done", getSelf());
            } else if ("bad".equals(msg)) {
                badWork();
            }
        } else {
            unhandled(message);
        }
    }

    private void goodWork() throws Exception {
        log.info("grandChild2 is good!");
    }

    private void badWork() throws Exception {
        log.info("grandChild2 is bad!");
        int a = 1 / 0; // intentional exception
    }

}
