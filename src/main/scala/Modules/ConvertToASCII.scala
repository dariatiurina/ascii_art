package Modules

import DataModels.{
  Data,
  ImageASCII,
  ImageGreyScale,
  ImageRowASCII,
  ImageRowGreyScale,
  PixelASCII,
  PixelGreyScale,
  TransformationTable
}

class ConvertToASCII(table: TransformationTable) {
  def convert(image: ImageGreyScale): ImageASCII = {
    var returnImage = ImageASCII()
    for (ind <- 0 until image.getSize)
      returnImage.appendRow(transformRow(image.getRow(ind)))
    returnImage
  }

  private def transformRow(row: ImageRowGreyScale): ImageRowASCII = {
    val returnRow = ImageRowASCII()
    for (ind <- 0 until row.getSize)
      returnRow.appendPixel(table.transform(row.getPixel(ind)))
    returnRow
  }
}
