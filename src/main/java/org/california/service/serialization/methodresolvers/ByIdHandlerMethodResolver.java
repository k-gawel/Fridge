package org.california.service.serialization.methodresolvers;

import org.california.model.entity.BaseEntity;
import org.california.service.serialization.annotations.ById;
import org.california.service.serialization.annotations.ByIdProcessor;
import org.california.service.serialization.annotations.ByIds;
import org.california.service.serialization.annotations.ByIdsProcessor;
import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Collection;

public class ByIdHandlerMethodResolver implements HandlerMethodArgumentResolver {

    private final Helper helper = new Helper();

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return BaseEntity.class.isAssignableFrom(methodParameter.getParameterType())
            && ( methodParameter.hasParameterAnnotation(ById.class) || methodParameter.hasParameterAnnotation(ByIds.class) );
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        return methodParameter.hasParameterAnnotation(ById.class)
                ? getEntity(methodParameter, nativeWebRequest)
                : getEntities(methodParameter, nativeWebRequest);
    }


    @Nullable
    private Collection<? extends BaseEntity> getEntities(MethodParameter parameter, NativeWebRequest webRequest) {
        Collection<Number> ids = getIds(parameter, webRequest);
        ByIdsProcessor processor = new ByIdsProcessor(ids, parameter.getParameter());
        return processor.getValues();
    }


    @Nullable
    private BaseEntity getEntity(MethodParameter parameter, NativeWebRequest webRequest) {
        Number id = getId(parameter, webRequest);
        ByIdProcessor processor = new ByIdProcessor(id, parameter.getParameter());
        return processor.getValue();
    }


    @Nullable
    private Collection<Number> getIds(MethodParameter parameter, NativeWebRequest webRequest) {
        String name = parameter.getParameterName();
        String stringId = webRequest.getParameter(name);
        return helper.toNumbersArray(stringId);
    }


    @Nullable
    private Number getId(MethodParameter parameter, NativeWebRequest webRequest) {
        String name = parameter.getParameterName();
        String idString = webRequest.getParameter(name);
        return helper.getNumber(idString);
    }


}
