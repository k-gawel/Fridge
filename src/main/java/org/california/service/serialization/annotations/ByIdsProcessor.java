package org.california.service.serialization.annotations;

import org.california.model.entity.BaseEntity;
import org.california.service.getter.BaseGetter;
import org.california.service.getter.GetterService;
import org.california.util.exceptions.NoContentException;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.*;

public class ByIdsProcessor {

    private ByIds annotation;
    private Class<? extends Collection> collectionType;
    private Class<? extends BaseEntity> entityType;
    private Collection<Number> ids;

    public ByIdsProcessor(@Nullable Collection<Number> ids, @NotNull Field field) {
        if(!field.isAnnotationPresent(ByIds.class))
            throw new IllegalArgumentException("Field must be annotated with @ByIds.");
        if(!Collection.class.isAssignableFrom(field.getType()))
            throw new IllegalArgumentException("Field must be type of Collection.");

        this.ids   = ids;
        this.annotation     = field.getAnnotation(ByIds.class);
        this.entityType     = annotation.entity();
        this.collectionType = getCollectionType((Class<? extends Collection>) field.getType());
    }


    public ByIdsProcessor(@Nullable Collection<Number> ids, @NotNull Parameter parameter) {
        if(!parameter.isAnnotationPresent(ByIds.class))
            throw new IllegalArgumentException("Parameter must be annotated with @ByIds.");
        if(!Collection.class.isAssignableFrom(parameter.getType()))
            throw new IllegalArgumentException("Parameter must be type of Collection.");

        this.ids   = ids;
        this.annotation     = parameter.getAnnotation(ByIds.class);
        this.entityType     = annotation.entity();
        this.collectionType = getCollectionType((Class<? extends Collection>) parameter.getType());
    }


    public Collection<? extends BaseEntity> getValues() {
        if(ids == null) return null;

        BaseGetter<? extends BaseEntity> getter = getGetter();
        Collection result = createCollection();

        for(Number id : ids) {
            Optional<? extends BaseEntity> e = getter.getByKey(id);
            if(e.isEmpty() && annotation.failOnNull())
                throw new NoContentException(entityType, id);
            else
                e.ifPresent(result::add);
        }

        return result;
    }


    private BaseGetter<? extends BaseEntity> getGetter() {
        return GetterService.GETTER.get(entityType);
    }


    private Collection createCollection() {
        try {
            return collectionType.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new IllegalStateException("Cannot create Collection ", e);
        }
    }


    private Class<? extends Collection> getCollectionType(Class<? extends Collection> fieldType) {
        if(!Modifier.isAbstract(fieldType.getModifiers()) && !fieldType.isInterface())
            return fieldType;
        else if(SortedSet.class.isAssignableFrom(fieldType))
            return TreeSet.class;
        else if(Set.class.isAssignableFrom(fieldType))
            return HashSet.class;
        else if(List.class.isAssignableFrom(fieldType))
            return ArrayList.class;
        else if(Deque.class.isAssignableFrom(fieldType))
            return LinkedList.class;
        else if(Queue.class.isAssignableFrom(fieldType))
            return PriorityQueue.class;
        else
            return ArrayList.class;

    }



}
