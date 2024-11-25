package Factories

import DataModels.Axis
import Modules.{Filter, FilterBrightness, FilterFlip, FilterInvert}

trait FilterFactory {
  def returnFilter(parameter: String): Filter[?]
}

trait FilterASCIIFactory extends FilterFactory

trait FilterGreyScaleFactory extends FilterFactory

class FilterFlipFactory extends FilterASCIIFactory {
  override def returnFilter(parameter: String): FilterFlip = {
    new FilterFlip(Axis(parameter))
  }
}

class FilterInvertFactory extends FilterGreyScaleFactory {
  override def returnFilter(parameter: String): FilterInvert = {
    new FilterInvert()
  }
}

class FilterBrightnessFactory extends FilterGreyScaleFactory {
  override def returnFilter(parameter: String): FilterBrightness = {
    new FilterBrightness(parameter.toInt)
  }
}

class MainFilterFactory {
  def create(filterType: String, parameter: String = ""): Filter[?] = {
    filterType match {
      case "--brightness" => FilterBrightnessFactory().returnFilter(parameter)
      case "--invert" => new FilterInvertFactory().returnFilter(parameter)
      case "--flip" => new FilterFlipFactory().returnFilter(parameter)
    }
  }
}