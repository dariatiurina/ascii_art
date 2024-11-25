package Exceptions

case class MoreThanOneImageSource(
  private val message: String = "Added more than one image source")
    extends Exception(message)

case class NoImageInFilter(
  private val message: String = "No image was added to the filter")
    extends Exception(message)

case class NotValidImport(
  private val message: String = "This Import Method is not valid")
    extends Exception(message)

case class NotKnownTransformationTable(
  private val message: String = "This transformation table is not known")
    extends Exception(message)

case class NotKnownImageFormat(
  private val message: String = "This image format is not known")
    extends Exception(message)

case class NotKnownCommand(
                                private val message: String = "This command is not known")
  extends Exception(message)