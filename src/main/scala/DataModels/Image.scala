package DataModels

//Generic trait for image
trait Image[T <: ImageRow[?]](protected val rows: List[T]) {
  //appends image with a new row
  def appendRow(append_row: T): Image[T]

  //returns row
  def getRow(index: Int): T =
    if (index < this.getSize)
      rows(index)
    else
      throw IndexOutOfBoundsException(
        "Index is more than number of rows in the Image")

  //returns number of rows
  def getSize: Int =
    rows.size

  //redefining of equals
  override def equals(obj: Any): Boolean =
    obj match {
      case image: Image[_] => this.rows.equals(image.rows)
      case _               => false
    }
}

case class ImageRGB(private val pixels: List[ImageRowRGB] = List.empty)
    extends Image[ImageRowRGB](pixels) {
  override def appendRow(append_row: ImageRowRGB): ImageRGB = ImageRGB(pixels :+ append_row)
}

case class ImageGreyScale(private val pixels: List[ImageRowGreyScale] = List.empty)
    extends Image[ImageRowGreyScale](pixels) {
  override def appendRow(append_row: ImageRowGreyScale): ImageGreyScale = ImageGreyScale(pixels :+ append_row)
}

case class ImageASCII(private val pixels: List[ImageRowASCII] = List.empty)
    extends Image[ImageRowASCII](pixels) {
  override def appendRow(append_row: ImageRowASCII): ImageASCII = ImageASCII(pixels :+ append_row)
}
