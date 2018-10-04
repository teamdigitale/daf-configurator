package it.gov.daf.utils

import javax.inject.Inject
import play.api.{Configuration, Environment}

class AppConfig @Inject()(playConfig: Configuration){

  val vocPath = playConfig.getString("voc.path")

}

object ConfigReader {
  private val config = new AppConfig(Configuration.load(Environment.simple()))

  def getVocPath = config.vocPath.get
}
