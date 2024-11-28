package DataModelsTest

import DataModels.{ImageRowRGB, PixelGreyScale, PixelRGB}
import org.scalatest.funsuite.AnyFunSuite

class ImageRowTest extends AnyFunSuite {
  test("flip-row") {
    val row1 = ImageRowRGB(List(PixelRGB(255, 255, 255), PixelRGB(0, 0, 0)))
    val row2 = ImageRowRGB(List(PixelRGB(0, 0, 0), PixelRGB(255, 255, 255)))
    row1.flipRow()
    assert(row1 == row2)
  }

  test("append-row") {
    val row1 = ImageRowRGB(List(PixelRGB(255, 255, 255)))
    val row2 = ImageRowRGB(List(PixelRGB(255, 255, 255), PixelRGB(0, 0, 0)))
    row1.appendPixel(PixelRGB(0, 0, 0))
    assert(row1 == row2)
  }

  test("get-size") {
    val row1 = ImageRowRGB(List(PixelRGB(255, 255, 255)))
    val row2 = ImageRowRGB()
    assert(row1.getSize == 1)
    assert(row2.getSize == 0)
  }

  test("flip-row-correct") {
    val row1 = ImageRowRGB(List(PixelRGB(255, 255, 255), PixelRGB(0, 0, 0)))
    val row2 = ImageRowRGB(List(PixelRGB(0, 0, 0), PixelRGB(255, 255, 255)))
    row1.flipRow()
    assert(row1 == row2)
  }

  test("flip-row-empty") {
    val row1 = ImageRowRGB()
    val row2 = ImageRowRGB()
    row1.flipRow()
    assert(row1 == row2)
  }

  test("get-pixel-correct") {
    val row1 = ImageRowRGB(List(PixelRGB(255, 255, 255), PixelRGB(0, 0, 0)))
    assert(row1.getPixel(0) == PixelRGB(255, 255, 255))
    assert(row1.getPixel(1) == PixelRGB(0, 0, 0))
  }

  test("get-pixel-empty") {
    val row1 = ImageRowRGB()
    intercept[IndexOutOfBoundsException] { row1.getPixel(0) }
  }

  test("set-pixel-correct") {
    val row1 = ImageRowRGB(List(PixelRGB(255, 255, 255), PixelRGB(0, 0, 0)))
    val row2 = ImageRowRGB(List(PixelRGB(0, 0, 0), PixelRGB(0, 0, 0)))
    row1.setPixel(0, PixelRGB(0, 0, 0))
    println(row1.getPixel(0))
    println(row2)
    assert(row1 == row2)
  }

  test("set-pixel-empty") {
    val row1 = ImageRowRGB()
    intercept[IndexOutOfBoundsException] {
      row1.setPixel(0, PixelRGB(0, 0, 0))
    }
  }

  test("equals") {
    val row1 = List(PixelRGB(0, 0, 0))
    val row2 = List(PixelGreyScale(0))
    assert(row1 != row2)
  }
}
