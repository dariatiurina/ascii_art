package Factories

import DataModels.{CustomLinearTable, LinearTransformTable, NonLinearTransformTable, TransformationTable}

trait TransformationTableFactory {
  def returnTransformationTable(parameter: String = ""): TransformationTable
}

trait TransformationTableWIthTableFactory extends TransformationTableFactory

class LinearTransformTableFactory extends TransformationTableWIthTableFactory {
  override def returnTransformationTable(parameter: String): LinearTransformTable =
    new LinearTransformTable()
}

class NonLinearTransformTableFactory extends TransformationTableWIthTableFactory {
  override def returnTransformationTable(parameter: String): NonLinearTransformTable =
    new NonLinearTransformTable()
}

class CustomLinearTableFactory extends TransformationTableWIthTableFactory {
  override def returnTransformationTable(parameter: String): CustomLinearTable =
    new CustomLinearTable(parameter)
}