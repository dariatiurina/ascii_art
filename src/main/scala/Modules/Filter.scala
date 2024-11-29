package Modules
import DataModels.{Axis, Image, ImageASCII, ImageGreyScale, ImageRowGreyScale}
import Exceptions.NoImageInFilter

/*Filter Class*/
trait Filter[T <: Image[?]] {
  def applyFilter(image: T): T
}

//filters for ascii images
trait FilterASCII extends Filter[ImageASCII] {
  override def applyFilter(image: ImageASCII): ImageASCII
}

//filters for greyscale images
trait FilterGreyScale extends Filter[ImageGreyScale] {
  override def applyFilter(image: ImageGreyScale): ImageGreyScale
}

class FilterFlip(private val axis: Axis) extends FilterASCII {
  //applies filter on the ASCII image and flips it depending on axis
  override def applyFilter(image: ImageASCII): ImageASCII = {
    axis match
      case Axis.x => flipX(image)
      case Axis.y => flipY(image)
  }

  private def flipY(image: ImageASCII): ImageASCII = {
    var retImage = ImageASCII()
    for (i <- 0 until image.getSize)
      retImage = retImage.appendRow(image.getRow(i).flipRow())
    retImage
  }

  private def flipX(image: ImageASCII): ImageASCII = {
    var retImage = ImageASCII()
    for (i <- image.getSize - 1 to 0 by -1)
      retImage = retImage.appendRow(image.getRow(i))
    retImage
  }
}

class FilterInvert extends FilterGreyScale {
  //inverts each pixel in the image
  override def applyFilter(image: ImageGreyScale): ImageGreyScale = {
    var returnImage = ImageGreyScale()
    for (i <- 0 until image.getSize){
      var appendRow = ImageRowGreyScale()
      for (j <- 0 until image.getRow(i).getSize)
        appendRow = appendRow.appendPixel(image.getRow(i).getPixel(j).invert())
      returnImage = returnImage.appendRow(appendRow)
    }
    returnImage
  }
}

class FilterBrightness(brightness: Int) extends FilterGreyScale {
  //changes brightness of each pixel in the image
  override def applyFilter(image: ImageGreyScale): ImageGreyScale = {
    var returnImage = ImageGreyScale()
    for (i <- 0 until image.getSize) {
      var appendRow = ImageRowGreyScale()
      for (j <- 0 until image.getRow(i).getSize) {
        appendRow = appendRow.appendPixel(image.getRow(i).getPixel(j).changeBrightness(brightness))
      }
      returnImage = returnImage.appendRow(appendRow)
    }
    returnImage
  }
}