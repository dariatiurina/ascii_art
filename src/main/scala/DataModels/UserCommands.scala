package DataModels

import Exceptions.{MoreThanOneImageSource, NoImageInFilter}
import Modules.{ConvertToASCII, ExportImage, ExportToConsole, Filter, FilterASCII, FilterGreyScale, Importer}

class UserCommands {
  private var imageSource: Option[Importer] = None
  private var exportDestination: List[ExportImage] = List(ExportToConsole())
  private var filters: List[Filter[?]] = List.empty
  private var filterASCII: List[FilterASCII] = List.empty
  private var filterGreyScale: List[FilterGreyScale] = List.empty
  private var transformTable: TransformationTable = DefaultLinearTransformTable()

  def addSource(importer: Importer): Unit = {
    if (imageSource.isEmpty)
      imageSource = Some(importer)
    else
      throw MoreThanOneImageSource()
  }

  def transformToASCII(image: ImageGreyScale): ImageASCII = {
    ConvertToASCII(transformTable).convert(image)
  }

  def runExport(image: ImageASCII): Unit = {
    for (exportOption <- exportDestination)
      exportOption.exportImage(image)
  }

  def runImport() : ImageRGB = {
    imageSource.getOrElse(throw NoImageInFilter()).importImage()
  }

  def addTransformationTable(table: TransformationTable): Unit =
    transformTable = table
  
  def runFiltersASCII(image: ImageASCII) : ImageASCII = {
    var returnImage = image
    for (filter <- filterASCII) {
      returnImage = filter.applyFilter(image)
    }
    returnImage
  }

  def runFiltersGreyScale(image: ImageGreyScale): ImageGreyScale = {
    var returnImage = image
    for (filter <- filterGreyScale) {
      returnImage = filter.applyFilter(image)
    }
    returnImage
  }

  def addExportDestination(exportImage: ExportImage): Unit =
    exportDestination = exportDestination.appended(exportImage)

  def addFilter(filter: Filter[?]): Unit =
    if (filter != null)
      filters = filters.appended(filter)

  def sortFilters(): Unit = {
    for (filter <- filters) {
      filter match
        case i: FilterASCII => filterASCII = filterASCII.appended(i)
        case i: FilterGreyScale => filterGreyScale = filterGreyScale.appended(i)
    }
  }
}
