package Modules.Converters

import DataModels.*

class ConvertImageToGreyScale {
  def convert(imageRGB: ImageRGB): ImageGreyScale = {
    var returnImage = ImageGreyScale()
    for (ind <- 0 until imageRGB.getSize)
      returnImage = returnImage.appendRow(transformRow(imageRGB.getRow(ind)))
    returnImage
  }

  private def transformRow(row: ImageRowRGB): ImageRowGreyScale = {
    var returnRow = ImageRowGreyScale()
    for (ind <- 0 until row.getSize)
      returnRow = returnRow.appendPixel(transformPixel(row.getPixel(ind)))
    returnRow
  }

  private def transformPixel(pixel: PixelRGB): PixelGreyScale =
    PixelGreyScale(
      ((0.3 * pixel.returnRed()) + (0.59 * pixel
        .returnGreen()) + (0.11 * pixel.returnBlue())).toInt)
}
