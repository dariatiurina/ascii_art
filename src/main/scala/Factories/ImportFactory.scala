package Factories

import Exceptions.{NotKnownImageFormat, NotValidImport}
import Modules.Importers.{ImportImageFromPath, ImportImageFromPathJpg, ImportImageFromPathPng, ImportRandomImage, Importer}

trait ImportFactory {
  def returnImport(parameter: String): Importer
}

trait ImportImageFromPathFactory extends ImportFactory {
  override def returnImport(parameter: String): ImportImageFromPath =
    new ImportImageFromPath(parameter)
}

class ImportImageFromPathJPGFactory extends ImportImageFromPathFactory {
  override def returnImport(parameter: String): ImportImageFromPathJpg =
    new ImportImageFromPathJpg(parameter)
}

class ImportImageFromPathPNGFactory extends ImportImageFromPathFactory {
  override def returnImport(parameter: String): ImportImageFromPathPng =
    new ImportImageFromPathPng(parameter)
}

class ImportRandomImageFactory extends ImportFactory {
  override def returnImport(parameter: String): ImportRandomImage =
    new ImportRandomImage()
}

class MainImportFactory(
  private val importRandomImageFactory: ImportRandomImageFactory = new ImportRandomImageFactory(),
  private val importFileType: Map[String, ImportImageFromPathFactory] = Map(
    "jpg" -> new ImportImageFromPathJPGFactory(),
    "jpeg" -> new ImportImageFromPathJPGFactory(),
    "png" -> new ImportImageFromPathPNGFactory()
  )
) {
  def create(importType: String, parameter: String = ""): Importer =
    importType match {
      case "--image" => importFileType.getOrElse(parameter.reverse.takeWhile(_ != '.').reverse, throw NotKnownImageFormat()).returnImport(parameter)
      case "--image-random" => importRandomImageFactory.returnImport(parameter)
      case _                => throw NotValidImport()
    }
}
