package com.davydov.vedmaster.api;

import com.davydov.vedmaster.calculation.Presenter;
import com.davydov.vedmaster.service.ConfigService;
import com.davydov.vedmaster.models.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;





@Controller
public class CalculationController {
   @Autowired
    ConfigService configService;
    @Autowired
    Presenter presenter;
   @GetMapping("/calc")
    String askPath( Model model){
       presenter.ordersCalculation();
       //presenter.printOrders();
       presenter.saveOrders();

      //  model.addAttribute("config", new Config());
        return "zaglushka";
    }
    @GetMapping("/showConfigDetails")
    String showConfigDetails(@ModelAttribute("config") Config config){
/*
        try {
            configService.showConfig(config);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

 */
        return "second-view";
    }

}
