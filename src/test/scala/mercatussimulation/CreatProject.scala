package mercatussimulation

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class CreatProject extends Simulation {

	val httpProtocol = http
		.baseUrl("https://us-development.gomercatus.com")
		.inferHtmlResources(BlackList(""".*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.(t|o)tf""", """.*\.png""", """.*\/cometd\/connect.*""", """.*\.html.*""", """.*app\.pendo\.io\/data.*""", """.*google\-analytics\.com.*""", """.*launchdarkly.*""", """.*pendo.*""", """.*zopim.*""", """.*\.js""", """.*\.js\?v.*"""), WhiteList())
		.acceptHeader("application/json, text/javascript, */*; q=0.01")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en-US,en;q=0.9")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.109 Safari/537.36")

	val headers_0 = Map("X-Requested-With" -> "XMLHttpRequest")

	val headers_5 = Map(
		"Content-Type" -> "application/json",
		"Origin" -> "https://us-development.gomercatus.com",
		"X-Requested-With" -> "XMLHttpRequest")



	val scn = scenario("CreatProject")
		.exec(http("CreatProject_0")
			.get("/rest/conditionalAssumption/list/609?_=1550572288928")
			.headers(headers_0)
			.resources(http("CreatProject_1")
			.get("/rest/users/adminOrgUsers/609?_=1550572288929")
			.headers(headers_0)
			.check(bodyBytes.is(RawFileBody("CreatProject_0001_response.txt"))),
            http("CreatProject_2")
			.get("/rest/wfTaskTemplate/fetchOrgCriteriaAssumption/609?_=1550572288930")
			.headers(headers_0)
			.check(bodyBytes.is(RawFileBody("CreatProject_0002_response.txt"))),
            http("CreatProject_3")
			.get("/rest/preferences/assumptions/projectCreation?orgId=609&projectCategory=PROJECT&_=1550572288931")
			.headers(headers_0)
			.check(bodyBytes.is(RawFileBody("CreatProject_0003_response.txt"))))
			.check(bodyBytes.is(RawFileBody("CreatProject_0000_response.txt"))))
		.pause(59)
		.exec(http("CreatProject_4")
			.get("/rest/wfTaskTemplate/list/609?_=1550572288932")
			.headers(headers_0)
			.check(bodyBytes.is(RawFileBody("CreatProject_0004_response.txt"))))
		.pause(6)
		.exec(http("CreatProject_5")
			.post("/rest/projects")
			.headers(headers_5)
			.body(RawFileBody("CreatProject_0005_request.txt"))
			.check(bodyBytes.is(RawFileBody("CreatProject_0005_response.txt"))))
		.pause(1)
		.exec(http("CreatProject_6")
			.get("/rest/conditionalAssumption/list/609?_=1550572288933")
			.headers(headers_0)
			.resources(http("CreatProject_7")
			.get("/rest/conditionalAssumption/parentAssumptions/project/28993/org/609?_=1550572288934")
			.headers(headers_0)
			.check(bodyBytes.is(RawFileBody("CreatProject_0007_response.txt"))),
            http("CreatProject_8")
			.get("/rest/phaseDefinition/projectPhaseDefData/28993?_=1550572288936")
			.headers(headers_0)
			.check(bodyBytes.is(RawFileBody("CreatProject_0008_response.txt"))),
            http("CreatProject_9")
			.get("/rest/projectDetail/additionalDetails/28993?_=1550572288935")
			.headers(headers_0)
			.check(bodyBytes.is(RawFileBody("CreatProject_0009_response.txt"))),
            http("CreatProject_10")
			.get("/rest/projectDetail/assumptions/28993/view/0?_=1550572288937")
			.headers(headers_0)
			.check(bodyBytes.is(RawFileBody("CreatProject_0010_response.txt"))))
			.check(bodyBytes.is(RawFileBody("CreatProject_0006_response.txt"))))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}