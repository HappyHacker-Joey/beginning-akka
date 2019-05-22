package org.study.pingpong.actor;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class PingActor extends UntypedAbstractActor {
    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    private ActorRef pong;

    @Override
    public void preStart() throws Exception {
        pong = context().actorOf(Props.create(PongActor.class, getSelf()), "pongActor");
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof String) {
            final String msg = (String) message;
            log.info("Ping received {}", msg);
            pong.tell("ping", getSelf());
        }
    }
}
