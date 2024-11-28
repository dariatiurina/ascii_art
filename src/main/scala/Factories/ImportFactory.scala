package Factories

import Exceptions.NotValidImport
import Modules.Importers.{ImportImageFromPath, ImportRandomImage, Importer}

trait ImportFactory {
  def returnImport(parameter: String): Importer
}

trait ImportImageFromPathFactory extends ImportFactory {
  override def returnImport(parameter: String): ImportImageFromPath =
    new ImportImageFromPath(parameter)
}

class ImportImageFromPathJPGFactory extends ImportImageFromPathFactory

class ImportImageFromPathPNGFactory extends ImportImageFromPathFactory

class ImportImageFromPathGIFFactory extends ImportImageFromPathFactory

class ImportRandomImageFactory extends ImportFactory {
  override def returnImport(parameter: String): ImportRandomImage =
    new ImportRandomImage()
}

class MainImportFactory(
  private val importRandomImageFactory: ImportRandomImageFactory = new ImportRandomImageFactory(),
  private val importFileType: Map[String, ImportImageFromPathFactory] = Map(
    "jpg" -> new ImportImageFromPathJPGFactory(),
    "png" -> new ImportImageFromPathPNGFactory()
  )
) {
  def create(importType: String, parameter: String = ""): Importer =
    importType match {
      case "--image" =>
        importFileType
          .getOrElse(
            parameter.reverse.takeWhile(_ != '.').reverse,
            throw NotValidImport())
          .returnImport(parameter)
      case "--image-random" => importRandomImageFactory.returnImport(parameter)
      case _                => throw NotValidImport()
    }
}
