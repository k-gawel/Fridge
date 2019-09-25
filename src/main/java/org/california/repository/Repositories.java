package org.california.repository;

import org.california.model.entity.BaseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class Repositories {

    public static HashMap<Class<? extends BaseEntity>, AbstractRepository<? extends BaseEntity>> BASE = new HashMap<>();

}
