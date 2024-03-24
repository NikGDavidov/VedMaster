package com.davydov.vedmaster.models;



import jakarta.persistence.*;
import lombok.Data;


import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "item")
@Data
public class Item {
    public static long sequence = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private long id;
    @Column (name = "name")
    private String name;
    @Column (name = "article")
    private String article;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH,CascadeType.MERGE,
            CascadeType.REFRESH})
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
    @ElementCollection
    private List<Double> salesPerWeek = new ArrayList<>(52);
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH,CascadeType.MERGE,
            CascadeType.REFRESH})
    @JoinColumn(name = "price_id")
    private Price price;




    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH,CascadeType.MERGE,
            CascadeType.REFRESH})
    @JoinColumn(name = "remains_id")
   private Remains remains;



    // PackageInfo packageInfo;
    //    Sales sales;


}
