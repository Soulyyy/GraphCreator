package controllers

import java.util.concurrent.TimeoutException

import daos.TestDatabaseDAO
import play.api.db.DB
import play.api.mvc._
import play.api.libs.ws._

//import slick.driver.SQLiteDriver.api._

import slick.driver.PostgresDriver.api._


/**
 * Created by Hans Peeter Tulmin on 25.08.2015.
 */
object TestQueryController extends Controller {

  //case class Person(name: String, surname: String, number: Int)

  case class Person(id: Option[Long] = None, name: String, surname: String, number: Option[Int])

  class Persons(tag: Tag) extends Table[Person](tag, "person") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def name = column[String]("name")

    def surname = column[String]("surname")

    def number = column[Int]("number")

    def * = (id.?, name, surname, number) <>(Person.tupled, Person.unapply)
  }

  def insert(person: Person): Action[AnyContent] = Action.async {
    implicit request =>
      val futurePersonInsert = TestDatabaseDAO.insert(person)
    futurePersonInsert.map {
      result => Home.flashing("success" -> "Person has been added")
    }.recover {
      case e: TimeoutException =>
        Logger.error("Adding Person failed")
        InternalServerError(e.getMessage)
    }
  }

  def insertTest = Action {
    DB.withSession {
      implicit s =>
        val p = Person("Simmo", "Nool", 22)
        Persons.persons.insert(p)
    }
    Ok("TERE")
  }

  def getTest = Action {
    Ok ("TERE")
  }

}
