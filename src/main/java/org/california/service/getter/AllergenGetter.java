package org.california.service.getter;

import org.california.model.entity.item.Allergen;
import org.california.repository.item.AllergenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;

@Service
public class AllergenGetter {
    
    private final AllergenRepository allergenRepository;

    @Autowired
    AllergenGetter(AllergenRepository allergenRepository) {
        this.allergenRepository = allergenRepository;
    }

    
    public Allergen getByKey(Serializable key) {
        return allergenRepository.getByKey(key);
    }


    public Collection<Allergen> getByIds(Collection<Long> ids) {
        return allergenRepository.getByKeys(ids);
    }

    
    public Allergen getByName(String name) {
        return allergenRepository.getByName(name);
    }


    public Collection<Allergen> searchByName(String name) {
        return allergenRepository.searchByName(name);
    }


    public Collection<Allergen> getWhereNameStartsWith(String nameStart) {
        return allergenRepository.getWhereNameStartsWith(nameStart);
    }

}
