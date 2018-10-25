package it.gov.daf.common.filters


import javax.inject.Inject
import akka.stream.Materializer
import it.gov.daf.common.CacheWrapper
import it.gov.daf.common.CredentialManager
import it.gov.daf.common.utils._
import play.api.Logger
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

<<<<<<< HEAD
class CredentialFilters @Inject() (implicit val mat: Materializer, ec: ExecutionContext, cacheWrapper: CacheWrapper) extends Filter {
=======
class CredentialFilter@Inject() (implicit val mat: Materializer, ec: ExecutionContext, cacheWrapper: CacheWrapper) extends Filter {
//class CredentialFilter@Inject() (implicit val mat: Materializer, ec: ExecutionContext) extends Filter {
>>>>>>> b8b578f564c6420923b7b73848509d7e88788dda

  private val logger = Logger(this.getClass.getName)

  def apply(nextFilter: RequestHeader => Future[Result])
           (requestHeader: RequestHeader): Future[Result] = {

    logger.debug("in credential filter")
    if( requestHeader.headers.get("authorization").nonEmpty ) {

      logger.debug("authorization header present")

      val credentials = CredentialManager.readCredentialFromRequest(requestHeader)
      credentials match {
        case Credentials(u, p, _) => logger.debug(s"caching $u");cacheWrapper.deleteCredentials(u); cacheWrapper.putCredentials(u,p)
        case _ => logger.debug("credential not cached")
      }

    }

    nextFilter(requestHeader)

  }



}
