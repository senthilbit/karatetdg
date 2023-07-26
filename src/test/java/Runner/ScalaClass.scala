package Runner

import com.intuit.karate.gatling.PreDef._
import io.gatling.core.Predef._

import scala.concurrent.duration._
import scala.language.postfixOps


class ScalaClass extends Simulation{
  var getuser = scenario("get").exec(karateFeature("classpath:Features/Datadriven.feature"));

  setUp(
    getuser.inject(rampUsers(10) during ( 6 seconds))
  )



}
