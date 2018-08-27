package filters

import javax.inject.Inject

import org.pac4j.play.filters.SecurityFilter
import play.api.http.HttpFilters
import play.filters.cors.CORSFilter



class Filters @Inject()(corsFilter: CORSFilter, securityFilter: SecurityFilter) extends HttpFilters {

  def filters = Seq(corsFilter, securityFilter)

}
