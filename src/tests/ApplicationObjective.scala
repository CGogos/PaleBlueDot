package tests

import org.scalatest._
import pbd.PaleBlueDot

class ApplicationObjective extends FunSuite {

  val countriesFile: String = "data/countries.txt"
  val citiesFilename: String = "data/cities.csv"

//  test("1 - greaterCircleDistance hard coded longitude"){
//
//    val testCases: Map[Double, Double] = Map(
//      42.55 -> 5225.0 * Math.pow(10.0, 3.0),   // la massana
//    )
//
//    //longitude of la massana
//    val lon1: Double = 1.5166667
//    //compute distance from dubai
//    val lat2: Double = 25.258172
//    val lon2: Double = 55.304717
//
//    for ((input, expectedOutput) <- testCases) {
//      val actualOutput: Double = PaleBlueDot.greaterCircleDistance(input, lon1, lat2, lon2)
//      assert((actualOutput - expectedOutput).abs < (0.03 * expectedOutput), input + "->" + actualOutput)
//      //The error here needs to be less than 3% of the expected value
//    }
//  }
//
//  test("2 - greaterCircleDistance multiple cities") {
//
//    val testCases: Map[List[Double], Double] = Map(
//      List(42.55,1.5166667) -> 5225.0 * Math.pow(10.0, 3.0),      // la massana
//      List(24.466667, 54.366667) -> 129.2 * Math.pow(10.0, 3.0),  // abu dhabi
//      List(17.05, -61.8) -> 11740.0 * Math.pow(10.0, 3.0),        // all saints
//    )
//
//    //compute distance from dubai
//    val lat2: Double = 25.258172
//    val lon2: Double = 55.304717
//
//    for ((input, expectedOutput) <- testCases) {
//      val actualOutput: Double = PaleBlueDot.greaterCircleDistance(input.head, input(1) , lat2, lon2)
//      assert((actualOutput - expectedOutput).abs < (0.03 * expectedOutput), input + "->" + actualOutput)
//      //The error here needs to be less than 3% of the expected value
//    }
//  }

  test("1 - closest city testing") {

    val testCases: Map[List[Double], List[String]] = Map(            // Starting from
      List(43.000879, -78.792168) -> List("NY", "north tonawanda", "us"),    // sitting in nsc hall
      List(43.16,-77.72) -> List("NY", "greece", "us"),  //
      List(43.2049865,-77.5902785) -> List("NY", "irondequoit", "us"),  //
    )


    for ((input, expectedOutput) <- testCases) {
      val actualOutput: List[String] = PaleBlueDot.closestCity(citiesFilename, input)
      assert(actualOutput.sorted == expectedOutput.sorted, input + "->" + actualOutput)
    }
  }

}
