package UI

import DataModels.{
  ImageASCII,
  ImageRGB,
  LinearTransformTable,
  MainFactories,
  TransformationTable,
  UserCommands
}
import Factories.{
  MainExportFactory,
  MainFilterFactory,
  MainImportFactory,
  MainTransformationTableFactory
}
import Modules.Converters.ConvertImageToGreyScale
import Modules.Exporters.ExporterImage
import Modules.Filter
import Modules.Importers.Importer
import org.scalactic.Or

class CommandParser(private val commandLine: Seq[String]) {
  private var userCommandsParsed: UserCommands = UserCommands()
  private val mainFactories: MainFactories = MainFactories()

  def parseCommands(): UserCommands = {
    for ((line, index) <- commandLine.zipWithIndex)
      detectCaseCommand(line, index)
    userCommandsParsed
  }

  private def detectCaseCommand(commandCheck: String, index: Int): Unit = {
    var parameter: String = ""
    if (index + 1 < commandLine.size)
      parameter = commandLine(index + 1)
    if (parameter.startsWith("--"))
      parameter = ""
    if (commandCheck.startsWith("--image"))
      userCommandsParsed.addSource(
        mainFactories.returnImportFactory.create(commandCheck, parameter))
    else if (commandCheck.startsWith("--output"))
      userCommandsParsed.addExportDestination(
        mainFactories.returnExportFactory.create(commandCheck, parameter))
    else if (commandCheck.startsWith("--table") || commandCheck.startsWith(
               "--custom-table"))
      userCommandsParsed.addTransformationTable(
        mainFactories.returnTransformationTableFactory
          .create(commandCheck, parameter))
    else if (commandCheck.startsWith("--"))
      userCommandsParsed.addFilter(
        mainFactories.returnFilterFactory.create(commandCheck, parameter))
  }
}
