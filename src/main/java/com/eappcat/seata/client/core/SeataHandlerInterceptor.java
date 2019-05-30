package com.eappcat.seata.client.core;

import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class SeataHandlerInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        String xid = RootContext.getXID();
        String rpcXid = httpServletRequest.getHeader(RootContext.KEY_XID);
        if (log.isDebugEnabled()) {
            log.debug("xid in RootContext {} xid in RpcContext {}", xid, rpcXid);
        }

        if (xid == null && rpcXid != null) {
            RootContext.bind(rpcXid);
            if (log.isDebugEnabled()) {
                log.debug("bind {} to RootContext", rpcXid);
            }
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception e) {

        String rpcXid = request.getHeader(RootContext.KEY_XID);

        if (StringUtils.isEmpty(rpcXid)) {
            return;
        }

        String unbindXid = RootContext.unbind();
        if (log.isDebugEnabled()) {
            log.debug("unbind {} from RootContext", unbindXid);
        }
        if (!rpcXid.equalsIgnoreCase(unbindXid)) {
            log.warn("xid in change during RPC from {} to {}", rpcXid, unbindXid);
            if (unbindXid != null) {
                RootContext.bind(unbindXid);
                log.warn("bind {} back to RootContext", unbindXid);
            }
        }
    }
}
