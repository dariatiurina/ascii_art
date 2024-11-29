package UI

import DataModels.Axis.{x, y}
import DataModels.{Axis, LinearTransformTable, NonLinearTransformTable}
import Modules.Converters.ConvertImageToGreyScale

class ASCIIArtController(ui: ASCIIArtView) {
  def run(commandLine: Seq[String]): Unit =
    try {
      val commands = CommandParser(commandLine).parseCommands()
      CommandRunner(commands).runAll()
    }
    catch
      case e: Exception => ui.displayErrorMessage(e.getMessage)
}

