package ModulesTest.ConvertersTest

import DataModels.{ImageRGB, ImageRowRGB, PixelRGB}
import Modules.Converters.ConvertBufferedImageToImageRGB
import org.scalatest.funsuite.AnyFunSuite

import java.awt.image.BufferedImage

class ConvertBufferedImageToImageRGBTest extends AnyFunSuite {
  test("convert-to-rgb") {
    val bufferedImage = new BufferedImage(2, 2, BufferedImage.TYPE_INT_RGB)
    bufferedImage.setRGB(0, 0, (0xFF & 0) << 24 | (0xFF & 255) << 16 | (0xFF & 0) << 8 | (0xFF & 0))
    bufferedImage.setRGB(1, 0, (0xFF & 0) << 24 | (0xFF & 0) << 16 | (0xFF & 255) << 8 | (0xFF & 0))
    bufferedImage.setRGB(0, 1, (0xFF & 0) << 24 | (0xFF & 0) << 16 | (0xFF & 0) << 8 | (0xFF & 255))
    bufferedImage.setRGB(1, 1, (0xFF & 0) << 24 | (0xFF & 255) << 16 | (0xFF & 255) << 8 | (0xFF & 255))

    val expectedImage = ImageRGB(List(
      ImageRowRGB(List(PixelRGB(255, 0, 0), PixelRGB(0, 0, 255))),
      ImageRowRGB(List(PixelRGB(0, 255, 0), PixelRGB(255, 255, 255)))
    ))
    val resultImage = ConvertBufferedImageToImageRGB().convert(bufferedImage)
    assert(resultImage == expectedImage)
  }
}
