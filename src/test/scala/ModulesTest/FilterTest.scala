package ModulesTest

import DataModels.Axis.{x, y}
import DataModels.{ImageASCII, ImageGreyScale, ImageRowASCII, ImageRowGreyScale, PixelASCII, PixelGreyScale}
import Modules.{FilterBrightness, FilterFlip, FilterInvert}
import org.scalatest.funsuite.AnyFunSuite

class FilterTest extends AnyFunSuite {
  test("filter-flip-y") {
    val imageTest = ImageASCII(
      List(
        ImageRowASCII(List(PixelASCII('a'), PixelASCII('b'), PixelASCII('c'))),
        ImageRowASCII(List(PixelASCII('c'), PixelASCII('b'), PixelASCII('a'))),
        ImageRowASCII(List(PixelASCII('1'), PixelASCII('1'), PixelASCII('2')))
      ))
    val imageRight = ImageASCII(
      List(
        ImageRowASCII(List(PixelASCII('c'), PixelASCII('b'), PixelASCII('a'))),
        ImageRowASCII(List(PixelASCII('a'), PixelASCII('b'), PixelASCII('c'))),
        ImageRowASCII(List(PixelASCII('2'), PixelASCII('1'), PixelASCII('1')))
      ))
    assert(FilterFlip(y).applyFilter(imageTest) == imageRight)
  }

  test("filter-flip-x") {
    val imageTest = ImageASCII(
      List(
        ImageRowASCII(List(PixelASCII('2'), PixelASCII('1'), PixelASCII('1'))),
        ImageRowASCII(List(PixelASCII('a'), PixelASCII('b'), PixelASCII('c'))),
        ImageRowASCII(List(PixelASCII('c'), PixelASCII('b'), PixelASCII('a')))
      ))
    val imageRight = ImageASCII(
      List(
        ImageRowASCII(List(PixelASCII('c'), PixelASCII('b'), PixelASCII('a'))),
        ImageRowASCII(List(PixelASCII('a'), PixelASCII('b'), PixelASCII('c'))),
        ImageRowASCII(List(PixelASCII('2'), PixelASCII('1'), PixelASCII('1')))
      ))
    assert(FilterFlip(x).applyFilter(imageTest) == imageRight)
  }

  test("flip-immutable") {
    val row1 = ImageRowASCII(List(PixelASCII('a'), PixelASCII('b'), PixelASCII('c')))
    val row2 = ImageRowASCII(List(PixelASCII('c'), PixelASCII('b'), PixelASCII('a')))
    val row3 = ImageRowASCII(List(PixelASCII('1'), PixelASCII('2'), PixelASCII('3')))
    val row4 = ImageRowASCII(List(PixelASCII('3'), PixelASCII('2'), PixelASCII('1')))

    val imageTest = ImageASCII(List(row1, row2, row3, row4))
    val imageRight = ImageASCII(List(row2, row1, row4, row3))
    assert(FilterFlip(y).applyFilter(imageTest) == imageRight)
  }

  test("filter-brightness") {
    val imageTest = ImageGreyScale(
      List(
        ImageRowGreyScale(List(PixelGreyScale(50), PixelGreyScale(100), PixelGreyScale(250))),
        ImageRowGreyScale(List(PixelGreyScale(20), PixelGreyScale(80), PixelGreyScale(20))),
        ImageRowGreyScale(List(PixelGreyScale(10), PixelGreyScale(50), PixelGreyScale(90)))
      )
    )
    val imageRight = ImageGreyScale(
      List(
        ImageRowGreyScale(List(PixelGreyScale(100), PixelGreyScale(150), PixelGreyScale(255))),
        ImageRowGreyScale(List(PixelGreyScale(70), PixelGreyScale(130), PixelGreyScale(70))),
        ImageRowGreyScale(List(PixelGreyScale(60), PixelGreyScale(100), PixelGreyScale(140)))
      )
    )
    assert(FilterBrightness(50).applyFilter(imageTest) == imageRight)
  }

  test("filter-invert") {
    val imageTest = ImageGreyScale(
      List(
        ImageRowGreyScale(List(PixelGreyScale(50), PixelGreyScale(100), PixelGreyScale(250))),
        ImageRowGreyScale(List(PixelGreyScale(20), PixelGreyScale(80), PixelGreyScale(20))),
        ImageRowGreyScale(List(PixelGreyScale(10), PixelGreyScale(50), PixelGreyScale(90)))
      )
    )
    val imageRight = ImageGreyScale(
      List(
        ImageRowGreyScale(List(PixelGreyScale(205), PixelGreyScale(155), PixelGreyScale(5))),
        ImageRowGreyScale(List(PixelGreyScale(235), PixelGreyScale(175), PixelGreyScale(235))),
        ImageRowGreyScale(List(PixelGreyScale(245), PixelGreyScale(205), PixelGreyScale(165)))
      )
    )
    assert(FilterInvert().applyFilter(imageTest) == imageRight)
  }

  test("invert-empty") {
    val imageTest = ImageGreyScale()
    val imageRight = ImageGreyScale()
    assert(FilterInvert().applyFilter(imageTest) == imageRight)
  }

  test("brightness-empty") {
    val imageTest = ImageGreyScale()
    val imageRight = ImageGreyScale()
    assert(FilterBrightness(50).applyFilter(imageTest) == imageRight)
  }

  test("flip-empty") {
    val imageTest = ImageASCII()
    val imageRight = ImageASCII()
    assert(FilterFlip(x).applyFilter(imageTest) == imageRight)
  }
}
