/**
 *
 */
package org.loveyoupeng.reactor.event

import org.junit.runner.RunWith
import org.specs2.Specification
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner])
class EventSourceSpec extends Specification {
  def is =
    "Event Observer" ^
      "simple observer" ! simpleObserver ^
      end

  def simpleObserver = {
    var value = 0
    val es = new EventSource[Int] {}
    import org.loveyoupeng.reactor.event.Observer._
    val ob = observe(es) {
      event: Int =>
        {
          value = event
          println(value)
        }
    }
    es.emit(10)

    value must beEqualTo(10)
  }
}