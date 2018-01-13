package moe.haruue.common.filter

import javax.servlet.*
import javax.servlet.http.HttpServletRequest

/**
 *
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */
class LogFilter : Filter {
    override fun destroy() {
        println("[LogFilter] Filter Destroy.")
    }

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain) {
        println("[LogFilter] ${if (request is HttpServletRequest) { request.requestURI } else { "no-path" } }")
        chain.doFilter(request, response)
    }

    override fun init(filterConfig: FilterConfig?) {
        println("[LogFilter] Filter Initialized.")
    }

}