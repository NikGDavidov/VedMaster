package com.davydov.vedmaster.api;

import com.davydov.vedmaster.models.Item;

import com.davydov.vedmaster.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ItemController {
    //C:\Sam\Sample2.xlsx
    @Autowired
    ItemService itemService;
    @GetMapping("/items")
        public String showItems(Model model){
        List<Item> items = itemService.getItems();
        model.addAttribute("items",items);
        return "items";
        }

    @GetMapping("/items/{id}")
    public String  showItemDetails(@PathVariable long id){
       Item item = itemService.getById(id);
    /*    System.out.println(item.getName());
        System.out.println();
        for (int i = 0; i<60; i++) {
            System.out.print(item.getSalesPerWeek().get(i) + " ");
             }
        System.out.println();
        for (int i = 0; i<60; i++) {
            System.out.print(item.getRemains().getRemainsPerWeek().get(i) + " ");
        }

     */
        return "zaglushka";
    }

}
