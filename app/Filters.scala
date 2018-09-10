import it.gov.daf.common.filters.authentication.SecurityFilter
import javax.inject.Inject
import play.api.http.DefaultHttpFilters
import play.filters.cors.CORSFilter

class Filters @Inject()(corsFilter: CORSFilter, securityFilter :SecurityFilter)
  extends DefaultHttpFilters(corsFilter, securityFilter)
