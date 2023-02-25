package com.stc.fileservice.repository;

import com.stc.fileservice.model.Item;
import com.stc.fileservice.model.enums.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item,String> {

    Boolean existsItemByNameAndTypeAndParentItem(String name, ItemType type, Item parent);
    Item getItemByNameAndTypeAndParentItem(String name, ItemType type, Long id);
    Item getItemById(Long id);
}
