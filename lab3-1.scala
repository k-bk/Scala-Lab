object Appl {
  def main(args: Array[String] = {
    println("Opening the file...")
    val inFile = scala.io.Source.fromFile("logins.txt")
    for (line <- inFile.getLines) println(line)
    println("Closing the file...")
    inFile.close
  }
}


