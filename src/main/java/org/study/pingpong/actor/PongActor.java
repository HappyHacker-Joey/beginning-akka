package org.study.pingpong.actor;

import akka.actor.ActorRef;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class PongActor extends UntypedAbstractActor {
    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    private ActorRef ping;

    public PongActor(ActorRef ping){
        this.ping = ping;
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof String) {
            final String msg = (String) message;
            log.info("Pong received {}", msg);
            ping.tell("ping", getSelf());
            Thread.sleep(500); // Do not use this for production.
        }
    }
}
