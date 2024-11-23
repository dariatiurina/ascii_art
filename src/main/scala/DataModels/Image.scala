package DataModels

abstract class Image[T <: ImageRow[?]](private var rows: List[T]){
  def apply(): Image[?] = new Image(List.empty) {}

  def flipImage(): Unit =
    rows = rows.reverse

  def flipRows(): Unit =
    rows.foreach(_.flipRow())

  def appendRow(append_row: T): Unit =
    rows = rows :+ append_row

  def getRow(index: Int): T =
    rows(index)

  def getSize: Int =
    rows.size
}

case class ImageRGB(private var pixels: List[ImageRowRGB] = List.empty)
    extends Image[ImageRowRGB](pixels)

case class ImageGreyScale(
  private var pixels: List[ImageRowGreyScale] = List.empty)
    extends Image[ImageRowGreyScale](pixels)

case class ImageASCII(private var pixels: List[ImageRowASCII] = List.empty)
    extends Image[ImageRowASCII](pixels)
