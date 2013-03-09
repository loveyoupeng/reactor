package org.loveyoupeng.reactor.event

import scala.actors.Actor
import scala.actors.Actor._
trait EventSource[A] {
  var observers: List[Observer[A]] = Nil
  val handler: Actor = actor {
    loop {
      react {
        case observer: Observer[A] => {
          observers ::= observer
        }

        case event => {
          observers.foreach(observer => observer(event.asInstanceOf[A]))
        }
      }
    }
  }

  def emit(event: A): Unit = handler ! event

  def subscribe(observer: Observer[A]): Unit = handler ! observer

}

class Observer[A](private val operation: A => Unit, private var on: Boolean = true) {
  def turnOff = on = false
  def turnOn = on = true
  def flip = on = !on
  def apply(event: A): Unit = handler ! event
  val handler: Actor = actor {
    loop {
      react {
        case x if (on) => operation(x.asInstanceOf[A])
      }
    }
  }
}

object Observer {
  def observe[A](eventSource: EventSource[A])(observer: Observer[A]): Observer[A] = {
    eventSource.subscribe(observer)
    observer
  }

  implicit def function2Observer[A](function: A => Unit): Observer[A] = new Observer[A](function)
}

