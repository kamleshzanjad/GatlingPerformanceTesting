package mercatussimulation

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class SelectTabName extends Simulation {

	val httpProtocol = http
		.baseUrl("https://us-development.gomercatus.com")
		.inferHtmlResources(BlackList(""".*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.(t|o)tf""", """.*\.png""", """.*\/cometd\/connect.*""", """.*\.html.*""", """.*app\.pendo\.io\/data.*""", """.*google\-analytics\.com.*""", """.*launchdarkly.*""", """.*pendo.*""", """.*zopim.*""", """.*\.js""", """.*\.js\?v.*"""), WhiteList())
		.acceptHeader("application/json")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en-US,en;q=0.9")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.109 Safari/537.36")

	val headers_0 = Map(
		"Accept" -> "application/json, text/javascript, */*; q=0.01",
		"X-Requested-With" -> "XMLHttpRequest")

	val headers_1 = Map(
		"Content-Type" -> "application/json; charset=utf-8",
		"X-Requested-With" -> "XMLHttpRequest")

	val headers_2 = Map(
		"Content-Type" -> "application/json; charset=UTF-8",
		"Origin" -> "https://us-development.gomercatus.com",
		"X-Requested-With" -> "XMLHttpRequest")



	val scn = scenario("SelectTabName")
		.exec(http("SelectTabName_0")
			.get("/rest/wfTask/P/settings/org/609/includeWeekend?_=1550572288926")
			.headers(headers_0)
			.resources(http("SelectTabName_1")
			.get("/rest/assumptionPermission/userPermission?_=1550572288927")
			.headers(headers_1)
			.check(bodyBytes.is(RawFileBody("SelectTabName_0001_response.txt"))),
            http("SelectTabName_2")
			.post("/rest/projects/609")
			.headers(headers_2)
			.body(RawFileBody("SelectTabName_0002_request.txt"))
			.check(bodyBytes.is(RawFileBody("SelectTabName_0002_response.txt"))))
			.check(bodyBytes.is(RawFileBody("SelectTabName_0000_response.txt"))))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}