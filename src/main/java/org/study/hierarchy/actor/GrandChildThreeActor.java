package org.study.hierarchy.actor;

import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class GrandChildThreeActor extends UntypedAbstractActor {
    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof String) {
            final String msg = (String) message;
            if ("work".equals(msg)) {
                log.info("grandChild3 received {}", msg);
                work();
                getSender().tell("done", getSelf());
            }
        }
    }

    private void work() throws Exception {
        log.info("grandChild3 working...");
        Thread.sleep(1000); // Do not use this for production.
    }

}
