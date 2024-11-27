package DataModels

trait ImageRow[T <: Pixel](private var pixels: List[T]) {

  def flipRow(): Unit =
    pixels = pixels.reverse

  def appendPixel(append_pixel: T): Unit =
    pixels = pixels :+ append_pixel

  def getSize: Int =
    pixels.size

  def getPixel(index: Int): T =
    pixels(index)

  def setPixel(index: Int, pixel: T): Unit =
    pixels.updated(index, pixel)

  override def equals(obj: Any): Boolean = {
    obj match
      case row: ImageRow[T] => row.pixels == this.pixels
      case _ => false
  }
}

class ImageRowRGB(pixels: List[PixelRGB] = List.empty)
    extends ImageRow[PixelRGB](pixels)

class ImageRowGreyScale(pixels: List[PixelGreyScale] = List.empty)
    extends ImageRow[PixelGreyScale](pixels)

class ImageRowASCII(pixels: List[PixelASCII] = List.empty)
    extends ImageRow[PixelASCII](pixels)
