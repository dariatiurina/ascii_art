package DataModels

import Exceptions.{MoreThanOneImageSource, NoImageInFilter}
import Modules.Converters.ConvertImageToASCII
import Modules.Exporters.{ExporterImage, ExporterToConsole}
import Modules.{Filter, FilterASCII, FilterGreyScale}
import Modules.Importers.Importer

case class UserCommands() {
  private var imageSource: Option[Importer] = None
  private var exportDestination: List[ExporterImage] = List.empty
  private var filterASCII: List[FilterASCII] = List.empty
  private var filterGreyScale: List[FilterGreyScale] = List.empty
  private var transformTable: TransformationTable = LinearTransformTable()

  def addSource(importer: Importer): Unit = {
    if (imageSource.isEmpty)
      imageSource = Some(importer)
    else
      throw MoreThanOneImageSource()
  }

  def transformToASCII(image: ImageGreyScale): ImageASCII = {
    ConvertImageToASCII(transformTable).convert(image)
  }

  def addTransformationTable(table: TransformationTable): Unit =
    transformTable = table

  def addExportDestination(exportImage: ExporterImage): Unit =
    exportDestination = exportDestination.appended(exportImage)

  def addFilter(filter: Filter[?]): Unit =
    filter match
      case i: FilterASCII => filterASCII = filterASCII.appended(i)
      case i: FilterGreyScale => filterGreyScale = filterGreyScale.appended(i)

  def returnImageSource(): Option[Importer] = imageSource

  def returnExportDestination(): List[ExporterImage] = {
    if(exportDestination.isEmpty)
      List(ExporterToConsole())
    exportDestination
  }

  def returnFiltersASCII(): List[FilterASCII] = filterASCII
  
  def returnFiltersGreyScale(): List[FilterGreyScale] = filterGreyScale
  
  def returnTransformTable(): TransformationTable = transformTable
}
