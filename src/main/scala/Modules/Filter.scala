package Modules
import DataModels.{Axis, Image, ImageASCII, ImageGreyScale}
import Exceptions.NoImageInFilter

trait Filter[T <: Image[?]] {
  def applyFilter(image: T): T
}

trait FilterASCII extends Filter[ImageASCII] {
  override def applyFilter(image: ImageASCII): ImageASCII
}

trait FilterGreyScale extends Filter[ImageGreyScale] {
  override def applyFilter(image: ImageGreyScale): ImageGreyScale
}

class FilterFlip(axis: Axis) extends FilterASCII {
  override def applyFilter(image: ImageASCII): ImageASCII = {
    axis match
      case Axis.x => flipX(image)
      case Axis.y => flipY(image)
  }

  private def flipY(image: ImageASCII): ImageASCII = {
    val imageRet = image.copy()
    imageRet.flipRows()
    imageRet
  }

  private def flipX(image: ImageASCII): ImageASCII = {
    val imageRet = image.copy()
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