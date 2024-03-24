package com.davydov.vedmaster.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Currency;
@Entity
@Table (name = "price")
@Data
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private long id;
    @Column (name = "purchase_price")
    private double purchasePrice;
    @Column(name = "whosale_price")
    private double whosalePrice;
    @Column(name = "retail_price")
    private double retailPrice;
    @OneToOne(mappedBy="price",
            cascade = {CascadeType.PERSIST, CascadeType.DETACH,CascadeType.MERGE,
                    CascadeType.REFRESH})
    private Item item;
    // private Currency currency;




}