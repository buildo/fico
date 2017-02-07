import org.scalatest._

//Standard scala library for making typesafe-config nice
//Same behavior as typesafe-config
object Fico extends FunSuite with App {
  import com.typesafe.config._
  import net.ceedubs.ficus.Ficus._

  val config = ConfigFactory.load()

  assert(config.as[String]("service.http.host") == "0.0.0.0")
  assert(config.as[Int]("service.http.port") == 8080)
  assertThrows[ConfigException$Missing](config.as[String]("service.fico"))
}

//Knobs is a haskell-like library used by verizon
//Ficus creator uses knobs
object Knobbo extends FunSuite with App {
  import com.typesafe.config._
  import knobs.{ Required, ClassPathResource, Config }
  import scalaz.concurrent.Task

  val cfg: Task[Config] = knobs.Typesafe.config

  val fico: Task[String] = for {
    c <- cfg
    f = c.require[String]("service.fico")
  } yield f

  assertThrows[knobs.KeyError](fico.run)
}
