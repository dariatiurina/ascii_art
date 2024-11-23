package Modules.Converters

import DataModels.*

class ConvertImageToGreyScale {
  def convert(imageRGB: ImageRGB): ImageGreyScale = {
    val returnImage = ImageGreyScale()
    for (ind <- 0 until imageRGB.getSize)
      returnImage.appendRow(transformRow(imageRGB.getRow(ind)))
    returnImage
  }

  private def transformRow(row: ImageRowRGB): ImageRowGreyScale = {
    val returnRow = ImageRowGreyScale()
    for (ind <- 0 until row.getSize)
      returnRow.appendPixel(transformPixel(row.getPixel(ind)))
    returnRow
  }

  private def transformPixel(pixel: PixelRGB): PixelGreyScale =
    PixelGreyScale(
      ((0.3 * pixel.returnRed()) + (0.59 * pixel
        .returnGreen()) + (0.11 * pixel.returnBlue())).toInt)
}
