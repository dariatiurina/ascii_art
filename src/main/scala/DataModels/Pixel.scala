package DataModels

trait Pixel {
  def returnPixel(): Pixel
}

case class PixelRGB(
  private var red: Int,
  private var blue: Int,
  private var green: Int)
    extends Pixel {
  require(red >= 0 && red < 256 && blue >= 0 && blue < 256 && green >= 0 && green < 256)

  override def returnPixel(): PixelRGB = PixelRGB(red, blue, green)

  def returnRed(): Int = red
  def returnBlue(): Int = blue
  def returnGreen(): Int = green

  override def equals(obj: Any) : Boolean = {
    obj match
      case p: PixelRGB => p.red == this.red && p.blue == this.blue && p.green == this.green
      case _ => false
  }
}

case class PixelGreyScale(private var grey: Int) extends Pixel {
  override def returnPixel(): PixelGreyScale = PixelGreyScale(grey)

  require(grey >= 0 && grey < 256)

  def returnGrey(): Int = grey

  def invert(): Unit =
    grey = 255 - grey

  def changeBrightness(brightness: Int): Unit =
    grey = math.max(math.min(grey + brightness, 255), 0)

  override def equals(obj: Any): Boolean = {
    obj match
      case p: PixelGreyScale => p.grey == this.grey
      case _ => false
  }
}

case class PixelASCII(private var ascii: Char) extends Pixel {
  override def returnPixel(): PixelASCII = PixelASCII(ascii)

  def returnASCII(): Char = ascii

  override def equals(obj: Any): Boolean = {
    obj match
      case p: PixelASCII => p.ascii == this.ascii
      case _ => false
  }
}
