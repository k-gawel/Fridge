package org.california.repository.item;

import org.california.model.entity.item.Item;
import org.california.repository.AbstractNamedEntityRepositoryImpl;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepositoryImpl extends AbstractNamedEntityRepositoryImpl<Item> implements ItemRepository {

    public ItemRepositoryImpl() { setClazz(Item.class); }
}

