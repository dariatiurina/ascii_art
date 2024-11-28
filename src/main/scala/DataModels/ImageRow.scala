package DataModels

trait ImageRow[T <: Pixel](private var pixels: List[T]) {

  def flipRow(): Unit =
    pixels = pixels.reverse

  def appendPixel(append_pixel: T): Unit =
    pixels = pixels :+ append_pixel

  def getSize: Int =
    pixels.size

  def getPixel(index: Int): T = {
    if (index < this.getSize)
      pixels(index)
    else
      throw IndexOutOfBoundsException("Index is more than number of Pixels in the Row")
  }

  def setPixel(index: Int, pixel: T): Unit = {
    if (index < this.getSize)
      pixels = pixels.updated(index, pixel)
    else
      throw IndexOutOfBoundsException("Index is more than number of Pixels in the Row")
  }

  override def equals(obj: Any): Boolean = {
    obj match
      case row: ImageRow[_] => row.pixels == this.pixels
      case _ => false
  }
}

class ImageRowRGB(pixels: List[PixelRGB] = List.empty)
    extends ImageRow[PixelRGB](pixels)

class ImageRowGreyScale(pixels: List[PixelGreyScale] = List.empty)
    extends ImageRow[PixelGreyScale](pixels)

class ImageRowASCII(pixels: List[PixelASCII] = List.empty)
    extends ImageRow[PixelASCII](pixels)
