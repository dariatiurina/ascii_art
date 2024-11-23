package Modules

import DataModels.{Data, ImageASCII, ImageRowASCII, PixelASCII}

import java.io.PrintWriter

abstract class ExportImage {
  def exportImage(image: ImageASCII): Unit
}

class ExportToFile(file_name: String) extends ExportImage {
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

class ExportToConsole extends ExportImage {
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

object ExportImage {
  def apply(exportType: String, parameter: String = ""): ExportImage = {
    exportType match
      case "--output-file" => new ExportToFile(parameter)
      case "--output-console" => new ExportToConsole()
  }
}
