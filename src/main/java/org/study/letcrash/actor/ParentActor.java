package org.study.letcrash.actor;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class ParentActor extends UntypedAbstractActor {
    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    private ActorRef child;

    public ParentActor() {
        child = context().actorOf(Props.create(ChildActor.class), "child1Actor");
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof String) {
            final String msg = (String) message;
            if ("good".equals(msg) || "bad".equals(msg)) {
                child.tell(msg, getSelf());
            } else if ("done".equals(msg)) {
                log.info("All works are successfully completed.");
            }
        } else {
            unhandled(message);
        }
    }
}
