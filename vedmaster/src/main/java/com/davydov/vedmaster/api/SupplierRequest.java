package com.davydov.vedmaster.api;
import com.davydov.vedmaster.models.Currency;
import com.davydov.vedmaster.models.Item;
import lombok.Data;

import java.util.List;
@Data
public class SupplierRequest {

    private String name;
    // private String email;
    private double minOrderAmount;
    private int leadTime;//in weeks
    private int deliveryTime; //in weeks
    private  int orderPeriod; // in weeks
    private double deliveryCostFactor;
    private Currency currency;

    public SupplierRequest(){

    }

}
