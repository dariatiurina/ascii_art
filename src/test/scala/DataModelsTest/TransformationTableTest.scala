package DataModelsTest

import DataModels.{CustomLinearTable, LinearTransformTable, NonLinearTransformTable, PixelASCII, PixelGreyScale}
import org.scalatest.funsuite.AnyFunSuite

class TransformationTableTest extends AnyFunSuite {
  test("linear-transform-1") {
    val transformationTable = LinearTransformTable()
    val pixelTest = PixelGreyScale(0)
    val pixelRight = PixelASCII('$')
    assert(transformationTable.transform(pixelTest) == pixelRight)
  }

  test("linear-transform-2") {
    val transformationTable = LinearTransformTable()
    val pixelTest = PixelGreyScale(255)
    val pixelRight = PixelASCII(' ')
    assert(transformationTable.transform(pixelTest) == pixelRight)
  }

  test("non-linear-transform-1") {
    val transformationTable = NonLinearTransformTable()
    val pixelTest = PixelGreyScale(60)
    val pixelRight = PixelASCII('%')
    assert(transformationTable.transform(pixelTest) == pixelRight)
  }

  test("non-linear-transform-2") {
    val transformationTable = NonLinearTransformTable()
    val pixelTest = PixelGreyScale(160)
    val pixelRight = PixelASCII(':')
    assert(transformationTable.transform(pixelTest) == pixelRight)
  }

  test("custom-table-linear-transform-1") {
    val transformationTable = CustomLinearTable("123456")
    val pixelTest = PixelGreyScale(70)
    val pixelRight = PixelASCII('2')
    assert(transformationTable.transform(pixelTest) == pixelRight)
  }

  test("custom-table-linear-transform-2") {
    val transformationTable = CustomLinearTable("123456")
    val pixelTest = PixelGreyScale(120)
    val pixelRight = PixelASCII('3')
    assert(transformationTable.transform(pixelTest) == pixelRight)
  }
}
