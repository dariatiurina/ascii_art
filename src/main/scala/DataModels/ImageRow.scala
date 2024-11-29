package DataModels

//Generic trait for ImageRow
trait ImageRow[T <: Pixel](private val pixels: List[T]) {

  def flipRow(): ImageRow[T] = new ImageRow(pixels.reverse) {}

  def appendPixel(append_pixel: T): ImageRow[T] = new ImageRow(pixels :+ append_pixel) {}

  def getSize: Int =
    pixels.size

  def getPixel(index: Int): T = {
    if (index < this.getSize)
      pixels(index)
    else
      throw IndexOutOfBoundsException("Index is more than number of Pixels in the Row")
  }

  override def equals(obj: Any): Boolean = {
    obj match
      case row: ImageRow[_] => row.getClass == this.getClass && row.pixels == this.pixels
      case _ => false
  }

  override def toString: String = {
    var ret = ""
    for(pixel <- pixels)
      ret = ret +  pixel + " "
    ret
  }
}

case class ImageRowRGB(private val pixels: List[PixelRGB] = List.empty)
    extends ImageRow[PixelRGB](pixels) {
  override def flipRow(): ImageRowRGB = ImageRowRGB(pixels.reverse)

  override def appendPixel(append_pixel: PixelRGB): ImageRowRGB = ImageRowRGB(pixels :+ append_pixel)
}

case class ImageRowGreyScale(private val pixels: List[PixelGreyScale] = List.empty)
    extends ImageRow[PixelGreyScale](pixels) {
  override def flipRow(): ImageRowGreyScale = ImageRowGreyScale(pixels.reverse)

  override def appendPixel(append_pixel: PixelGreyScale): ImageRowGreyScale = ImageRowGreyScale(pixels :+ append_pixel)
}

case class ImageRowASCII(private val pixels: List[PixelASCII] = List.empty)
    extends ImageRow[PixelASCII](pixels) {
  override def flipRow(): ImageRowASCII = ImageRowASCII(pixels.reverse)

  override def appendPixel(append_pixel: PixelASCII): ImageRowASCII = ImageRowASCII(pixels :+ append_pixel)
}
