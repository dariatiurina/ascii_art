package FactoriesTest

import Factories.MainFilterFactory
import Modules.{FilterBrightness, FilterFlip, FilterInvert}
import org.scalatest.funsuite.AnyFunSuite

class FilterFactoryTest extends AnyFunSuite {
  test("flip") {
    assert(MainFilterFactory().create("--flip", "x").get.isInstanceOf[FilterFlip])
  }

  test("brightness") {
    assert(MainFilterFactory().create("--brightness", "50").get.isInstanceOf[FilterBrightness])
  }

  test("invert") {
    assert(MainFilterFactory().create("--invert").get.isInstanceOf[FilterInvert])
  }

  test("no-filter") {
    assert(MainFilterFactory().create("--scale", "0.25").isEmpty)
  }
}
