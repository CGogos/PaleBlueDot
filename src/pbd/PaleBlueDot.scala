package pbd

import java.awt.Desktop
import java.net.URI
import scala.io.{BufferedSource, Source}

object PaleBlueDot {


  /**
   * Task 1
   *
   * Given a country name using a mix of case (upper/lower), return the country code in all lowercase letters
   *
   * Ex. If "Heard Island and McDonald Islands#HM" is a line countriesFilename and the countryName input
   * of your method is "hEaRd IsLaNd AnD mCdOnAlD iSlAnDs" the returned value is "hm"
   *
   * If countryName is not in the file, return the empty String: ""
   *
   * @param countriesFilename Name of the file containing country names and codes
   * @param countryName       The name of the country to lookup in the file with any mix of upper/lower-case
   * @return The two letter country code for countryName in lowercase letters
   */
  def getCountryCode(countriesFilename: String, countryName: String): String = {
    val countriesFile: BufferedSource = Source.fromFile(countriesFilename)
    var answer = ""
    for (line <- countriesFile.getLines()){
      val splits: Array[String] = line.split("#")
      val name = splits(0)
      val code = splits(1)
      if (name.toLowerCase() == countryName.toLowerCase()){
        answer = code.toLowerCase()
      }
    }
    answer
  }


  /**
   * Task 2
   *
   * Find the average population of cities in a country
   * regardless.
   *
   * @param countriesFilename Name of the file containing country names and codes
   * @param citiesFilename    Name of the file containing city name, population, and location data
   * @param countryName       The name of the country with any mix of upper/lower-case
   * @return The average population of cities in the given country
   */
  def averagePopulation(countriesFilename: String, citiesFilename: String, countryName: String): Double = {
    val citiesFile: BufferedSource = Source.fromFile(citiesFilename)
    citiesFile.drop(1)   // drops the header of csv file
    var countryPop: Double = 0.0  //count of total peeps in country
    var cityCount: Double = 0.0  //count for # of cit for average
    val inputCountryCode: String = getCountryCode(countriesFilename,countryName)  //something to use the file?
    for (line <- citiesFile.getLines()){
      val splits: Array[String] = line.split(",")  //splitting by the commas in csv???
      val fileCountryCode = splits(0)  //making country code in line checkable
      if (inputCountryCode == fileCountryCode) {  //checking if user cc is cc in line
        cityCount += 1  //adding to city count
        countryPop += splits(3).toDouble //adding the peeps in city to tot count
      }
    }
    val avPop: Double = countryPop/cityCount  //averaging the pop by # of cities
    avPop
  }


  /**
   * Task 3
   */

  /**
   * Returns a Map[cityName -> population] for all cities in the given county. The name of each
   * city should match exactly how it appears in citiesFilename and the population is read from the file
   * and converted to an Int. The country name may contain any mix of upper/lower-case letters.
   *
   * @param countriesFilename Name of the file containing country names and codes
   * @param citiesFilename    Name of the file containing city name, population, and location data
   * @param countryName       The name of the country with any mix of upper/lower-case
   * @param regionCode        Two digit region code with case matching the case from the cities file
   * @return A Map containing the name and population of every city in the given country
   */

  //creating a function that gets the region code for a specified city and country, idky tho
  def getRegionCode(countriesFilename: String, citiesFilename: String, cityName: String): String ={
    val citiesFile: BufferedSource = Source.fromFile(citiesFilename)
    citiesFile.drop(1)
    var regionCode: String = ""
    for (line <- citiesFile.getLines()){
      val splitzys: Array[String] = line.split(",")
      if (splitzys(1).toLowerCase() == cityName.toLowerCase()){
        regionCode += splitzys(2)
      }
    }
    regionCode
    }

  def cityPopulations(countriesFilename: String, citiesFilename: String, countryName: String, regionCode: String): Map[String, Int] = {
    val citiesFile: BufferedSource = Source.fromFile(citiesFilename)
    citiesFile.drop(1)
    val countryCode: String = getCountryCode(countriesFilename, countryName)
    var retMap: Map[String, Int] = Map()
    for (line <- citiesFile.getLines()){
      val splitzys: Array[String] = line.split(",")
      if (splitzys(0).toLowerCase() == countryCode && splitzys(2) == regionCode){
        retMap = retMap + (splitzys(1).toLowerCase() -> splitzys(3).toInt)
      }
    }
    retMap
  }


  /**
   * Returns a List of city names in the given county and with above average population for that country
   *
   * @param countriesFilename Name of the file containing country names and codes
   * @param citiesFilename    Name of the file containing city name, population, and location data
   * @param countryName       The name of the country with any mix of upper/lower-case
   * @return All city names in given country with a population > the average populations of cities in that country
   */
  def aboveAverageCities(countriesFilename: String, citiesFilename: String, countryName: String): List[String] = {
    val citiesFile: BufferedSource = Source.fromFile(citiesFilename)
    citiesFile.drop(1)
    val countryCode: String = getCountryCode(countriesFilename, countryName)
    val countryAverage: Double = averagePopulation(countriesFilename, citiesFilename, countryName)
    var retList: List[String] = List()
    for (line <- citiesFile.getLines()){
      val splitzys: Array[String] = line.split(",")
      if (splitzys(0) == countryCode && splitzys(3).toDouble > countryAverage){
        retList = retList :+ splitzys(1)
      }
    }
    retList
  }


  /**
   * Application Objective
   *
   * You find yourself stranded in an unfamiliar place with no signs of civilization. You don't have much with you,
   * but you do have a locator that gives your current latitude/longitude, a csv file of cities, and your final
   * submission to the PaleBlueDot assignment from CSE116 (What luck!). You decide that finding and walking
   * directly to the closest city will give you the best chance to survive.
   *
   * Return the closest city to the given location in terms of greater circle distance which is the shortest distance
   * needed to walk along the surface of the Earth to reach a city.
   *
   * @param citiesFilename Name of the file containing city name, population, and location data
   * @param location       A location on Earth given as a List containing latitude and longitude coordinates
   * @return The city closest to the given location as a List containing country code, city name, and region
   *         exactly as they appear in the cities file (ie. the List should have exactly 3 values to return
   *         a single city
   */

    def greaterCircleDistance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double = {

      // JavaScript code from the website into scala
      val R: Double = 6371.0 * Math.pow(10, 3) // metres
      val phi1: Double = lat1 * Math.PI / 180 // phi, lambda in radians
      val phi2: Double = lat2 * Math.PI / 180
      val deltaPhi: Double = (lat2 - lat1) * Math.PI / 180;
      val deltaLambda: Double = (lon2 - lon1) * Math.PI / 180;

      val a: Double = (Math.sin(deltaPhi / 2) * Math.sin(deltaPhi / 2)) +
        (Math.cos(phi1) * Math.cos(phi2) * Math.sin(deltaLambda / 2) * Math.sin(deltaLambda / 2))
      val c: Double = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))

      val d: Double = R * c // in metres

      d
    }

  def closestCity(citiesFilename: String, location: List[Double]): List[String] = {
    val citiesFile: BufferedSource = Source.fromFile(citiesFilename)
    citiesFile.drop(1)
    var retList: List[String] = List()
    var checkDistance: Double = 7000000.0 * Math.pow(10, 3)
    for (line <- citiesFile.getLines()){
      val splitzys: Array[String] = line.split(",")
      if (splitzys(0).length == 2) {
        val lat2: Double = splitzys(4).toDouble
        val lon2: Double = splitzys(5).toDouble
        val distance: Double = greaterCircleDistance(location.head, location(1), lat2, lon2)
        if (distance < checkDistance) {
          checkDistance = distance
          retList = List(splitzys(0), splitzys(1), splitzys(2))
        }
      }
    }
    retList
    //List("Country Code", "City Name", "Region")
  }


  /**
   * Helper Method
   *
   * Opens Google Maps at a specific location. The location is a List containing the latitude then longitude as Doubles
   *
   * @param location The location to open in the format List(Latitude, Longitude)
   */
  def openMap(location: List[Double]): Unit = {
    if (Desktop.isDesktopSupported && Desktop.getDesktop.isSupported(Desktop.Action.BROWSE)) {
      val url: String = "http://maps.google.com/maps?t=m&q=loc:" + location.head.toString + "+" + location(1).toString
      Desktop.getDesktop.browse(new URI(url))
    } else {
      println("Opening the browser not supported")
    }
  }


  def main(args: Array[String]): Unit = {
    openMap(List(43.002743, -78.7874136))
  }

}
