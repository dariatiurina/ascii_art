package ModulesTest.ExportersTest

import DataModels.{ImageASCII, ImageRowASCII, PixelASCII}
import Modules.Exporters.{ExporterToConsole, ExporterToFile}
import org.scalatest.funsuite.AnyFunSuite

import java.io.{ByteArrayOutputStream, PrintStream}
import java.nio.file.{Files, Paths}

class ExporterImageTest extends AnyFunSuite {
  test("export-image-file") {
    val tempFilePath = Files.createTempFile("exporter_test", ".txt").toString
    val exporter = new ExporterToFile(tempFilePath)
    val imageTest = ImageASCII(List(
      ImageRowASCII(List(PixelASCII('a'), PixelASCII('b'), PixelASCII('c'))),
      ImageRowASCII(List(PixelASCII('b'), PixelASCII('a'), PixelASCII('c'))),
      ImageRowASCII(List(PixelASCII('c'), PixelASCII('b'), PixelASCII('a')))
    ))
    exporter.exportImage(imageTest)
    val content = new String(Files.readAllBytes(Paths.get(tempFilePath)), "UTF-8")
    val expectedContent = "abc\nbac\ncba\n"
    assert(content == expectedContent)
    Files.delete(Paths.get(tempFilePath))
  }

  test("export-image-console") {
    val outStream = new ByteArrayOutputStream()
    val printStream = new PrintStream(outStream)
    val imageTest = ImageASCII(List(
      ImageRowASCII(List(PixelASCII('a'), PixelASCII('b'), PixelASCII('c'))),
      ImageRowASCII(List(PixelASCII('b'), PixelASCII('a'), PixelASCII('c'))),
      ImageRowASCII(List(PixelASCII('c'), PixelASCII('b'), PixelASCII('a')))
    ))
    Console.withOut(printStream) {
      val exporter = new ExporterToConsole()
      exporter.exportImage(imageTest)
    }
    val content = outStream.toString.replaceAll("\r\n", "\n")
    val expectedContent = "abc\nbac\ncba\n"
    assert(content == expectedContent)
  }

  test("export-console-empty") {
    val outStream = new ByteArrayOutputStream()
    val printStream = new PrintStream(outStream)
    val imageTest = ImageASCII()
    Console.withOut(printStream) {
      val exporter = new ExporterToConsole()
      exporter.exportImage(imageTest)
    }
    val content = outStream.toString.replaceAll("\r\n", "\n")
    val expectedContent = ""
    assert(content == expectedContent)
  }
}
