package DataModels

import Factories.{
  MainExportFactory,
  MainFilterFactory,
  MainImportFactory,
  MainTransformationTableFactory
}

case class MainFactories() {
  private lazy val mainImportFactory: MainImportFactory = new MainImportFactory()
  private lazy val mainExportFactory: MainExportFactory = new MainExportFactory()
  private lazy val mainFilterFactory: MainFilterFactory = new MainFilterFactory()
  private lazy val mainTransformationTableFactory: MainTransformationTableFactory =
    new MainTransformationTableFactory()

  def returnImportFactory: MainImportFactory = mainImportFactory
  def returnExportFactory: MainExportFactory = mainExportFactory
  def returnFilterFactory: MainFilterFactory = mainFilterFactory
  def returnTransformationTableFactory: MainTransformationTableFactory = mainTransformationTableFactory
}
