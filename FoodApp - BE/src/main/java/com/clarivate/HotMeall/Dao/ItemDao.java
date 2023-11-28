package com.clarivate.HotMeall.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.clarivate.HotMeall.DTO.Item;
import com.clarivate.HotMeall.Repository.ItemRepository;

@Repository
public class ItemDao {
	
	@Autowired
	ItemRepository itemRepository;
	
	// 1. Add Item
	public Item addItem(Item item) {
		return itemRepository.save(item);
	}
	
	// 2. Get Item by ID
	public Optional<Item> getItemById(int id) {
		return itemRepository.findById(id);
	}

	// 3. Edit Item
	public Item editItem(Item item) {
		itemRepository.save(item);
		return item;
	}
	
	// 4. Read all items of a order
	public List<Item> getItemsByOrderId(int orderId) {
		return itemRepository.getItemsInOrder(orderId);
	}

}
