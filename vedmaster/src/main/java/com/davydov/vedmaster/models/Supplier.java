package com.davydov.vedmaster.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Entity
@Table(name = "supplier")
@Data
public class Supplier {
    public static long sequence = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")

    private long id;
  @Column(name = "name")
    private String name;
  // private String email;
  @Column(name = "min_order_amount")
    private double minOrderAmount;
  @Column(name = "lead_time")
    private int leadTime;//in weeks
  @Column(name = "delivery_time")
    private int deliveryTime; //in weeks
  @Column(name = "order_period")
    private  int orderPeriod; // in weeks
  @Column(name = "delivery_cost_factor")
    private double deliveryCostFactor;
    String filePath;
  @OneToMany(cascade  = {CascadeType.PERSIST, CascadeType.DETACH,CascadeType.MERGE,
          CascadeType.REFRESH}
          ,mappedBy="supplier")
    List<Item> items;
    @Enumerated(EnumType.STRING)
    @Column(name="currency")
    public Currency currency;
    public Supplier(){

    }

    public Supplier(String name, double minOrderAmount, int leadTime, int deliveryTime, int orderPeriod, double deliveryCostFactor, Currency currency) {
        this.id =sequence++;
        this.name = name;
        this.minOrderAmount = minOrderAmount;
        this.leadTime = leadTime;
        this.deliveryTime = deliveryTime;
        this.orderPeriod = orderPeriod;
        this.deliveryCostFactor = deliveryCostFactor;
        this.currency = currency;
    }
}
