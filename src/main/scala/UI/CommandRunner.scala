package UI

import DataModels.{ImageASCII, ImageGreyScale, UserCommands}
import Exceptions.NotValidImport
import Modules.Converters.{ConvertImageToGreyScale, ConvertToASCII}

class CommandRunner(userCommands: UserCommands) {
  def runAll(): Unit = {
    val imageRGB = userCommands
      .returnImageSource()
      .getOrElse(throw NotValidImport())
      .importImage()
    var imageGreyScale = ConvertImageToGreyScale().convert(imageRGB)
    imageGreyScale = runGreyScaleFilters(imageGreyScale)
    var imageASCII = ConvertToASCII(userCommands.returnTransformTable())
      .convert(imageGreyScale)
    imageASCII = runASCIIFilters(imageASCII)
    userCommands
      .returnExportDestination()
      .foreach(exportOption => exportOption.exportImage(imageASCII))
  }

  private def runGreyScaleFilters(image: ImageGreyScale): ImageGreyScale = {
    var imageReturn = image
    for (filterGreyScale <- userCommands.returnFiltersGreyScale())
      imageReturn = filterGreyScale.applyFilter(image)
    imageReturn
  }

  private def runASCIIFilters(image: ImageASCII): ImageASCII = {
    var imageReturn = image
    for (filterASCII <- userCommands.returnFiltersASCII())
      imageReturn = filterASCII.applyFilter(image)
    imageReturn
  }
}
