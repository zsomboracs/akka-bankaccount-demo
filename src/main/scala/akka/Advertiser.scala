package akka

import akka.actor.Actor
import akka.actor.Status.Failure
import akka.pattern.pipe
import reactivemongo.api._
import reactivemongo.api.collections.bson.BSONCollection
import reactivemongo.bson._

import scala.concurrent.ExecutionContext.Implicits.global

object Advertiser {

  case class Advertise(email: String, amount: Int) {
    require(amount > 0)
  }

  implicit object AdvertiseHandler extends BSONDocumentReader[Advertise] with BSONDocumentWriter[Advertise] {

    override def read(bson: BSONDocument): Advertise = {
      val email = bson.getAs[String]("email").get
      val amount = bson.getAs[Int]("amount").get
      Advertise(email, amount)
    }

    override def write(ad: Advertise): BSONDocument = BSONDocument(
      "email" -> ad.email,
      "amount" -> ad.amount
    )
  }

}

class Advertiser extends Actor {

  import Advertiser._

  val driver: MongoDriver = new MongoDriver
  var connection: MongoConnection = driver.connection(List("localhost"))
  var db = connection("admin")
  var collection = db.collection[BSONCollection]("advertise")

  override def postStop(): Unit = {
    connection.close()
    driver.close()
  }

  def receive = {
    case ad: Advertise =>
      collection.insert(ad).map { _ => println("successful insert") } pipeTo self
    case Failure(throwable) =>
      throw throwable
  }


}
