package UITest

import Exceptions.NotKnownCommand
import UI.CommandParser
import org.scalatest.funsuite.AnyFunSuite

class CommandParserTest extends AnyFunSuite {
  test("detect-no-command") {
    intercept[NotKnownCommand] {
      CommandParser (Seq ("--this-is-not-a-command") ).parseCommands ()
    }
  }
}
