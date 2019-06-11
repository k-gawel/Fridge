package org.california.repository.item;

import org.california.model.entity.Allergen;
import org.california.repository.AbstractNamedEntityRepositoryImpl;
import org.springframework.stereotype.Repository;

@Repository
public class AllergenRepositoryImpl extends AbstractNamedEntityRepositoryImpl<Allergen> implements AllergenRepository {

    public AllergenRepositoryImpl() {
        setClazz(Allergen.class);
    }
}
