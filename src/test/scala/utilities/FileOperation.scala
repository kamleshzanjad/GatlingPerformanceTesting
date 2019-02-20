package utilities

import akka.util.ByteString

import scala.io.{BufferedSource, Codec, Source}
import scala.util.Try

object FileOperation{
//: Try[String] = Try
  def readFileUtf8(path: String) ={
    val source: BufferedSource = Source.fromFile(path)(Codec.UTF8)
    val content =source.mkString  ///source.getLines().take(1).toList ///source.mkString
    source.close()
    println("File content is: " +content)
    content

    // From byte array to string
   // val s = System.Text.Encoding.UTF8.GetString(content, 0, content.Length);

//    val src = Source.fromFile(path)
//    val line = src.getLines.take(1).toList
//    src.close
//    line

  }

  def fileToByteStr(filename : String) : ByteString =
    ByteString(Source.fromFile(filename).mkString)

}
