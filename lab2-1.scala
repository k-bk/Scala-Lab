import scala.beans.BeanProperty

class Person (val name: String, @BeanProperty var surname: String, private val id: String) {
  def getName(): String = name + " " + surname
}

object Appl {
  def main(args: Array[String]) {
    val p = new Person("Jan", "Kowalski", "1234567890")
    println(p.getName)
  }
}
