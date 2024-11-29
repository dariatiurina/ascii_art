package ModulesTest.ImportersTest

import DataModels.ImageRGB
import Modules.Converters.ConvertBufferedImageToImageRGB
import Modules.Importers.{ImportImageFromPath, ImportRandomImage}
import org.mockito.Mockito.{verify, when}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.mockito.MockitoSugar.mock

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class ImportImageTest extends AnyFunSuite {
  test("import-image-from-file") {
    val image = new File("input/test.png")
    if (image.exists && !image.isDirectory) {
      val imageImport = ImportImageFromPath("input/test.png").importImage()
      assert(imageImport.getSize > 0)
      assert(imageImport.getRow(0).getSize > 0)
    }
  }

  test("import-no-file") {
    val invalidPath = "no.png"
    val importer = new ImportImageFromPath(invalidPath)
    assertThrows[Exception] {
      importer.importImage()
    }
  }

  test("import-random-image") {
    val randomImage = ImportRandomImage().importImage()
    assert(randomImage.getSize >= 50 && randomImage.getSize <= 500)
    assert(randomImage.getRow(0).getSize >= 50 && randomImage.getRow(0).getSize <= 500)
  }
}
