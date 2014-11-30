package models
// Importing the slick driver
import play.api.db.slick.Config.driver.simple._


/**
 * Created by sandman on 11/30/14.
 */

// The case class to represent the data object
case class Player(id: Option[Int] = None, first: String, last: String)

// The static object that does the actual work - note the names of tables and fields in H2 are case sensitive and must be all caps
object Players extends Table[Player]("RESOURCE") {
  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)
  def first = column[String]("FIRST")
  def last = column[String]("LAST")

  // Every table needs a * projection with the same type as the table's type parameter
  def * = id.? ~ first ~ last <> (Player, Player.unapply _)

  // This custom insert method allows the return of the auto increment id when the row is inserted without specifying it
  def forInsert = first ~ last  <> ({ t => Player(None, t._1, t._2)}, { (r: Player) => Some ((r.first, r.last))})
}

