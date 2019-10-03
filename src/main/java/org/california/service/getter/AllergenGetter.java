package org.california.service.getter;

import org.california.model.entity.item.Allergen;
import org.california.repository.item.AllergenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AllergenGetter extends BaseNamedGetter<Allergen> {
    
    private final AllergenRepository allergenRepository;

    @Autowired
    AllergenGetter(AllergenRepository allergenRepository) {
        super(allergenRepository, Allergen.class);
        this.allergenRepository = allergenRepository;
    }


}
