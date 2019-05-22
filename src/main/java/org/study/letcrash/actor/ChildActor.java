package org.study.letcrash.actor;

import java.util.concurrent.TimeUnit;

import akka.actor.ActorRef;
import akka.actor.OneForOneStrategy;
import akka.actor.Props;
import akka.actor.SupervisorStrategy;
import akka.actor.SupervisorStrategy.Directive;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Function;
import scala.concurrent.duration.Duration;

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
            if ("good".equals(msg) || "bad".equals(msg)) {
                log.info("child received {}", msg);
                grandChild2.tell(msg, getSender());
                grandChild3.tell(msg, getSender());
            }
        } else {
            unhandled(message);
        }
    }

    private static SupervisorStrategy strategy =
            new OneForOneStrategy(10, Duration.apply(1, TimeUnit.MINUTES),
                                  new Function<Throwable, Directive>() {
                                      @Override
                                      public Directive apply(Throwable t) throws Exception {
//                                          if (t instanceof ArithmeticException) {
//                                              return SupervisorStrategy.stop();
//                                          } else
                                          if (t instanceof NullPointerException) {
                                              return SupervisorStrategy.escalate();
                                          } else if (t instanceof IllegalArgumentException) {
                                              return SupervisorStrategy.restart();
                                          } else {
                                              return SupervisorStrategy.resume();
                                          }
                                      }
                                  });

    @Override
    public SupervisorStrategy supervisorStrategy() {
        return strategy;
    }
}
