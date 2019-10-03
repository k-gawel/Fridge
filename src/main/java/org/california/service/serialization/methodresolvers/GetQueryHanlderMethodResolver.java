package org.california.service.serialization.methodresolvers;

import org.california.model.transfer.request.queries.GetQuery;
import org.california.service.serialization.JSONMapper;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


@Component
public class GetQueryHanlderMethodResolver implements HandlerMethodArgumentResolver {

    private final Helper helper = new Helper();

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return GetQuery.class.isAssignableFrom(methodParameter.getParameterType());
    }

    @Override
    public GetQuery resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        String json = helper.parametersToJson(nativeWebRequest.getParameterMap());
        JSONMapper mapper = JSONMapper.MAPPER;
        return (GetQuery) mapper.readValue(json, methodParameter.getParameterType());
    }






}
