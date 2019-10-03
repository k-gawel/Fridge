package org.california.service.serialization.methodresolvers;

import org.california.model.entity.Account;
import org.california.service.serialization.annotations.ByToken;
import org.california.service.serialization.annotations.ByTokenProcessor;
import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class ByTokenHandlerMethodResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return Account.class.isAssignableFrom(methodParameter.getParameterType())
            && methodParameter.hasParameterAnnotation(ByToken.class);
    }

    @Override
    public Account resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        return getAccount(methodParameter, nativeWebRequest);
    }


    @Nullable
    private Account getAccount(MethodParameter parameter, NativeWebRequest webRequest) {
        String tokenString = getTokenFromHeader(webRequest);
        ByTokenProcessor processor = new ByTokenProcessor(tokenString, parameter.getParameter());
        return processor.getValue();
    }

    @Nullable
    private String getTokenFromHeader(NativeWebRequest webRequest) {
        return webRequest.getHeader("token");
    }

}
