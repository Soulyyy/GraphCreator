package controllers

import play.api._
import play.api.libs.json.Json
import play.api.mvc._

object Application extends Controller {

  case class Place(id: Int, name: String)

  val places = List(Place(1, "a"), Place(2, "b"), Place(32, "ELVA"))

  implicit val placesWrites = Json.writes[Place]

  def listPlaces = Action {
    val json = Json.toJson(places)
    Ok(json)
  }

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

}