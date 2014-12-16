package cakesolutions.example2

import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.StreamingContext._
import scala.spores._

object SparkWorkflow {

  def apply(host: String, port: Int): Spore[StreamingContext, Unit] = spore {
    (ssc: StreamingContext) => {
      // Create a socket stream on target host:port and count the
      // words in input stream of \n delimited text (eg. generated by 'nc')
      // Note that no duplication in storage level only for running locally.
      // Replication necessary in distributed scenario for fault tolerance.
      val lines = ssc.socketTextStream(capture(host), capture(port), StorageLevel.MEMORY_AND_DISK_SER)
      // FIXME: FlatMappedDStream (return type of lines.flatMap(..)) is package private, and so presumably the spore macros can not access the class instance(?) - hence an explicit DStream type cast?
      val words = lines.flatMap(_.split(" ")).asInstanceOf[DStream[String]].map(_.trim.toLowerCase)
      val wordCounts = words.map(x => (x, 1)).reduceByKey(_ + _)
      wordCounts.print()
    }
  }

}

/**
 * Counts words in UTF8 encoded, '\n' delimited text received from the network every second.
 *
 * Usage: example2.Main <hostname> <port>
 * <hostname> and <port> describe the TCP server that Spark Streaming would connect to receive data.
 *
 * To run this on your local machine, you need to first run a Netcat server:
 *    nc -lk 9999
 * and then run the example:
 *    spark-submit --class cakesolutions.example2.Main --master local[4] --jars `ls -m ./sparky/target/universal/stage/lib/ | tr -d " \n"` ./sparky/target/universal/stage/lib/cakesolutions.sparky-0.1.0-SNAPSHOT.jar localhost 9999
 */
object Main {

  def main(args: Array[String]) {
    if (args.length < 2) {
      System.err.println(
        "Usage: example2.Main <hostname> <port>")
      System.exit(1)
    }

    val Seq(host, port) = args.toSeq
    val sparkConf = new SparkConf().setAppName("Example")
    // Create the context and set the batch size
    val ssc = new StreamingContext(sparkConf, Seconds(5))

    SparkWorkflow(host, port.toInt)(ssc)

    ssc.start()
    ssc.awaitTermination()
  }

}
