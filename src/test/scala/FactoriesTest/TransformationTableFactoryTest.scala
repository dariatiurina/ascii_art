package FactoriesTest

import DataModels.{CustomLinearTable, LinearTransformTable, NonLinearTransformTable}
import Exceptions.NotKnownTransformationTable
import Factories.MainTransformationTableFactory
import org.scalatest.funsuite.AnyFunSuite

class TransformationTableFactoryTest extends AnyFunSuite {
  test("linear") {
    assert(MainTransformationTableFactory().create("--table", "linear").isInstanceOf[LinearTransformTable])
  }

  test("default") {
    assert(MainTransformationTableFactory().create("--table", "default").isInstanceOf[LinearTransformTable])
  }

  test("non-linear") {
    assert(MainTransformationTableFactory().create("--table", "non-linear").isInstanceOf[NonLinearTransformTable])
  }

  test("custom-table") {
    assert(MainTransformationTableFactory().create("--custom-table", "abcdefg").isInstanceOf[CustomLinearTable])
  }

  test("no-table-parameter") {
    intercept[NotKnownTransformationTable] {
      MainTransformationTableFactory().create("--table", "not-default")
    }
  }

  test("no-table-name") {
    intercept[NotKnownTransformationTable] {
      MainTransformationTableFactory().create("--table-new", "abcdefg")
    }
  }
}
