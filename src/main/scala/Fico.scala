import org.scalatest._

object Fico extends FunSuite with App {
  import com.typesafe.config._
  import net.ceedubs.ficus.Ficus._

  val config = ConfigFactory.load()

  assert(config.as[String]("service.http.host") == "0.0.0.0")
  assert(config.as[Int]("service.http.port") == 8080)
  assertThrows[ConfigException$Missing](config.as[String]("service.fico"))
}
