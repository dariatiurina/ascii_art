package Modules
import DataModels.{Axis, Data, Image, ImageASCII, ImageGreyScale}
import Exceptions.NoImageInFilter

abstract class Filter[T <: Image[?]] extends CommandType {
  protected var image: Option[T] = None

  def addImage(addImage: T) : Unit = image = Some(addImage)

  override def runCommand(): Image[?]
}

abstract class FilterASCII extends Filter[ImageASCII] {

  override def runCommand(): ImageASCII
}

abstract class FilterGreyScale extends Filter[ImageGreyScale] {
  override def runCommand(): ImageGreyScale
}

class FilterFlip(axis: Axis) extends FilterASCII {
  override def runCommand(): ImageASCII = {
    axis match
      case Axis.x => flipX()
      case Axis.y => flipY()
  }

  private def flipX(): ImageASCII = {
    image.getOrElse(throw NoImageInFilter()).flipRows()
    image.get
  }

  private def flipY(): ImageASCII = {
    image.getOrElse(throw NoImageInFilter()).flipImage()
    image.get
  }
}

class FilterInvert extends FilterGreyScale {
  override def runCommand(): ImageGreyScale = {
    val returnImage = image.getOrElse(throw NoImageInFilter())
    for (i <- 0 until returnImage.getSize()){
      for (j <- 0 until returnImage.getRow(i).getSize())
        returnImage.getRow(i).getPixel(j).invert()
    }
    returnImage
  }
}

class FilterBrightness(brightness: Int) extends FilterGreyScale {
  override def runCommand(): ImageGreyScale = {
    val returnImage = image.getOrElse(throw  NoImageInFilter())
    for (i <- 0 until returnImage.getSize()) {
      for (j <- 0 until returnImage.getRow(i).getSize())
        returnImage.getRow(i).getPixel(j).changeBrightness(brightness)
    }
    returnImage
  }
}

object Filter{
  def apply(filterType: String, parameter: String = ""): Filter[?] = {
    filterType match
      case "--brightness" => new FilterBrightness(parameter.toInt)
      case "--invert" => new FilterInvert()
      case "--flip" => new FilterFlip(Axis(parameter))
  }
}