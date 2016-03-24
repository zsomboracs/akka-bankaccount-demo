package akka

import akka.actor.SupervisorStrategy.{Escalate, Restart}
import akka.actor.{Actor, OneForOneStrategy, Props, SupervisorStrategy}
import reactivemongo.core.errors.DriverException

import scala.concurrent.duration._

class AdvertiserMaster extends Actor {

  import Advertiser._

  val advertiser = context.actorOf(Props[Advertiser], "advertiser")

  override def supervisorStrategy: SupervisorStrategy =
    OneForOneStrategy(maxNrOfRetries = 30, withinTimeRange = 2.minutes) {
      case _: DriverException =>
        print(s"restart actor ${sender()}")
        Restart
      case t: Throwable =>
        print(s"escalate actor ${sender()}")
        Escalate
    }

  def receive() = {
    case ad: Advertise => advertiser ! ad
  }

}
