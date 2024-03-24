package com.davydov.vedmaster.calculation;


import com.davydov.vedmaster.models.Currency;
import com.davydov.vedmaster.models.Item;
import com.davydov.vedmaster.service.ItemRequest;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "zakazy")
@Data
public class OrderDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private long id;
    @Column(name ="supplier_name")
    String supplierName;
    @ElementCollection
    List <String> itemNames;
    @ElementCollection
    List<String>itemArticles;
    @ElementCollection
    List<Double>itemQuantities;
    @ElementCollection
    List<Double>itemPurchasePrices;
    @ElementCollection
    List<Double>amounts;
    @Column (name = "week")
    int week;
    @Column(name = "total_amount")
    double orderTotalAmount;
    @Enumerated(EnumType.STRING)
    @Column(name="currency")
    Currency currency;
}
