package org.study.hierarchy.actor;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class ParentActor extends UntypedAbstractActor {
    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    private ActorRef child;
    private int count;

    public ParentActor() {
        child = context().actorOf(Props.create(ChildActor.class), "child1Actor");
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof String) {
            final String msg = (String) message;
            if ("work".equals(msg)) {
                child.tell(msg, getSelf());
            } else if ("done".equals(msg)) {
                if (count == 0) {
                    count++;
                } else {
                    log.info("all works are completed.");
                    count = 0;
                }
            }
        }
    }
}
