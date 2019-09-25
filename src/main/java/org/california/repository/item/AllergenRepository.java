package org.california.repository.item;

import org.california.model.entity.item.Allergen;
import org.california.repository.AbstractNamedEntityRepository;

import java.util.Optional;

public interface AllergenRepository extends AbstractNamedEntityRepository<Allergen> {

    Optional<Allergen> getByNameAndValue(String name, boolean contains);

}
