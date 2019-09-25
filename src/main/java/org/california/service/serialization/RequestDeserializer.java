package org.california.service.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.california.model.transfer.request.forms.Form;
import org.california.service.getter.BaseGetter;
import org.california.service.getter.GetterService;
import org.california.util.exceptions.NotValidException;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;


public class RequestDeserializer<T> extends StdDeserializer<T> {

    private ObjectMapper formMapper = JSONMapper.MAPPER;
    private ObjectMapper mapper = new ObjectMapper();

    private JsonNode node;
    private final Class<T> valueClass;
    private final ConstructorGetter<T> constructorGetter;


    public RequestDeserializer(Class<T> vc) {
        super(vc);
        this.valueClass = vc;
        this.constructorGetter = new ConstructorGetter(_valueClass);
    }


    @Override
    public T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        this.node = jsonParser.getCodec().readTree(jsonParser);

        Object[] parameters = getParametersArray();
        Constructor<T> constructor = constructorGetter.getConstructor();

        try {
            return constructor.newInstance(parameters);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException(e);
        }
    }


    private Object[] getParametersArray() {
        Field[] fields = valueClass.getDeclaredFields();
        Object[] result = new Object[fields.length];

        for (int i = 0; i < fields.length; i++) {
            Object value = getValue(fields[i]);
            result[i] = value;
        }

        return result;
    }


    private Object getValue(Field field) {
        BaseGetter getter = getGetter(field);
        String nodeName = getNodeName(field, getter != null);
        JsonNode value = this.node.get(nodeName);

        return getter == null ? getValue(value, field) : getValue(value, getter);
    }


    private Object getValue(JsonNode value, BaseGetter getter) {
        if (value == null) return null;

        long id = value.longValue();
        return getter.getByKey(id).orElse(null);
    }


    private Object getValue(JsonNode value, Field field) {
        if (value == null) return null;

        Class<?> fieldClass = field.getType();
        ObjectMapper mapper = Form.class.isAssignableFrom(fieldClass) ? formMapper : this.mapper;

        try {
            return mapper.readValue(value.toString(), fieldClass);
        } catch (IOException e) {
            throw new NotValidException("Cannot cast " + node.toString() + " to " + field.getType());
        }
    }


    private String getNodeName(Field field, boolean entity) {
        String name = field.getName().toLowerCase() + (entity ? "id" : "");
        var iterator = node.fieldNames();

        String fieldName = null;

        while (iterator.hasNext()) {
            String nodeName = iterator.next();
            if (nodeName.toLowerCase().equals(name))
                if (fieldName == null)
                    fieldName = nodeName;
                else
                    throw new IllegalStateException("Duplicate node name " + fieldName + " in JSON.");
        }

        return fieldName;
    }


    private BaseGetter<?> getGetter(Field field) {
        EntityById annotation = field.getAnnotation(EntityById.class);
        if (annotation == null) return null;

        Class<?> fieldType = field.getType();
        BaseGetter<?> getter = GetterService.GETTER.get(fieldType);
        if (getter == null)
            throw new IllegalStateException("There is no available getter for " + fieldType);

        return getter;
    }

}
