package DataModels

import Exceptions.{MoreThanOneImageSource, NoImageInFilter}
import Modules.Converters.ConvertToASCII
import Modules.Exporters.{ExportImage, ExportToConsole}
import Modules.{Filter, FilterASCII, FilterGreyScale}
import Modules.Importers.Importer

case class UserCommands() {
  private var imageSource: Option[Importer] = None
  private var exportDestination: List[ExportImage] = List.empty
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
    ConvertToASCII(transformTable).convert(image)
  }

  def addTransformationTable(table: TransformationTable): Unit =
    transformTable = table

  def addExportDestination(exportImage: ExportImage): Unit =
    exportDestination = exportDestination.appended(exportImage)

  def addFilter(filter: Filter[?]): Unit =
    filter match
      case i: FilterASCII => filterASCII = filterASCII.appended(i)
      case i: FilterGreyScale => filterGreyScale = filterGreyScale.appended(i)

  def returnImageSource(): Option[Importer] = imageSource

  def returnExportDestination(): List[ExportImage] = {
    if(exportDestination.isEmpty)
      List(ExportToConsole())
    exportDestination
  }

  def returnFiltersASCII(): List[FilterASCII] = filterASCII
  
  def returnFiltersGreyScale(): List[FilterGreyScale] = filterGreyScale
  
  def returnTransformTable(): TransformationTable = transformTable
}
