package DataModelsTest

import org.scalatest.funsuite.AnyFunSuite
import DataModels.{ImageGreyScale, ImageRGB, ImageRowGreyScale, ImageRowRGB, PixelGreyScale, PixelRGB}

class ImageTest extends AnyFunSuite {
  test("append-test") {
    val imageTest = ImageRGB()
    val rowAppend = ImageRowRGB(List(PixelRGB(0, 0, 0)))
    val imageRight = ImageRGB(List(rowAppend))
    assert(imageRight == imageTest.appendRow(rowAppend))
  }

  test("image-equals") {
    val row1 = ImageRowRGB(List(PixelRGB(0, 0, 0)))
    val row2 = ImageRowRGB(List(PixelRGB(255, 255, 255)))
    val imageTest1 = ImageRGB(List(row1))
    val imageTest2 = ImageRGB(List(row1))
    val imageTest3 = ImageRGB(List(row1, row2))
    assert(imageTest1 == imageTest2)
    assert(imageTest1 != imageTest3)
    assert(imageTest2 != imageTest3)
  }

  test("get-row-correct") {
    val row1 = ImageRowRGB(List(PixelRGB(0, 0, 0), PixelRGB(255, 255, 255)))
    val row2 = ImageRowRGB(List(PixelRGB(255, 255, 255), PixelRGB(0, 0, 0)))
    val imageTest = ImageRGB(List(row1, row2))
    assert(imageTest.getRow(0) == row1)
    assert(imageTest.getRow(1) == row2)
  }

  test("get-row-empty") {
    val imageTest = ImageRGB()
    intercept[IndexOutOfBoundsException] { imageTest.getRow(0) }
  }

  test("get-size") {
    val imageTest1 = ImageRGB()
    val row1 = ImageRowRGB(List(PixelRGB(0, 0, 0), PixelRGB(255, 255, 255)))
    val imageTest2 = ImageRGB(List(row1))
    assert(imageTest1.getSize == 0)
    assert(imageTest2.getSize == 1)
  }

  test("equals") {
    val row1 = ImageRowRGB(List(PixelRGB(0, 0, 0)))
    val row2 = ImageRowGreyScale(List(PixelGreyScale(0)))
    val imageTest1 = ImageRGB(List(row1))
    val imageTest2 = ImageGreyScale(List(row2))
    assert(imageTest1 != imageTest2)
  }
}
