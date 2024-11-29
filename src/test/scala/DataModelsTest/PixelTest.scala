package DataModelsTest

import DataModels.{PixelASCII, PixelGreyScale, PixelRGB}
import org.scalatest.funsuite.AnyFunSuite

class PixelTest extends AnyFunSuite {
  test("equals") {
    val pixel1 = PixelRGB(0, 0, 0)
    val pixel2 = PixelGreyScale(0)
    val pixel3 = PixelASCII(' ')
    assert(pixel1 != pixel2)
    assert(pixel1 != pixel3)
    assert(pixel2 != pixel3)
  }

  test("invert") {
    val pixelTest1 = PixelGreyScale(0)
    val pixelTest2 = PixelGreyScale(255)
    assert(pixelTest1.invert() == pixelTest2)
  }

  test("change-brightness-1") {
    val pixel1 = PixelGreyScale(0)
    val pixel2 = PixelGreyScale(50)
    assert(pixel1.changeBrightness(50) == pixel2)
  }

  test("change-brightness-2") {
    val pixel1 = PixelGreyScale(50)
    val pixel2 = PixelGreyScale(0)
    assert(pixel1.changeBrightness(-50) == pixel2)
  }

  test("change-brightness-3") {
    val pixel1 = PixelGreyScale(0)
    val pixel2 = PixelGreyScale(255)
    assert(pixel1.changeBrightness(256) == pixel2)
  }

  test("change-brightness-4") {
    val pixel1 = PixelGreyScale(0)
    val pixel2 = PixelGreyScale(0)
    assert(pixel1.changeBrightness(-256) == pixel2)
  }

  test("not-correct-color-1") {
    intercept[IllegalArgumentException] { PixelGreyScale(256) }
  }

  test("not-correct-color-2") {
    intercept[IllegalArgumentException] { PixelGreyScale(-200) }
  }
}
