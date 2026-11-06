package pt.isel.daw

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class TimingFilter : Filter {
    private val logger = LoggerFactory.getLogger(TimingFilter::class.java)
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        // Ensure it's an HTTP request
        if (request is HttpServletRequest) {
            // Generate a unique request ID
            val start = System.currentTimeMillis()
            // Continue the filter chain
            chain.doFilter(request, response)
            val duration = System.currentTimeMillis() - start

            logger.info("Request completed in {}ms", duration)
            return
        }

        chain.doFilter(request, response)

    }
}
