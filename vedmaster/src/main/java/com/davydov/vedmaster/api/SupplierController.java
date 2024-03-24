package com.davydov.vedmaster.api;


import com.davydov.vedmaster.models.Config;
import com.davydov.vedmaster.models.Supplier;
import com.davydov.vedmaster.service.ConfigService;
import com.davydov.vedmaster.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.io.FileNotFoundException;
import java.util.List;

@Controller
public class SupplierController {
    @Autowired
    SupplierService supplierService;
    @Autowired
    ConfigService configService;

    @GetMapping("/supplier")
    String setSupplierDetails(Model model) { //
        model.addAttribute("supplier", new SupplierRequest());
        return "supplier-set";
    }

    @GetMapping("/showSupplierDetails")
    String readDetails(@ModelAttribute("supplier") SupplierRequest supplier) {
        supplierService.addSupplier(supplier);

        return "supplier-view";
    }

    @GetMapping("/suppliers")
    public String suppliers(Model model) {
        List<Supplier> suppliers = supplierService.getSuppliers();
     /*   Supplier supplier = suppliers.getLast();
        List<Item> items = supplier.getItems();
        for (Item item:items){
            System.out.println(item.getName() + " " + item.getArticle() + " " + item.getSupplier().getName());
        }

      */
        model.addAttribute("suppliers", suppliers);
        return "suppliers";
    }

    @GetMapping("/filePath")
    public String filePath(Model model) {
        model.addAttribute("config", new Config());
        return "filePath";
    }

    @GetMapping("/showFilePath")
    String readDetails(@ModelAttribute("config") Config config) {

        try {
            configService.readFile(config);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
                   }
        return "second-view";
    }
}

