package com.davydov.vedmaster.calculation;


import com.davydov.vedmaster.models.Parameters;
import com.davydov.vedmaster.models.Item;
import com.davydov.vedmaster.models.Remains;

import java.util.ArrayList;
import java.util.List;

import static com.davydov.vedmaster.models.Parameters.getExpectedSalesGrowthRatio;
import static com.davydov.vedmaster.models.Parameters.getServiceRatio;


/**
 * Класс ItemOrder для расчета заказа по каждой единице товара поставщика
 */
public class ItemOrder {
    private Item item;
    private int week;

    private int weekOfOrderReceiving;
    private double quantity;
    public ItemOrder(Item item,int week) {
        this.item = item;
        this.week = week;
        //weekOfOrderReceiving - номер недели,на которой ожидается поступление товара, если заказ разместить в неделю week
        weekOfOrderReceiving = week + item.getSupplier().getLeadTime() + item.getSupplier().getDeliveryTime();
    }
    /**
     itemQuantityCalc() расчитывает необходимое количество товара, которое необходимо закупить
     на период между заказами
     */
    public void itemQuantityCalc() {
        //weekOfOrderProvision - номер недели, до которой нам нужно обеспечить склад данным товаром
        int weekOfOrderProvision = weekOfOrderReceiving + item.getSupplier().getOrderPeriod();
        //salesOrderProvision - ожидаемое количество товара, которое будет продано за период между заказами.
        double salesOrderProvision = 0;
        for (int i = weekOfOrderReceiving; i < weekOfOrderProvision; i++) {
            salesOrderProvision += item.getSalesPerWeek().get(i);
        }
        salesOrderProvision *=getServiceRatio()*getExpectedSalesGrowthRatio();
        quantity =  salesOrderProvision-expectedRemainsCalc();
        if (quantity<0) quantity = 0;
    }

    /**
     * метод expectedRemainsCalc расчитывает ожидаемый остаток товара на ожидаемую неделю поступления товара weekOfOrderReceiving на склад
     */
    public double expectedRemainsCalc() {
    return item.getRemains().getRemainsPerWeek().get(weekOfOrderReceiving);
    }
    public void fillItemRemains(int weekNum) {
        Remains remains = item.getRemains();
        List <Double> remainsPerWeek = remains.getRemainsPerWeek();
        List <Double> sales = item.getSalesPerWeek();

        double remain = remainsPerWeek.get(weekNum);
        remainsPerWeek.set(weekNum, remain + quantity);

        for (int i = weekNum+1; i<remainsPerWeek.size(); i++){
            double salesThisWeek = sales.get(i)* getServiceRatio()*getExpectedSalesGrowthRatio();
            double remainsThisWeek = remainsPerWeek.get(i-1) - salesThisWeek;
            if (remainsThisWeek<0) remainsThisWeek = 0; //остаток товара не может быть отрицательным
            remainsPerWeek.set(i,remainsThisWeek);
        }
        remains.setRemainsPerWeek(remainsPerWeek);
       // System.out.println("метод fillremains // неделя" + weekNum + " " + week);
       // System.out.println(item.getRemains().getRemainsPerWeek());
    }


    public double getQuantity(){
        return quantity;
    }

    public Item getItem() {
        return item;
    }

    public int getWeek() {
        return week;
    }

    public int getWeekOfOrderReceiving() {
        return weekOfOrderReceiving;
    }
}
