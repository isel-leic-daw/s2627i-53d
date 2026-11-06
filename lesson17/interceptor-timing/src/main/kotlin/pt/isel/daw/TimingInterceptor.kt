package pt.isel.daw

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView

@Component
class TimingInterceptor : HandlerInterceptor {

    private val logger = LoggerFactory.getLogger(TimingInterceptor::class.java)
    private val requestStartTimeKey = "requestStartTime"

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        request.setAttribute(requestStartTimeKey, System.currentTimeMillis())
        return true
    }

    override fun postHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        modelAndView: ModelAndView?
    ) {
        val startTime = request.getAttribute(requestStartTimeKey) as? Long ?: return
        val duration = System.currentTimeMillis() - startTime
        
        val method = request.method
        val uri = request.requestURI
        
        logger.info("Request completed: method={}, uri={}, status={}, duration={}ms", 
            method, uri, response.status, duration)
    }
}
