package org.california.service.getter;

import org.california.model.entity.item.Allergen;
import org.california.repository.item.AllergenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class AllergenGetter extends BaseGetter<Allergen> {
    
    private final AllergenRepository allergenRepository;

    @Autowired
    AllergenGetter(AllergenRepository allergenRepository) {
        super(allergenRepository, Allergen.class);
        this.allergenRepository = allergenRepository;
    }


    public Optional<Allergen> getByName(String name) {
        return allergenRepository.getByName(name);
    }


    public Collection<Allergen> searchByName(String name) {
        return allergenRepository.searchByName(name);
    }


    public Collection<Allergen> getWhereNameStartsWith(String nameStart) {
        return allergenRepository.getWhereNameStartsWith(nameStart);
    }

}
