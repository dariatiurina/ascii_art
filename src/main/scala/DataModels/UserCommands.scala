package DataModels

import Exceptions.{MoreThanOneImageSource, NoImageInFilter}
import Modules.{ConvertToASCII, ExportImage, Filter, FilterASCII, FilterGreyScale, Importer}

class UserCommands {
  private var imageSource: Option[Importer] = None
  private var exportDestination: List[ExportImage] = List.empty
  private var filters: List[Filter[?]] = List.empty
  private var filterASCII: List[FilterASCII] = List.empty
  private var filterGreyScale: List[FilterGreyScale] = List.empty
  private var transformTable: TransformTable = DefaultLinearTransformTable()

  def addSource(importer: Importer): Unit = {
    if (imageSource.isEmpty)
      imageSource = Some(importer)
    else
      throw MoreThanOneImageSource()
  }

  def transformToASCII(image: ImageGreyScale): ImageASCII = {
    ConvertToASCII(image, transformTable).runCommand()
  }

  def runExport(image: ImageASCII): Unit = {
    for (exportOption <- exportDestination)
      exportOption.addImage(image)
      exportOption.runCommand()
  }

  def runImport() : ImageRGB = {
    imageSource.getOrElse(throw NoImageInFilter()).runCommand()
  }

  def addTransformationTable(table: TransformTable): Unit =
    transformTable = table
  
  def runFiltersASCII(image: ImageASCII) : ImageASCII = {
    var returnImage = image
    for (filter <- filterASCII) {
      filter.addImage(returnImage)
      returnImage = filter.runCommand()
    }
    returnImage
  }

  def runFiltersGreyScale(image: ImageGreyScale): ImageGreyScale = {
    var returnImage = image
    for (filter <- filterGreyScale) {
      filter.addImage(returnImage)
      returnImage = filter.runCommand()
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
