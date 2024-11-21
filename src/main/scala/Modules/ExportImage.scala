package Modules

import DataModels.{Data, ImageASCII, ImageRowASCII, PixelASCII}

import java.io.PrintWriter

abstract class ExportImage extends CommandType {
  protected var image: ImageASCII = ImageASCII()

  def addImage(image: ImageASCII): Unit =
    this.image = image
}

class ExportToFile(file_name: String) extends ExportImage {
  override def runCommand(): ImageASCII = exportImage()

  private def exportImage(): ImageASCII = {
    val file = new PrintWriter(file_name)
    for (i <- 0 until image.getSize)
      writeRow(file, image.getRow(i))
    file.close()
    image
  }

  private def writeRow(file: PrintWriter, row: ImageRowASCII): Unit = {
    for (i <- 0 until row.getSize)
      file.write(row.getPixel(i).returnASCII())
    file.write('\n')
  }
}

class ExportToConsole extends ExportImage {
  override def runCommand(): ImageASCII = exportImage()

  private def exportImage(): ImageASCII = {
    for (i <- 0 until image.getSize)
      writeRow(image.getRow(i))
    image
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
