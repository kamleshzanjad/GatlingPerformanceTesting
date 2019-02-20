package scenarios

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import io.gatling.core.ConfigKeys.data.file
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import utilities.FileOperation

object CreateProject {

  var folderName = "src\\test\\resources\\TestDataFiles\\Env_" + System.getProperty("env").toLowerCase()
  var getCurrentOrgId = System.getProperty("orgid")


  def methSelectTabProject(requestName: String) = {


    var fileName_DealroomProject = folderName + "\\templateDealRoomProject.txt"
    var template_DealroomProject = FileOperation.readFileUtf8(fileName_DealroomProject)

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

    group("SelectTabProject") {

      exec(http(requestName + "GetIncludeWeekend")
        .get("/rest/wfTask/P/settings/org/609/includeWeekend?_=1550572288926")
        .headers(headers_0)
        .resources(http(requestName = "GetuserPermission")
          .get("/rest/assumptionPermission/userPermission?_=1550572288927")
          .headers(headers_1)
          // .check(bodyBytes.is(RawFileBody("SelectTabName_0001_response.txt")))
          ,
          http(requestName + "NavToProjects")
            .post("/rest/projects/" + getCurrentOrgId)
            .headers(headers_2)
            .body(RawFileBody(fileName_DealroomProject))
          //.check(bodyBytes.is(RawFileBody("SelectTabName_0002_response.txt")))
        )
        //.check(bodyBytes.is(RawFileBody("SelectTabName_0000_response.txt")))
      ).pause(2)
    }.pause(1)
  }


  def methCreateProject(requestName: String) = {

    var projectShortName = "ProName4"
    var fileName_CreateProject = folderName + "\\templateCreateProject.txt"
    var template_CreateProject = FileOperation.readFileUtf8(fileName_CreateProject) //.toString()
      .replace("609", getCurrentOrgId)
      .replace("Project0Task", projectShortName)
      .replace("02/19/2019", "04/03/2019")

    var jsonQuery = "$..[?(@.shortName=='Newone')].projectId".replace("Newone", projectShortName)
    ///{"shortName":"Project0Task","organizationId":609,"tzOffsetPlanned":330,"category":"Project","taskTemplateId":"","autoSelectTemplateFlag":false,"projectFieldMap":{"25034":"Project0Task Test",
    // /"25036":"4","25038":"a","25040":"3046","25041":"02/19/2019","25042":"Yes","25056":"Project","26376":"1"}}


    val headers_0 = Map("X-Requested-With" -> "XMLHttpRequest")

    val headers_5 = Map(
      "Content-Type" -> "application/json",
      "Origin" -> "https://us-development.gomercatus.com",
      "X-Requested-With" -> "XMLHttpRequest")


    group("CreateProject " + requestName) {
      exec(http(requestName + "GetconditionalAssumption")
        .get("/rest/conditionalAssumption/list/" + getCurrentOrgId + "?_=1550572288928")
        .headers(headers_0)
        .resources(http(requestName)
          .get("/rest/users/adminOrgUsers/609?_=1550572288929".replace("609", getCurrentOrgId))
          .headers(headers_0)
          // .check(bodyBytes.is(RawFileBody("CreatProject_0001_response.txt")))
          ,
          http(requestName + "fetchOrgCriteriaAssumption")
            .get("/rest/wfTaskTemplate/fetchOrgCriteriaAssumption/609?_=1550572288930".replace("609", getCurrentOrgId))
            .headers(headers_0)
          //  .check(bodyBytes.is(RawFileBody("CreatProject_0002_response.txt")))
          ,
          http(requestName + "getPreferencesAssumptions")
            .get("/rest/preferences/assumptions/projectCreation?orgId=609&projectCategory=PROJECT&_=1550572288931".replace("609", getCurrentOrgId))
            .headers(headers_0)
          //.check(bodyBytes.is(RawFileBody("CreatProject_0003_response.txt")))
        )
        // .check(bodyBytes.is(RawFileBody("CreatProject_0000_response.txt")))
      )
        .pause(2)
        .exec(http(requestName + "getTaskTemplateList")
          .get("/rest/wfTaskTemplate/list/609?_=1550572288932".replace("609", getCurrentOrgId))
          .headers(headers_0)
          // .check(bodyBytes.is(RawFileBody("CreatProject_0004_response.txt")))
        )
        .pause(2)
        .exec(http(requestName + "PostCreateProjectGetProjId")
          .post("/rest/projects")
          .headers(headers_5)
          // .body(Ar)
          //.body(StringBody(template_CreateProject))//.asJson
          //.body(RawFileBody(fileName_CreateProject))
          .body(StringBody(template_CreateProject))
          .check(jsonPath(jsonQuery).optional.saveAs("projID"))
          //  .check(status.is(403))
          //.body(RawFileBody("fileName_CreateProject.txt"))
          //.check(bodyBytes.is(RawFileBody("CreatProject_0005_response.txt")))
        )
        .pause(2)
        .exec(http(requestName + "GetconditionalAssumption")
          .get("/rest/conditionalAssumption/list/609?_=1550572288933".replace("609", getCurrentOrgId))
          .headers(headers_0)
          .resources(http(requestName + "getParentAssumptionsforProjectId")
            .get("/rest/conditionalAssumption/parentAssumptions/project/${projID}/org/609?_=1550572288934".replace("609", getCurrentOrgId))
            .headers(headers_0)
            // .check(bodyBytes.is(RawFileBody("CreatProject_0007_response.txt")))
            ,
            http(requestName + "GetphaseDefinition")
              .get("/rest/phaseDefinition/projectPhaseDefData/${projID}?_=1550572288936")
              .headers(headers_0)
            //.check(bodyBytes.is(RawFileBody("CreatProject_0008_response.txt")))
            ,
            http(requestName + "GetProjectAdditionalDetails")
              .get("/rest/projectDetail/additionalDetails/${projID}?_=1550572288935")
              .headers(headers_0)
            //.check(bodyBytes.is(RawFileBody("CreatProject_0009_response.txt")))
            ,
            http(requestName + "GetProjectDetailAssumptions")
              .get("/rest/projectDetail/assumptions/${projID}/view/0?_=1550572288937")
              .headers(headers_0)
            //.check(bodyBytes.is(RawFileBody("CreatProject_0010_response.txt")))
          )
          // .check(bodyBytes.is(RawFileBody("CreatProject_0006_response.txt")))
        ).pause(2)


    }
  }.pause(10)
}

//
//byteBody={"shortName":"Project0Task","organizationId":609,"tzOffsetPlanned":330,"category":"Project","taskTemplateId":"","autoSelectTemplateFlag":false,"projectFieldMap":{"25034":"Desc","25036":"4","25038":"a","25040":"3046","25041":"02/19/2019","25042":"Yes","25056":"Project","26376":"1"}}
//
//JSESSIONID=F540DA80F9B2BC11A67C0AAD49C937C7, path=/, secure, HTTPOnly
//byteArraysBody=Success({"shortName":"Project0Task","organizationId":609,"tzOffsetPlanned":330,"category":"Project","taskTemplateId":"","autoSelectTemplateFlag":false,"projectFieldMap":{"25034":"Desc","25036":"4","25038":"a","25040":"3046","25041":"02/19/2019","25042":"Yes","25056":"Project","26376":"1"}})