package com.stc.fileservice.service;

import com.stc.fileservice.model.Item;
import com.stc.fileservice.model.enums.ItemType;
import com.stc.fileservice.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;
    public Item createItemFromName(String name, ItemType type ){
        Item item = new Item();
        item.setName(name);
        item.setType(type);
        return item;
    }

    public Item getItem(String name , ItemType type , Long parentId){
        return itemRepository.getItemByNameAndTypeAndParentItem(name,type,parentId);
    }
    public Item getItemById(Long id){
        return itemRepository.getItemById(id);
    }
    public Boolean checkIfItemExist(String name , ItemType type , Item parent){
        return  itemRepository.existsItemByNameAndTypeAndParentItem(name,type,parent);
    }
//    public Boolean checkIfItemExist(String name , ItemType type , String parentName){
//        return  itemRepository.existsItemByNameAndTypeAndParentItem(name,type,parentId);
//    }
    public Item saveItem(Item item ){
        return itemRepository.save(item);
    }
}
