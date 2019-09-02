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

    /**
     * 过滤器的类型: pre route post error
     *  执行过程:
     *      正常流程: 请求到达zuul -> pre -> route -> 服务请求分发 -> post -> 响应浏览器
     *      普通异常时: error -> post -> 响应
     *      post异常时; error -> 响应
     *      error异常时: post -> 响应
     * @return
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 前置过滤器可能有多个，这个方法决定过滤器的优先级
     *  返回值越小，优先级越高
     * @return
     */
    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER -1;
    }

    /**
     * 是否执行该过滤器（run方法）
     * 返回true 执行run方法，否则不再执行run
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的业务逻辑 方法
     * 返回值为 null 表示该过滤器什么都不做
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        //1 获取request对象
        //1.1 初始化context上下文对象，由 zuul 网关提供的
        RequestContext currentContext = RequestContext.getCurrentContext();
        //1.2 获取request
        HttpServletRequest request = currentContext.getRequest();

        // 获取请求参数access-token
        String token = request.getParameter("access-token");

        // 判断是否存在
        if (StringUtils.isBlank(token)){
            // 不存在，未登录，拦截，不转发请求   设置是否转发请求
            currentContext.setSendZuulResponse(false);
            // 返回403    设置响应状态
            currentContext.setResponseStatusCode(HttpStatus.FORBIDDEN.value());
            // 设置相应提示 中文会乱码
            currentContext.setResponseBody("request error");
        }

        return null;
    }
}
