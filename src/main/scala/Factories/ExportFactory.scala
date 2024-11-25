package Factories

import Modules.Exporters.{ExporterImage, ExporterToFile, ExporterToConsole}

trait ExportFactory {
  def returnExport(parameter: String = ""): ExporterImage
}

class ExporterToFileFactory extends ExportFactory {
  override def returnExport(parameter: String): ExporterToFile = {
    new ExporterToFile(parameter)
  }
}

class ExporterToConsoleFactory extends ExportFactory {
  override def returnExport(parameter: String): ExporterToConsole = {
    new ExporterToConsole()
  }
}