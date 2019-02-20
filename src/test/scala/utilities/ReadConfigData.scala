package utilities

import java.io.File

import com.typesafe.config.ConfigFactory
import io.gatling.core.Predef._


class ReadConfigData extends Simulation {

  var userCount:Int=1
  var envName=""
  var url=""
  var orgid=""
  var dataFeederLogin=""
  var GroupToExecute="All"

      try {
        userCount = Integer.parseInt(System.getProperty("users")) ///Integer.getInteger("users", 10).toInt both working
        envName = System.getProperty("env")

        val configPath = "src\\test\\resources\\TestDataFiles\\Env_" + envName.toLowerCase() +"\\"
        println("Folder  name for environment selected: " +configPath)
        val config = ConfigFactory.parseFile(new File(configPath + "AppConfig.conf"))

        if(System.getProperty("GroupToExecute")==null){
          GroupToExecute="All"
        }else{
          GroupToExecute=System.getProperty("GroupToExecute")
        }


        url = config.getString("data.url")
        orgid = config.getString("data.orgid")
        dataFeederLogin=configPath+"LoginCredentials.csv"

        System.setProperty("url", url)
        System.setProperty("orgid", orgid)


       /* try {
          url = config.getString(this.envName+".url")
          orgid = config.getString(this.envName+".orgid")
        } catch {
          case e: Exception => println(s"${envName} - environment is not found in AppConfig.conf file.\nException  is " + e)
        }*/

      } catch {
       /// case ce: ConfigException =>println("No configuration setting found for key ")
        case e: Exception => println("\n\n\nError in Finding file AppConfig in path src test resources TestDataFiles Env_environmentname.\nException is " + e+"\n\n\n")

      }


  def getAppUrl() = {
    this.url
  }

}


