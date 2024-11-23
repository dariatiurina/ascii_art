package Modules
import DataModels.{Axis, Image, ImageASCII, ImageGreyScale}
import Exceptions.NoImageInFilter

abstract class Filter[T <: Image[?]] {
  def applyFilter(image: T): T
}

abstract class FilterASCII extends Filter[ImageASCII] {
  override def applyFilter(image: ImageASCII): ImageASCII
}

abstract class FilterGreyScale extends Filter[ImageGreyScale] {
  override def applyFilter(image: ImageGreyScale): ImageGreyScale
}

class FilterFlip(axis: Axis) extends FilterASCII {
  override def applyFilter(image: ImageASCII): ImageASCII = {
    axis match
      case Axis.x => flipX(image)
      case Axis.y => flipY(image)
  }

  private def flipX(image: ImageASCII): ImageASCII = {
    image.flipRows()
    image
  }

  private def flipY(image: ImageASCII): ImageASCII = {
    image.flipImage()
    image
  }
}

class FilterInvert extends FilterGreyScale {
  override def applyFilter(image: ImageGreyScale): ImageGreyScale = {
    val returnImage = image
    for (i <- 0 until returnImage.getSize){
      for (j <- 0 until returnImage.getRow(i).getSize)
        returnImage.getRow(i).getPixel(j).invert()
    }
    returnImage
  }
}

class FilterBrightness(brightness: Int) extends FilterGreyScale {
  override def applyFilter(image: ImageGreyScale): ImageGreyScale = {
    val returnImage = image
    for (i <- 0 until returnImage.getSize) {
      for (j <- 0 until returnImage.getRow(i).getSize)
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