package org.study.hierarchy;

import org.study.hierarchy.actor.ParentActor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class Main {
    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("TestSystem");
        ActorRef parentActor = actorSystem.actorOf(Props.create(ParentActor.class), "parentActor");
        parentActor.tell("work", ActorRef.noSender());
    }
}
