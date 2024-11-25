package Factories

import Modules.Importers.{ImportImageFromPath, ImportRandomImage, Importer}

trait ImportFactory {
  def returnImport(parameter: String): Importer
}

class ImportImageFromPathFactory extends ImportFactory {
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
