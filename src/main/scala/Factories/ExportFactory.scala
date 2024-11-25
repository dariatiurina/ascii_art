package Factories

import Modules.Exporters.{ExporterImage, ExporterToFile, ExporterToConsole}

trait ExportFactory {
  def returnExport(parameter: String = ""): ExporterImage
}

class ExporterToFileFactory extends ExportFactory {
  override def returnExport(parameter: String): ExporterToFile =
    new ExporterToFile(parameter)
}

class ExporterToConsoleFactory extends ExportFactory {
  override def returnExport(parameter: String): ExporterToConsole =
    new ExporterToConsole()
}

class MainExportFactory(
  private val exporterToFileFactory: ExporterToFileFactory =
    new ExporterToFileFactory(),
  private val exporterToConsoleFactory: ExporterToConsoleFactory =
    new ExporterToConsoleFactory()) {
  def create(exportType: String, parameter: String): ExporterImage =
    exportType match {
      case "--output-file" => exporterToFileFactory.returnExport(parameter)
      case "--output-console" =>
        exporterToConsoleFactory.returnExport(parameter)
    }
}
