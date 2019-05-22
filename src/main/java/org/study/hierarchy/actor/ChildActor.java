package org.study.hierarchy.actor;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class ChildActor extends UntypedAbstractActor {
    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    private ActorRef grandChild2;
    private ActorRef grandChild3;

    public ChildActor() {
        grandChild2 = context().actorOf(Props.create(GrandChildTwoActor.class), "grandChild2Actor");
        grandChild3 = context().actorOf(Props.create(GrandChildThreeActor.class), "grandChild3Actor");
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof String) {
            final String msg = (String) message;
            if ("work".equals(msg)) {
                log.info("grandChild1 received {}", msg);
                grandChild2.tell("work", getSender());
                grandChild3.tell("work", getSender());
            }
        }
    }
}
