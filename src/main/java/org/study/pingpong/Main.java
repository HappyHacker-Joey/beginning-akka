package org.study.pingpong;

import org.study.pingpong.actor.PingActor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public final class Main {
    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("TestSystem");
        ActorRef ping = actorSystem.actorOf(Props.create(PingActor.class), "pingActor");
        ping.tell("start", ActorRef.noSender());
    }
}
