package cn.zhangxd.oak.gateway.internal.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author zhangxd
 */
@Component
public class TokenRelayFilter extends ZuulFilter {

    @Override
    @SuppressWarnings("unchecked")
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        Set<String> headers = (Set<String>) ctx.get("ignoredHeaders");
        // JWT tokens should be relayed to the resource servers
        if (headers != null) {
            headers.remove("authorization");
        }
        return null;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 10000;
    }
}