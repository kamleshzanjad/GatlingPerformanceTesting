package scenarios

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object LoginOperation {
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


	def methodLogin(requestName:String) ={

		group("LoginOperation") {
			exec(http(requestName+"PostUserAuthType")
				.post("/external/getUserAuthType")
				.headers(headers_0)
				.formParam("userName", "${UserId}")
				.check(status.is(302))
			)
				.pause(1)
				.exec(http(requestName+"Postj_spring_security_check")
					.post("/j_spring_security_check")
					.headers(headers_0)
					.formParam("j_username", "${UserId}")
					.formParam("j_password", "${Password}")
					.check(status.is(302))
				)
				.pause(1)

				.exec(http(requestName+"GetuserInfo")
					.get("/rest/userInfo?_=1548411351095")
					.headers(headers_2)
					.resources(http(requestName+"GetMessagePrpoerties")
						.get("/app/js/libs/i18bundle/Messages.properties?_=1548411351096")
						.headers(headers_3),
						http(requestName+"GetMessageEnPrpoerties")
							.get("/app/js/libs/i18bundle/Messages_en.properties?_=1548411351097")
							.headers(headers_3),
						http(requestName+"GetMessageUSPrpoerties")
							.get("/app/js/libs/i18bundle/Messages_en-US.properties?_=1548411351098")
							.headers(headers_3),
						http(requestName+"GetuserLocalizationConfig")
							.get("/rest/userInfo/userLocalizationConfig?_=1548411351099")
							.headers(headers_2),
						http(requestName+"GetAssetConfig")
							.get("/rest/assets/assetConfig?_=1548411351100")
							.headers(headers_2),
						http(requestName+"DownloadPhoto")
							.get("/rest/userInfo/photo/download/?ts=1548411356580&scaleHeight=140&scaleWidth=140"),

						http(requestName+"GetTaskCount")
							.get("/rest/wfTaskApproval/myTask/count?ts=1548411355944&_=1548411351101")
							.headers(headers_10),

						http(requestName+"GetAssetHomePage")
							.get("/rest/assetHomePage/3341?_=1548411351102")
							.headers(headers_2),

						http(requestName+"GetGlogalTask")
							.get("/rest/wfTask/A/globalTasks/3341?_=1548411351104")
							.headers(headers_2)
					)

				)
				.pause(1)
		}.pause(10)
	}
}
