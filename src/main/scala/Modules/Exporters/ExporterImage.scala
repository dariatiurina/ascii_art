package Modules.Exporters

import DataModels.{ImageASCII, ImageRowASCII, PixelASCII}

import java.io.PrintWriter

trait ExporterImage {
  def exportImage(image: ImageASCII): Unit
}

class ExporterToFile(file_name: String) extends ExporterImage {
  override def exportImage(image: ImageASCII): Unit = {
    val file = new PrintWriter(file_name)
    for (i <- 0 until image.getSize)
      writeRow(file, image.getRow(i))
    file.close()
  }

  private def writeRow(file: PrintWriter, row: ImageRowASCII): Unit = {
    for (i <- 0 until row.getSize)
      file.write(row.getPixel(i).returnASCII())
    file.write('\n')
  }
}

class ExporterToConsole extends ExporterImage {
  override def exportImage(image: ImageASCII): Unit = {
    for (i <- 0 until image.getSize)
      writeRow(image.getRow(i))
  }

  private def writeRow(row: ImageRowASCII): Unit = {
    for (i <- 0 until row.getSize)
      print(row.getPixel(i).returnASCII())
    println()
  }
}

object ExporterImage {
  def apply(exportType: String, parameter: String = ""): ExporterImage = {
    exportType match
      case "--output-file" => new ExporterToFile(parameter)
      case "--output-console" => new ExporterToConsole()
  }
}
