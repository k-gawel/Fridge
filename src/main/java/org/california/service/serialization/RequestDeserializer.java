package org.california.service.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.california.model.transfer.request.forms.Form;
import org.california.util.exceptions.NotValidException;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;


public class RequestDeserializer<T> extends StdDeserializer<T> {

    private ObjectMapper formMapper = JSONMapper.MAPPER;
    private ObjectMapper mapper     = new ObjectMapper();

    private Class<T> valueClass;


    public RequestDeserializer(Class<T> vc) {
        super(vc);
        this.valueClass = vc;
    }


    @Override
    public T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        Object[] parameters = getParametersArray(node);
        Constructor<T> constructor = new ConstructorGetter(_valueClass).getConstructor();

        try {
            return constructor.newInstance(parameters);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException(e);
        }
    }


    private Object[] getParametersArray(JsonNode node) {
        Field[] fields = valueClass.getDeclaredFields();
        Object[] result = new Object[fields.length];

        for (int i = 0; i < fields.length; i++) {
            Object value = getValue(node, fields[i]);
            result[i] = value;
        }

        return result;
    }


    private Object getValue(JsonNode node, Field field) {
        String nodeName = getNodeName(field);
        JsonNode value = node.get(nodeName);

        if(field.isAnnotationPresent(ById.class))
            return new ByIdProcessor(value, field).getValue();
        else if(field.isAnnotationPresent(ByIds.class))
            return new ByIdsProcessor(value, field).getValues();
        else
            return getPrimitiveValue(value, field);
    }


    private Object getPrimitiveValue(JsonNode value, Field field) {
        if (value == null) return null;

        Class<?> fieldClass = field.getType();
        ObjectMapper mapper = Form.class.isAssignableFrom(fieldClass) ? formMapper : this.mapper;

        try {
            return mapper.readValue(value.toString(), fieldClass);
        } catch (IOException e) {
            throw new NotValidException("Cannot cast " + value.toString() + " to " + field.getType());
        }
    }


    private String getNodeName(Field field) {
        String fieldName = field.getName();

        if(field.isAnnotationPresent(ById.class))
            return fieldName + "Id";
        else if(field.isAnnotationPresent(ByIds.class))
            return fieldName + "Ids";
        else
            return fieldName;
    }


}
