package UI

import DataModels.Axis.{x, y}
import DataModels.{Axis, DefaultLinearTransformTable, DefaultNonLinearTransformTable}
import Modules.{ConvertToASCII, ExportToFile, FilterFlip, ImportImageFromPath, ConvertImageToGreyScale}

class ASCIIArtController(ui: ASCIIArtView) {
  def run(commandLine: Seq[String]): Unit =
    val commands = CommandParser(commandLine).parseCommands()
    val image_imported = commands.runImport()
    val imageGreyScale = ConvertImageToGreyScale().convert(image_imported)
    commands.runFiltersGreyScale(imageGreyScale)
    val imageASCII = commands.transformToASCII(imageGreyScale)
    commands.runExport(imageASCII)
}

