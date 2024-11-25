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

class MainTransformationTableFactory(
  private val linearTransformTableFactory: LinearTransformTableFactory =
    new LinearTransformTableFactory(),
  private val nonLinearTransformTableFactory: NonLinearTransformTableFactory =
    new NonLinearTransformTableFactory(),
  private val customLinearTableFactory: CustomLinearTableFactory =
    new CustomLinearTableFactory()
) {
  def create(tableType: String, parameter: String = ""): TransformationTable =
    if (tableType == "--table")
      parameter match {
        case "linear" =>
          linearTransformTableFactory.returnTransformationTable(parameter)
        case "default" =>
          linearTransformTableFactory.returnTransformationTable(parameter)
        case "non-linear" =>
          nonLinearTransformTableFactory.returnTransformationTable(parameter)
      } else if (tableType == "--custom-table")
      customLinearTableFactory.returnTransformationTable(parameter)
    else
      throw NotKnownTransformationTable()
}
