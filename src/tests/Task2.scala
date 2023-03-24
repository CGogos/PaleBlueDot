package tests

import org.scalatest._
import pbd.PaleBlueDot

class Task2 extends FunSuite {

  val countriesFile: String = "data/countries.txt"
  val citiesFilename: String = "data/cities.csv"

  // create a map where the keys are the countries and
  // the values are the expected output population
  //
  //think of countries with a low amount of cities in them
  //maybe ae or ad? look into the cities file to see

  test("1 - Countries with Normal Caps") {
    val testCases: Map[String,Double] = Map(
      "Andorra" -> 8409.5,
      "United Arab Emirates" -> 761668.333,
      "Anguilla" -> 1379.0,    // this is a good test of a country with only 1 city
    )


    for((input, expectedOutput) <- testCases){
      val actualOutput: Double = PaleBlueDot.averagePopulation(countriesFile,citiesFilename,input)
      assert((actualOutput-expectedOutput).abs < 0.01, input + "->" + actualOutput)
      //do the minus thing and then check if its less than allowed tolerance
    }
  }

    test("2 - One City Test") {
      val testCases: Map[String, Double] = Map(
        "Anguilla" -> 1379.0, // this is a good test of a country with only 1 city
      )


      for ((input, expectedOutput) <- testCases) {
        val actualOutput: Double = PaleBlueDot.averagePopulation(countriesFile, citiesFilename, input)
        assert((actualOutput - expectedOutput).abs < 0.01, input + "->" + actualOutput)
      }
  }

  test("3 - Random capitalization") {
    val testCases: Map[String, Double] = Map(
      "AnDoRRa" -> 8409.5,
      "UniTEd ARaB EmiRaTEs" -> 761668.333,
      "AnGuIlLa" -> 1379.0,
    )


    for ((input, expectedOutput) <- testCases) {
      val actualOutput: Double = PaleBlueDot.averagePopulation(countriesFile, citiesFilename, input)
      assert((actualOutput - expectedOutput).abs < 0.01, input + "->" + actualOutput)
    }
  }

}
