package FactoriesTest

import Exceptions.NotKnownExportOption
import Factories.MainExportFactory
import Modules.Exporters.{ExporterToConsole, ExporterToFile}
import org.scalatest.funsuite.AnyFunSuite

class ExportFactoryTest extends AnyFunSuite {
  test("export-to-console") {
    assert(MainExportFactory().create("--output-console").isInstanceOf[ExporterToConsole])
  }

  test("export-to-file") {
    assert(MainExportFactory().create("--output-file", "test.png").isInstanceOf[ExporterToFile])
  }

  test("not-known-export") {
    intercept[NotKnownExportOption] {
      MainExportFactory().create("--export-to-website")
    }
  }
}
