trait Fraction {
  val num: Int
  val denom: Int
  def *(other: Fraction): Fraction
  def +(other: Fraction): Fraction
  def -(other: Fraction): Fraction
  def /(other: Fraction): Fraction
}
  
trait Loggable {
  def log(timeStamp: Long, msg: String) = println("[" + timeStamp.toString + "]: " + msg)
}

trait Simplifiable {
  def simplify(f: Fraction): Fraction = { 
    var div = 2
    var num = f.num
    var denom = f.denom
    while (num >= div) {
      if (num % div == 0 && denom % div == 0) {
        num /= div
        denom /= div
      } else {
        div += 1
      }
    }
    Fraction(num, denom);
  }
}

  
object Fraction {
  // one of the "creational patterns/idioms"
  def apply(num: Int, denom: Int, loggable: Boolean = false): Fraction =
    if (loggable) new LoggableImpl(num, denom) else new DefaultImpl(num, denom) 
  
  private class DefaultImpl(val num: Int, val denom: Int) extends Fraction {
    override def *(other: Fraction): Fraction =
      Fraction(this.num * other.num, this.denom * other.denom)
    override def +(other: Fraction): Fraction =
      Fraction(this.num * other.denom + other.num * this.denom, this.denom * other.denom)
    override def -(other: Fraction): Fraction =
      Fraction(this.num * other.denom - other.num * this.denom, this.denom * other.denom)
    override def /(other: Fraction): Fraction =
      Fraction(this.num * other.denom, this.denom * other.num)
    override def toString() = num.toString + "/" + denom.toString
  }
   
  private class LoggableImpl(num: Int, denom: Int) extends DefaultImpl(num, denom) with Loggable with Simplifiable {
    def timeStamp = System.nanoTime // to keep the example short...
    override def *(other: Fraction): Fraction = {
      log(timeStamp, "multiplying " + this.toString + " by " + other.toString)
      Fraction(this.num * other.num, this.denom * other.denom, true) // super.*(other) is not loggable
    }
    override def +(other: Fraction): Fraction = {
      log(timeStamp, "adding " + this.toString + " by " + other.toString)
      Fraction(this.num * other.denom + other.num * this.denom, this.denom * other.denom, true)
    }
    override def -(other: Fraction): Fraction = {
      log(timeStamp, "subbing " + this.toString + " by " + other.toString)
      Fraction(this.num * other.denom - other.num * this.denom, this.denom * other.denom, true)
    }
    override def /(other: Fraction): Fraction = {
      log(timeStamp, "dividing " + this.toString + " by " + other.toString)
      Fraction(this.num * other.denom, this.denom * other.num, true)
    }
  }
}
  
object Appl {
  def main(agrs: Array[String]) {
    val f1 = Fraction(3, 7)
    val f2 = Fraction(4, 9)
    val f3 = Fraction(1, 19, true)
    val f1f2 = f1 * f2
    //println(f1.toString + " * " + f2.toString + " = " + f1f2)
    //println(f3.toString + " * " + f2.toString + " * " + f1.toString + " = " + (f3 * f2 * f1))
    //println(f1.toString + " + " + f2.toString + " = " + (f1 + f2))
    //println(f3.toString + " / " + f2.toString + " + " + f1.toString + " = " + ((f3 * f2) + f1))
    //println(f1.toString + " - " + f2.toString + " = " + (f1 - f2))
  }
}
