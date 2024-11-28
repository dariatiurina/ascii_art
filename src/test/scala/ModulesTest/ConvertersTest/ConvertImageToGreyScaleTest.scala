package ModulesTest.ConvertersTest

import DataModels.{ImageGreyScale, ImageRGB, ImageRowGreyScale, ImageRowRGB, PixelGreyScale, PixelRGB}
import Modules.Converters.ConvertImageToGreyScale
import org.scalatest.funsuite.AnyFunSuite

class ConvertImageToGreyScaleTest extends AnyFunSuite {
  test("convert-to-greyscale") {
    val imageTest = ImageRGB(
      List(
        ImageRowRGB(List(PixelRGB(50, 50, 100), PixelRGB(100, 150, 120), PixelRGB(70, 190, 110))),
        ImageRowRGB(List(PixelRGB(60, 80, 10), PixelRGB(20, 25, 10), PixelRGB(75, 90, 105))),
        ImageRowRGB(List(PixelRGB(100, 70, 80), PixelRGB(55, 25, 70), PixelRGB(85, 115, 85)))
      )
    )
    val imageRight = ImageGreyScale(
      List(
        ImageRowGreyScale(List(PixelGreyScale(79), PixelGreyScale(117), PixelGreyScale(106))),
        ImageRowGreyScale(List(PixelGreyScale(32), PixelGreyScale(14), PixelGreyScale(94))),
        ImageRowGreyScale(List(PixelGreyScale(84), PixelGreyScale(60), PixelGreyScale(88)))
      )
    )
    assert(ConvertImageToGreyScale().convert(imageTest) == imageRight)
  }
}
