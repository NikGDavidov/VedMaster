package com.davydov.vedmaster.calculation;



import com.davydov.vedmaster.models.Item;
import com.davydov.vedmaster.models.Supplier;


import java.util.ArrayList;
import java.util.List;

import static com.davydov.vedmaster.models.Parameters.getWeeksNum;

public class Order {

    private List<ItemOrder> itemOrders = new ArrayList<>();

    private List<List<ItemOrder>> orders = new ArrayList<>();
    private Supplier supplier;

    public Order(Supplier supplier) {
        this.supplier = supplier;
    }

    public void orderCalculation() {
        List<Item> items = supplier.getItems();
        for (int i = 0; i < getWeeksNum(); i++) {
            double sum = 0;
            itemOrders = new ArrayList<>();
            for (Item item : items) {
                ItemOrder itemOrder = new ItemOrder(item, i);
                itemOrder.itemQuantityCalc();
                itemOrders.add(itemOrder);
                sum += itemOrder.getQuantity() * item.getPrice().getPurchasePrice();
            }
            if (sum >= supplier.getMinOrderAmount()) {
            //    System.out.println("Сумма заказа " + sum +" минимальная сумма заказа " + supplier.getMinOrderAmount());
             //   for (ItemOrder io :itemOrders){
              //      System.out.println(io.getItem().getName() + " " + io.getQuantity() + " шт");
          //      }
                prepareOrder();

            }
        }
    }

    /**
     * Метод prepareOrder() подготавливает заказ поставщику, который необходимо будет сделать в определенную неделю
     * и пересчитывает ожидаемые остатки по каждому товару поставщика после получения этого заказа)
     */
    public void prepareOrder() {
        orders.add(itemOrders);
        for (ItemOrder itemOrder : itemOrders){
            itemOrder.fillItemRemains(itemOrder.getWeekOfOrderReceiving());
        }
    }

    public List<ItemOrder> getItemOrders() {
        return itemOrders;
    }

    public List<List<ItemOrder>> getOrders() {
        return orders;
    }

    public Supplier getSupplier() {
        return supplier;
    }
}

