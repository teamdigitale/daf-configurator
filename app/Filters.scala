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
import it.gov.daf.common.filters.CredentialFilter
import javax.inject.Inject
import it.gov.daf.common.filters.authentication.SecurityFilter
import play.api.http.DefaultHttpFilters
import play.filters.cors.CORSFilter

//class Filters @Inject()(corsFilter: CORSFilter, dafConfiguratorSecurityFilter :DafConfiguratorSecurityFilter, credentialFilter: CredentialFilter)
//  extends DefaultHttpFilters(corsFilter, dafConfiguratorSecurityFilter, credentialFilter)


class Filters @Inject()(corsFilter: CORSFilter, credentialFilter: CredentialFilter)
  extends DefaultHttpFilters(corsFilter, credentialFilter)
