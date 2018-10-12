package it.gov.daf.common.filters.authentication

import akka.stream.Materializer
import javax.inject.{Inject, Singleton}
import org.pac4j.core.config.Config
import org.pac4j.play.store.PlaySessionStore
import play.api.Configuration
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class SecurityFilter @Inject()(mat: Materializer, configuration: Configuration, playSessionStore: PlaySessionStore, config: Config, ec: ExecutionContext) extends org.pac4j.play.filters.SecurityFilter(mat, configuration, playSessionStore, config, ec) {

  override def apply(nextFilter: (RequestHeader) => Future[play.api.mvc.Result])
                    (request: RequestHeader): Future[play.api.mvc.Result] = {
    super.apply(nextFilter)(request)
  }
}
