package ModulesTest.ConvertersTest

import DataModels.{ImageASCII, ImageGreyScale, ImageRowASCII, ImageRowGreyScale, LinearTransformTable, PixelASCII, PixelGreyScale}
import Modules.Converters.ConvertImageToASCII
import org.scalatest.funsuite.AnyFunSuite

class ConvertImageToASCIITest extends AnyFunSuite {
  test("convert") {
    val imageTest = ImageGreyScale(
      List(
        ImageRowGreyScale(
          List(PixelGreyScale(0), PixelGreyScale(0), PixelGreyScale(0))),
        ImageRowGreyScale(
          List(PixelGreyScale(100), PixelGreyScale(120), PixelGreyScale(100))),
        ImageRowGreyScale(
          List(PixelGreyScale(255), PixelGreyScale(255), PixelGreyScale(255)))
      ))
    val imageRight = ImageASCII(
      List(
        ImageRowASCII(List(PixelASCII('$'), PixelASCII('$'), PixelASCII('$'))),
        ImageRowASCII(List(PixelASCII('U'), PixelASCII('v'), PixelASCII('U'))),
        ImageRowASCII(List(PixelASCII(' '), PixelASCII(' '), PixelASCII(' ')))
      )
    )
    assert(ConvertImageToASCII(LinearTransformTable()).convert(imageTest) == imageRight)
  }
}
