package cn.imust.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class LoginFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER -1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        // 获取request对象
        RequestContext currentContext = RequestContext.getCurrentContext();     // 获取请求上下文
        HttpServletRequest request = currentContext.getRequest();       // 获取request

        // 获取请求参数access-token
        String token = request.getParameter("access-token");

        // 判断是否存在
        if (StringUtils.isBlank(token)){
            // 不存在，未登录，拦截
            currentContext.setSendZuulResponse(false);
            // 返回403
            currentContext.setResponseStatusCode(HttpStatus.FORBIDDEN.value());
        }

        return null;
    }
}
