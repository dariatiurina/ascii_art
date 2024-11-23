package Modules

import DataModels.{Data, ImageRGB, ImageRowRGB, PixelRGB}

import java.awt.image.BufferedImage

class ConvertBufferedImageToImageRGB {
  def convert(image: BufferedImage): ImageRGB = {
    val returnImage = ImageRGB(List.empty)
    for (i <- 0 until image.getHeight())
      returnImage.appendRow(getPixelRow(i, image.getWidth, image))
    returnImage
  }

  private def getPixelRow(
    row: Int,
    width: Int,
    image: BufferedImage): ImageRowRGB = {
    val retRow = ImageRowRGB(List.empty)
    for (i <- 0 until width)
      retRow.appendPixel(getPixel(image.getRGB(i, row)))
    retRow
  }

  private def getPixel(color: Int): PixelRGB = {
    val blue = color & 0xff
    val green = (color & 0xff00) >> 8
    val red = (color & 0xff0000) >> 16
    PixelRGB(red, blue, green)
  }
}
