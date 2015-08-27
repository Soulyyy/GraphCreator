package controllers

import play.api.mvc.{Action, Controller}

/**
 * Created by Hans Peeter Tulmin on 24.08.2015.
 */
object HelloWorldController extends Controller{

  def index = Action {
    Ok("Hello World!")
  }

}
