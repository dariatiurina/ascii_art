package Modules.Converters

import DataModels.{
  ImageASCII,
  ImageGreyScale,
  ImageRowASCII,
  ImageRowGreyScale,
  PixelASCII,
  PixelGreyScale,
  TransformationTable
}

class ConvertImageToASCII(table: TransformationTable) {
  def convert(image: ImageGreyScale): ImageASCII = {
    var returnImage = ImageASCII()
    for (ind <- 0 until image.getSize)
      returnImage = returnImage.appendRow(transformRow(image.getRow(ind)))
    returnImage
  }

  private def transformRow(row: ImageRowGreyScale): ImageRowASCII = {
    var returnRow = ImageRowASCII()
    for (ind <- 0 until row.getSize)
      returnRow = returnRow.appendPixel(table.transform(row.getPixel(ind)))
    returnRow
  }
}
