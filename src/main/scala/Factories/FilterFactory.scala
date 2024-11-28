package Factories

import DataModels.Axis
import Modules.{Filter, FilterBrightness, FilterFlip, FilterInvert}

trait FilterFactory {
  def returnFilter(parameter: String): Filter[?]
}

trait FilterASCIIFactory extends FilterFactory

trait FilterGreyScaleFactory extends FilterFactory

class FilterFlipFactory extends FilterASCIIFactory {
  override def returnFilter(parameter: String): FilterFlip =
    new FilterFlip(Axis(parameter))
}

class FilterInvertFactory extends FilterGreyScaleFactory {
  override def returnFilter(parameter: String): FilterInvert =
    new FilterInvert()
}

class FilterBrightnessFactory extends FilterGreyScaleFactory {
  override def returnFilter(parameter: String): FilterBrightness =
    new FilterBrightness(parameter.toInt)
}

class MainFilterFactory(
  private val filterBrightnessFactory: FilterBrightnessFactory =
    new FilterBrightnessFactory(),
  private val filterFlipFactory: FilterFlipFactory = new FilterFlipFactory(),
  private val filterInvertFactory: FilterInvertFactory =
    new FilterInvertFactory()
) {
  def create(filterType: String, parameter: String = ""): Option[Filter[?]] =
    filterType match {
      case "--brightness" => Option(filterBrightnessFactory.returnFilter(parameter))
      case "--invert" => Option(filterInvertFactory.returnFilter(parameter))
      case "--flip"   => Option(filterFlipFactory.returnFilter(parameter))
      case _          => Option.empty
    }
}
