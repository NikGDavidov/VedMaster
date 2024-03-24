package com.davydov.vedmaster.api;

import com.davydov.vedmaster.models.Currency;
import lombok.Data;

@Data
public class ItemOrderView {
    long id;
    String supplierName;
    String itemName;
    String itemArticle;
    double itemQuantity;
   double itemPurchasePrice;
   double amount;
    int week;
   double orderTotalAmount;
   Currency currency;
}
