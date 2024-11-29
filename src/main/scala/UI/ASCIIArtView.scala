package UI

class ASCIIArtView {
  def displayMessage(message: String): Unit =
    println(message)

  def displayErrorMessage(message: String): Unit =
    println("!!! Error: " + message)
}
