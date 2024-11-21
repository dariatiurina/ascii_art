package DataModels

import Exceptions.NotKnownTransformationTable

abstract class TransformTable {
  protected val table: String
  def transform(pixel: PixelGreyScale): PixelASCII
}

class DefaultLinearTransformTable extends TransformTable {
  override val table: String =
    "$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\\|()1{}[]?-_+~<>i!lI;:,\"^`'. "

  override def transform(pixel: PixelGreyScale): PixelASCII = {
    val index = (pixel.returnGrey() * (table.length - 1)) / 255
    PixelASCII(table.charAt(index))
  }
}

class DefaultNonLinearTransformTable extends TransformTable {
  override val table: String = "@%#*+=-:. "
  protected val transformRule: List[Range] = List(
    (0 to 50),
    (51 to 63),
    (64 to 70),
    (71 to 75),
    (76 to 80),
    (81 to 87),
    (88 to 151),
    (152 to 185),
    (186 to 220),
    (221 to 256)
  )

  override def transform(pixel: PixelGreyScale): PixelASCII = {
    val index =
      transformRule.indexWhere(range => range.contains(pixel.returnGrey()))
    val finalIndex = if (index == -1) 0 else index
    PixelASCII(table.charAt(finalIndex))
  }
}

class CustomLinearTable(customTable: String)
    extends DefaultLinearTransformTable {
  override val table: String = customTable
}

object TransformTable{
  def apply(tableType: String, parameter: String = ""): TransformTable = {
    if(tableType == "--table"){
      parameter match
        case "linear" => new DefaultLinearTransformTable()
        case "default" => new DefaultLinearTransformTable()
        case "non-linear" => new DefaultNonLinearTransformTable()
    }
    else if(tableType == "--custom-table"){
      new CustomLinearTable(parameter)
    }
    else
      throw NotKnownTransformationTable()
  }
}
