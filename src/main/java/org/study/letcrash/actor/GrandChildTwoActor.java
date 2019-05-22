package org.study.letcrash.actor;

import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class GrandChildTwoActor extends UntypedAbstractActor {
    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof String) {
            final String msg = (String) message;
            if ("work".equals(msg)) {
                log.info("grandChild2 received {}", msg);
                work();
                getSender().tell("done", getSelf());
            }
        }else {
            unhandled(message);
        }
    }

    private void work() throws Exception {
        log.info("grandChild2 working...");
        Thread.sleep(1000); // Do not use this for production.
    }

}
