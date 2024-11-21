import UI.{ASCIIArtController, ASCIIArtView}

import scala.io.StdIn.readLine

@main def main(args: String*): Unit = {
  val view = ASCIIArtView()
  val controller = ASCIIArtController(view)
  controller.run(args)
}
