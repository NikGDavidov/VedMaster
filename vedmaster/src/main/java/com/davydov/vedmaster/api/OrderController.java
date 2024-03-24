package com.davydov.vedmaster.api;

import com.davydov.vedmaster.calculation.OrderDAO;

import com.davydov.vedmaster.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import com.davydov.vedmaster.models.Currency;

@Controller
public class OrderController {
    @Autowired
    OrderService orderService;
    @GetMapping("/orders")
    public String showOrders(Model model){
        List<OrderDAO> orders = orderService.getOrders();
        model.addAttribute("orders",orders);
        return "orders";
    }

    @GetMapping("/orders/{id}")
    public String  showItemDetails(@PathVariable long id, Model model){
        OrderDAO order = orderService.getById(id);

        long di = order.getId();
        String supplierName = order.getSupplierName();
        List<String> articles = order.getItemArticles();
        List<String> itemNames = order.getItemNames();
        List<Double> itemQuantities = order.getItemQuantities();
        List <Double> itemPurchasePrices = order.getItemPurchasePrices();
        List<Double> amounts = order.getAmounts();
        int week = order.getWeek();
        double orderTotalAmount = order.getOrderTotalAmount();

        List<ItemOrderView> itemOrderViews = new ArrayList<>();

        for (int i = 0; i<articles.size();i ++){

            ItemOrderView itemOrderView = new ItemOrderView();
            itemOrderView.setId(di);
            itemOrderView.setSupplierName(supplierName);
            itemOrderView.setItemArticle(articles.get(i));
            itemOrderView.setItemName(itemNames.get(i));
            itemOrderView.setItemQuantity(itemQuantities.get(i));
            itemOrderView.setItemPurchasePrice(itemPurchasePrices.get(i));
            itemOrderView.setAmount(amounts.get(i));
            itemOrderView.setWeek(week);
            itemOrderView.setOrderTotalAmount(orderTotalAmount);
            itemOrderView.setCurrency(order.getCurrency());

            itemOrderViews.add(itemOrderView);
        }
        model.addAttribute("itemOrderViews", itemOrderViews);
        return "order";
    }
    @GetMapping("/orders/sum")
    public String showSums(Model model){
        List<OrderDAO> orders = orderService.getOrders();
        OrdersAmountView ordersAmountView = new OrdersAmountView();
        for (OrderDAO order:orders){
            switch (order.getCurrency()) {
                case EUR :
                    ordersAmountView.euroAmount += order.getOrderTotalAmount();
                    break;
                case USD :
                    ordersAmountView.usdAmount += order.getOrderTotalAmount();
                    break;
                case RMB:
                    ordersAmountView.rmbAmount += order.getOrderTotalAmount();
                    break;
                case RUB:
                    ordersAmountView.rubAmount += order.getOrderTotalAmount();
                    break;
            }

        }


        model.addAttribute("ordersAmountView", ordersAmountView);
        return "sums";
    }


}
