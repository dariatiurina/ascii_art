package Factories

import DataModels.{
  CustomLinearTable,
  LinearTransformTable,
  NonLinearTransformTable,
  TransformationTable
}
import Exceptions.NotKnownTransformationTable

trait TransformationTableFactory {
  def returnTransformationTable(parameter: String = ""): TransformationTable
}

trait TransformationTableWIthTableFactory extends TransformationTableFactory

class LinearTransformTableFactory extends TransformationTableWIthTableFactory {
  override def returnTransformationTable(
    parameter: String): LinearTransformTable =
    new LinearTransformTable()
}

class NonLinearTransformTableFactory
    extends TransformationTableWIthTableFactory {
  override def returnTransformationTable(
    parameter: String): NonLinearTransformTable =
    new NonLinearTransformTable()
}

class CustomLinearTableFactory extends TransformationTableWIthTableFactory {
  override def returnTransformationTable(parameter: String): CustomLinearTable =
    new CustomLinearTable(parameter)
}

class MainTransformationTableFactory {
  def create(tableType: String, parameter: String = ""): TransformationTable =
    if (tableType == "--table")
      parameter match {
        case "linear" =>
          LinearTransformTableFactory().returnTransformationTable(parameter)
        case "default" =>
          LinearTransformTableFactory().returnTransformationTable(parameter)
        case "non-linear" =>
          NonLinearTransformTableFactory().returnTransformationTable(parameter)
      } else if (tableType == "--custom-table")
      CustomLinearTableFactory().returnTransformationTable(parameter)
    else
      throw NotKnownTransformationTable()
}