package daos

import controllers.TestQueryController.{Person, Persons}
import play.api.db.DB
import slick.lifted.TableQuery
import slick.driver.PostgresDriver.api._

import scala.concurrent.Future

/**
 * Created by Hans Peeter Tulmin on 26.08.2015.
 */
object TestDatabaseDAO {

  val persons = TableQuery[Persons]

  def db: Database = Database.forDataSource(DB.getDataSource())

  def filterQuery(id: Long): Query[Persons, Person, Seq] =
  persons.filter(_.id === id)

  def findById(id: Long): Future[Person] =
  try db.run(filterQuery(id).result.head)
  finally db.close

  def insert(person : Person): Future[Int] =
  try db.run(persons += person)
  finally db.close

  def update(id: Long, person: Person): Future[Int] =
  try db.run(filterQuery(id).update(person))
  finally db.close

  def delete(id: Long): Future[Int] =
  try db.run(filterQuery(id).delete)
  finally db.close

}
