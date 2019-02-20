import io.gatling.recorder.GatlingRecorder
import io.gatling.recorder.config.RecorderPropertiesBuilder

object Recorder extends App {

	val props = new RecorderPropertiesBuilder()
		.simulationsFolder(IDEPathHelper.recorderSimulationsDirectory.toString)
		.simulationPackage("computerdatabase")
		.resourcesFolder(IDEPathHelper.resourcesDirectory.toString)

	GatlingRecorder.fromMap(props.build, Some(IDEPathHelper.recorderConfigFile))
}

////https://github.com/gatling/gatling-maven-plugin-demo
///$      mvn gatling:test -Dgatling.simulationClass=computerdatabase.BasicSimulation
//or simply:
//
//$   mvn gatling:test
//mvn gatling:test -Dgatling.simulationClass=computerdatabase.SampleRecord   > logFiles/a.txt