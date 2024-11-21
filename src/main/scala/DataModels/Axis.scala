package DataModels

enum Axis:
  case x, y

object Axis{
  def apply(axis: String): Axis = {
    axis match
      case "x" => Axis.x
      case "y" => Axis.y
  }
}
