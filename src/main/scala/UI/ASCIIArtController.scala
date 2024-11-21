package UI

import DataModels.Axis.{x, y}
import DataModels.{Axis, DefaultLinearTransformTable, DefaultNonLinearTransformTable}
import Modules.{CommandType, ConvertToASCII, ExportToFile, FilterFlip, ImportImageFromPath, TransformImageToGreyScale}

class ASCIIArtController(ui: ASCIIArtView) {
  private def findCommand(commandLine: Seq[String]): List[CommandType] = {
    var returnLine = List[CommandType]()
    returnLine
  }

  def run(commandLine: Seq[String]): Unit =
    val commands = CommandParser(commandLine).parseCommands()
    val image_imported = commands.runImport()
    val imageGreyScale = TransformImageToGreyScale(image_imported).runCommand()
    commands.runFiltersGreyScale(imageGreyScale)
    val imageASCII = commands.transformToASCII(imageGreyScale)
    commands.runExport(imageASCII)
}

