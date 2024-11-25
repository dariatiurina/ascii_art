package DataModels

trait Pixel {
  def returnPixel(): Pixel
}

case class PixelRGB(
  private var red: Int,
  private var blue: Int,
  private var green: Int)
    extends Pixel {
  override def returnPixel(): PixelRGB = PixelRGB(red, blue, green)

  def returnRed(): Int = red
  def returnBlue(): Int = blue
  def returnGreen(): Int = green
}

case class PixelGreyScale(private var grey: Int) extends Pixel {
  override def returnPixel(): PixelGreyScale = PixelGreyScale(grey)

  def returnGrey(): Int = grey

  def invert(): Unit =
    grey = 255 - grey

  def changeBrightness(brightness: Int): Unit =
    grey = math.max(math.min(grey + brightness, 255), 0)

}

case class PixelASCII(private var ascii: Char) extends Pixel {
  override def returnPixel(): PixelASCII = PixelASCII(ascii)

  def returnASCII(): Char = ascii
}
