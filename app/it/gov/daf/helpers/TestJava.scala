package it.gov.daf.helpers

import apiModels.Vocabulary

object TestJava {

  def test() :Vocabulary =  {
    var voc = new Vocabulary()
    voc.setId(0L)
    voc.setName("Ale")
    voc
  }
}
