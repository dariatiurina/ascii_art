package FactoriesTest

import Exceptions.{NotKnownImageFormat, NotValidImport}
import Factories.MainImportFactory
import Modules.Importers.{ImportImageFromPath, ImportImageFromPathJpg, ImportImageFromPathPng, ImportRandomImage}
import org.scalatest.funsuite.AnyFunSuite

class ImportFactoryTest extends AnyFunSuite {
  test("create-png") {
    assert(MainImportFactory().create("--image", "test.png").isInstanceOf[ImportImageFromPathPng])
  }

  test("create-jpg") {
    assert(MainImportFactory().create("--image", "test.jpg").isInstanceOf[ImportImageFromPathJpg])
  }

  test("create-jpeg") {
    assert(MainImportFactory().create("--image", "test.jpeg").isInstanceOf[ImportImageFromPathJpg])
  }

  test("image-random") {
    assert(MainImportFactory().create("--image-random").isInstanceOf[ImportRandomImage])
  }

  test("not-know-image-format") {
    intercept[NotKnownImageFormat] {
      MainImportFactory().create("--image", "test.pdf")
    }
  }

  test("not-know-import") {
    intercept[NotValidImport] {
      MainImportFactory().create("--image-create")
    }
  }
}
