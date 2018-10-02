package it.gov.daf.utils

import javax.inject.Inject
import play.api.{Configuration, Environment}

class AppConfig @Inject()(playConfig: Configuration){

  val gitVocUrl = playConfig.getString("git-voc.url")
  val gitVocPath = playConfig.getString("git-voc.path")

}

object ConfigReader {
  private val config = new AppConfig(Configuration.load(Environment.simple()))

  require(config.gitVocUrl.nonEmpty, "A git repo url must be specified")
  require(config.gitVocPath.nonEmpty, "A path of file must be specified")

  def getGitVocUrl = config.gitVocUrl.get
  def getGitVocPath = config.gitVocPath.get
}
