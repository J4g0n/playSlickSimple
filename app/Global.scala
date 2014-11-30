import play.api.db.slick.DB
import play.api.GlobalSettings
// Use mysql to connect to a mysql database
import play.api.db.slick.Config.driver.simple._
import play.api.Application
import play.api.Play.current
import models._


object Global extends GlobalSettings {

  override def onStart(app: Application) {
    InitialData.insert
  }

  object InitialData {
    def insert = {
      DB.withSession {
        implicit session:Session =>
          val resourceOneId = Resources.forInsert returning Resources.id insert(Resource(None, "Great", "Engineer"))
      }
    }
  }
}