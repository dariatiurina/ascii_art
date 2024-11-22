package Modules

import DataModels.{Data, ImageRGB, ImageRowRGB, PixelRGB}

import java.awt.image.BufferedImage

class GetImage(var image: BufferedImage = null) extends CommandType {
  override def runCommand(): ImageRGB = {
    val returnImage = ImageRGB(List.empty)
    for (i <- 0 until image.getHeight())
      returnImage.appendRow(getPixelRow(i))
    returnImage
  }

  private def getPixelRow(row: Int): ImageRowRGB = {
    val retRow = ImageRowRGB(List.empty)
    for (i <- 0 until image.getWidth())
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
