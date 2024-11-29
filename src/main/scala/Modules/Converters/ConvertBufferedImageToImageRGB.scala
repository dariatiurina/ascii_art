package Modules.Converters

import DataModels.{ImageRGB, ImageRowRGB, PixelRGB}

import java.awt.image.BufferedImage

class ConvertBufferedImageToImageRGB {
  def convert(image: BufferedImage): ImageRGB = {
    var returnImage = ImageRGB(List.empty)
    for (i <- 0 until image.getHeight())
      returnImage = returnImage.appendRow(getPixelRow(i, image.getWidth, image))
    returnImage
  }

  private def getPixelRow(
    row: Int,
    width: Int,
    image: BufferedImage): ImageRowRGB = {
    var retRow = ImageRowRGB(List.empty)
    for (i <- 0 until width)
      retRow = retRow.appendPixel(getPixel(image.getRGB(i, row)))
    retRow
  }

  private def getPixel(color: Int): PixelRGB = {
    val red = (color >> 16) & 0xFF
    val green = (color >> 8) & 0xFF
    val blue = color & 0xFF
    PixelRGB(red, blue, green)
  }
}
