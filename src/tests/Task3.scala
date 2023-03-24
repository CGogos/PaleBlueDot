package tests

import org.scalatest._
import pbd.PaleBlueDot

class Task3 extends FunSuite {

  val countriesFile: String = "data/countries.txt"
  val citiesFilename: String = "data/cities.csv"


  //Testing getting the region Code

//  test("1 - regioncode bizzzz good caps") {
//    val testCases: Map[String, String] = Map(
//      "la massana" -> "04"
//    )
//
//    for ((input, expectedOutput) <- testCases) {
//      val actualOutput: String = PaleBlueDot.getRegionCode(countriesFile, citiesFilename, input)
//      assert(actualOutput == expectedOutput, input + "->" + actualOutput)
//    }
//  }
//
//  test("2 - regioncode bizzzz BAD caps + more cities") {
//    val testCases: Map[String, String] = Map(
//      "lA maSSana" -> "04",
//      "sANt jULia de loRIa" -> "06",
//      "tOKZar" -> "33"
//    )
//
//    for ((input, expectedOutput) <- testCases) {
//      val actualOutput: String = PaleBlueDot.getRegionCode(countriesFile, citiesFilename, input)
//      assert(actualOutput == expectedOutput, input + "->" + actualOutput)
//    }
//  }

  //Testing for cityPopulations()

  test("1 - cityPopulations Normal Caps") {
    val testCases: Map[String, Map[String,Int]] = Map(
      "Afghanistan" -> Map(
        "andarab" -> 27034,
        "baglan" -> 108481,
        "nahrin" -> 22389
      )
    )

    val regionCode: String = "03"

    for ((input, expectedOutput) <- testCases) {
      val actualOutput: Map[String, Int] = PaleBlueDot.cityPopulations(countriesFile, citiesFilename, input, regionCode)
      assert(actualOutput == expectedOutput, input + "->" + actualOutput)
    }
  }


  //Testing for aboveAverageCities()

  test("1 - aboveAverageCities Normal Caps") {
    val testCases: Map[String, List[String]] = Map(
      "Andorra" -> List("les escaldes"),
      "United Arab Emirates" -> List("dubai"),
      "Antigua and Barbuda" -> List("all saints","liberta"),
    )


    for ((input, expectedOutput) <- testCases) {
      val actualOutput: List[String] = PaleBlueDot.aboveAverageCities(countriesFile, citiesFilename, input)
      assert(actualOutput.sorted == expectedOutput.sorted, input + "->" + actualOutput)
    }
  }


  test("2 - aboveAverageCities One City") {
    val testCases: Map[String, List[String]] = Map(
      "Anguilla" -> List(),
    )


    for ((input, expectedOutput) <- testCases) {
      val actualOutput: List[String] = PaleBlueDot.aboveAverageCities(countriesFile, citiesFilename, input)
      assert(actualOutput.sorted == expectedOutput.sorted, input + "->" + actualOutput)
    }
  }

  test("3 - aboveAverageCities Random Caps") {
    val testCases: Map[String, List[String]] = Map(
      "ANdORra" -> List("les escaldes"),
      "UniTEd ArAB EmIRaTEs" -> List("dubai"),
      "ANtiGUa AnD BARbuDA" -> List("all saints", "liberta")
    )


    for ((input, expectedOutput) <- testCases) {
      val actualOutput: List[String] = PaleBlueDot.aboveAverageCities(countriesFile, citiesFilename, input)
      assert(actualOutput.sorted == expectedOutput.sorted, input + "->" + actualOutput)
    }
  }

//  test("4 - aboveAverageCities equal to average") {
//    val testCases: Map[String, List[String]] = Map(
//      "" -> List(),
//    )
//
//
//    for ((input, expectedOutput) <- testCases) {
//      val actualOutput: List[String] = PaleBlueDot.aboveAverageCities(countriesFile, citiesFilename, input)
//      assert(actualOutput.sorted == expectedOutput.sorted, input + "->" + actualOutput)
//    }
//  }
//
//  test("5 - aboveAverageCities off by one") {
//    val testCases: Map[String, List[String]] = Map(
//      "" -> List(),
//    )
//
//
//    for ((input, expectedOutput) <- testCases) {
//      val actualOutput: List[String] = PaleBlueDot.aboveAverageCities(countriesFile, citiesFilename, input)
//      assert(actualOutput.sorted == expectedOutput.sorted, input + "->" + actualOutput)
//    }
//  }

}
