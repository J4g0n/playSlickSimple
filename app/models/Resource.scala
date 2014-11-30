package models

// Importing the slick driver
import play.api.db.slick.Config.driver.simple._



// The case class to represent the data object
case class Resource(id: Option[Int] = None, first: String, last: String)

// The static object that does the actual work - note the names of tables and fields in H2 are case sensitive and must be all caps
object Resources extends Table[Resource]("RESOURCE") {
  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)
  def first = column[String]("FIRST")
  def last = column[String]("LAST")

  // Every table needs a * projection with the same type as the table's type parameter
  def * = id.? ~ first ~ last <> (Resource, Resource.unapply _)

  // This custom insert method allows the return of the auto increment id when the row is inserted without specifying it
  def forInsert = first ~ last  <> ({ t => Resource(None, t._1, t._2)}, { (r: Resource) => Some ((r.first, r.last))})
}
