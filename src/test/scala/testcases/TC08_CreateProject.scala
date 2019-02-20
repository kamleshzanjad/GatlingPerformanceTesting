package testcases

import io.gatling.core.Predef._
import scenarios.ConfigHttpProtocol
import scenarios._
import utilities.{FileOperation, ReadConfigData}

///DealRoomRedning for Assets
import scala.concurrent.duration._
import com.typesafe.config.ConfigFactory

import java.io.File
//import com.typesafe.config.{ Config, ConfigFactory }


class TC08_CreateProject extends ReadConfigData {


  val feederUrl = csv(this.dataFeederLogin).circular
  //dataFeederLogin

  val CreateProject100Tasks = scenario("ScnCreateProject100Tasks").feed(feederUrl)
    .exec(
      LoginOperation.methodLogin("Setup"),
      CreateProject.methSelectTabProject("SelectProject"),
      CreateProject.methCreateProject("CreateProject0Task")
     // CreateProject.CreateProjectsWithTask("ReqCreateProject100Tasks", "Projects",100)
     // SelectParentTab.NavigateToTabName("Scn: 10 users performing Deal RoomRendering for 200 Projects over 10 minutes: ", "Projects")
  )




//   mvn gatling:test -Dgatling.simulationClass=testcases.TC08_CreateProject  -Dusers=2 -Denv=usrelease -DGroupToExecute=All> logFiles/TC08_CreateProject.txt
  //mvn gatling:test -Dgatling.simulationClass=testcases.TC08_CreateProject  -Dusers=2 -Denv=usdevelopment -DGroupToExecute=All> logFiles/TC08_CreateProject.txt
// mvn gatling:test -Dgatling.simulationClass=testcases.TC08_CreateProject  -Dusers=2 -Denv=QA > logFiles/TC08_CreateProject.txt


  setUp(
      CreateProject100Tasks.inject(rampUsers(userCount) during (60 seconds))
      //,
      //scnOtherUsersActivity.inject(rampUsers(5) during (60 seconds))
    ).protocols(ConfigHttpProtocol.httpProtocol)
    // .maxDuration(60 minutes)

}


//    mvn gatling:test -Dgatling.simulationClass=testcases.TC08_CreateProject  -Dusers=2 -Denv=QAEnv > logFiles/TC08_CreateProject.txt
//  mvn gatling:test -Dgatling.simulationClass=testcases.TC08_CreateProject   > logFiles/TC08_CreateProject.txt
/// JAVA_OPTS="-Dusers=4 -Denv=3600"   mvn gatling:test -Dgatling.simulationClass=testcases.TC08_CreateProject   > logFiles/TC08_CreateProject.txt
//  -Dgatling.dataFolder=

/// set JAVA_OPTS="-DuserCount=4 -Durl=qa" &&
//set JAVA_OPTS=-DuserCount=4 -Durl=qa
// set JAVA_OPTS=-DuserCount=3
//set JAVA_OPTS="-DuserCount=3"
// mvn gatling:test -Dgatling.simulationClass=testcases.TC08_CreateProject   > logFiles/TC08_CreateProject.txt
//set JAVA_OPTS=-DuserCount=2 -DflowRepeatCount=2 -DdefinitionId=1123612

//set JAVA_OPTS="-Dusers=500 -Dramp=3600"  mvn gatling:test -Dgatling.simulationClass=testcases.TC08_CreateProject   > logFiles/TC08_CreateProject.txt
// JAVA_OPTS=-DuserCount=2 -DflowRepeatCount=3 -DdefinitionId=102168 -DtestServerUrl=https://someURL -DenvAuthenticationHeaderFromPostman="Basic UWRZm9aGwsxFsB1V7RXK0OlB5cmZvcm1hbmNldGVzdDE="