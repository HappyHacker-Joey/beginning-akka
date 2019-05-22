package org.study.letcrash.actor;

import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import scala.Option;

public class GrandChildThreeActor extends UntypedAbstractActor {
    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public GrandChildThreeActor() {
        log.info("grandChild3 constructor...");
    }

    @Override
    public void preRestart(Throwable reason, Option<Object> message) throws Exception {
        log.info("grandChild3 pre-restart...");
    }

    @Override
    public void postRestart(Throwable reason) throws Exception {
        log.info("grandChild3 post-restart...");
    }

    @Override
    public void postStop() throws Exception {
        log.info("grandChild3 post-stop...");
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
        log.info("grandChild3 is good!");
    }

    private void badWork() throws Exception {
        log.info("grandChild3 is bad!");
        throw new NullPointerException();
    }

}
