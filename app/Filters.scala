//import javax.inject.Inject
//import org.pac4j.play.filters.SecurityFilter
//import play.api.http.HttpFilters
//
//class Filters @Inject()(securityFilter: SecurityFilter) extends HttpFilters {
//
//  def filters = Seq(securityFilter)
//
//}
//
//
import it.gov.daf.common.filters.{CredentialFilters, SecurityFilter}
import javax.inject.Inject
import play.api.http.DefaultHttpFilters
import play.filters.cors.CORSFilter

class Filters @Inject()(corsFilter: CORSFilter, securityFilter : SecurityFilter, credentialFilter: CredentialFilters)
  extends DefaultHttpFilters(corsFilter, securityFilter, credentialFilter)
