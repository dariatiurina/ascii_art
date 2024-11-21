package Modules

import DataModels.{Data, ImageRGB}

abstract class CommandType {
  def runCommand(): Data
}