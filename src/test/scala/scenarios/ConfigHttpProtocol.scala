package scenarios
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import java.io.File
import com.typesafe.config.{ Config, ConfigFactory }
//class Config extends Simulation {
import utilities.ReadConfigData

	object ConfigHttpProtocol {

    val objReadConfigData= new ReadConfigData()


		val baseUrl =objReadConfigData.getAppUrl() ///"https://us-development.gomercatus.com"
		val httpProtocol = http
			.baseUrl(baseUrl)
			.inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.(t|o)tf""", """.*\.png""", """.*\/connect\/commet.*"""), WhiteList())
			.acceptHeader("*/*")
			.acceptEncodingHeader("gzip, deflate")
			.acceptLanguageHeader("en-US,en;q=0.5")
			.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0")
			.disableFollowRedirect

		val headers_common = Map("Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")

		val headers_0 = Map("Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
		val headers_2 = Map(
			"Accept" -> "application/json, text/javascript, */*; q=0.01",
			"X-Requested-With" -> "XMLHttpRequest")

		val headers_3 = Map(
			"Accept" -> "text/plain, */*; q=0.01",
			"Content-Type" -> "text/plain;charset=UTF-8",
			"X-Requested-With" -> "XMLHttpRequest")

		val headers_9 = Map(
			"Content-Type" -> "application/json;charset=UTF-8",
			"X-Requested-With" -> "XMLHttpRequest")

		val headers_10 = Map(
			"Accept" -> "application/json",
			"Content-Type" -> "application/json; charset=utf-8",
			"X-Requested-With" -> "XMLHttpRequest")

	}
//}
