package Modules.Importers

import DataModels.{ImageRGB, ImageRowRGB, PixelRGB}
import Exceptions.{NotKnownImageFormat, NotValidImport}
import Modules.Converters.ConvertBufferedImageToImageRGB

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import scala.util.Random

abstract class Importer {
  def importImage(): ImageRGB
}

abstract class ImportFromFileSystem(private val path: String) extends Importer

class ImportImageFromPath(private val path: String)
    extends ImportFromFileSystem(path) {
  override def importImage(): ImageRGB =
    ConvertBufferedImageToImageRGB().convert(ImageIO.read(new File(path)))
}

class ImportImageFromPathPng(private val path: String)
    extends ImportImageFromPath(path)

class ImportImageFromPathJpg(private val path: String)
    extends ImportImageFromPath(path)

class ImportImageFromPathGif(private val path: String)
    extends ImportImageFromPath(path)

class ImportRandomImage extends Importer {
  private val rand = new Random()

  override def importImage(): ImageRGB = {
    val returnImage = ImageRGB()
    val width = 50 + rand.nextInt(451)
    val height = 50 + rand.nextInt(451)
    for (i <- 0 until height)
      returnImage.appendRow(generateRow(width))
    returnImage
  }

  private def generateRow(width: Int): ImageRowRGB = {
    val returnRow = ImageRowRGB()
    for (i <- 0 until width)
      returnRow.appendPixel(generatePixel())
    returnRow
  }

  private def generatePixel(): PixelRGB =
    PixelRGB(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256))
}

object Importer {
  def apply(importType: String, parameter: String = ""): Importer =
    importType match {
      case "--image" =>
        if (parameter.endsWith(".jpg"))
          new ImportImageFromPathJpg(parameter)
        else if (parameter.endsWith(".png"))
          new ImportImageFromPathPng(parameter)
        else if (parameter.endsWith(".gif"))
          new ImportImageFromPathGif(parameter)
        else
          throw NotKnownImageFormat()
      case "--image-random" => new ImportRandomImage()
      case _                => throw NotValidImport()
    }
}
