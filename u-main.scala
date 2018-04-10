object Appl {
  import utils._

  def generate(inPath: String, outPath: String, passwdLen: Int) = try {
    val inFile = scala.io.Source.fromFile(inPath)
    val outFile = new java.io.PrintWriter(outPath)
    try {
      for (login <- inFile.getLines) {
        val passwd = PasswdGen.nextPasswd(passwdLen)
        outFile.println(login + ":" + passwd)
      }
    } finally {
      inFile.close
      outFile.close
    }
  } catch {
    case ex: java.io.FileNotFoundException => println(ex.getMessage)
    case ex: Throwable => println("Default exception handler: "+ ex.getMessage)
  }

  def main(args: Array[String]) {
    if (args.length < 2) println("Usage: Appl fileIn fileOut")
    else generate(args(0), args(1), 20)
  }
}
