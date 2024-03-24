package com.davydov.vedmaster.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "remains")
@Data
public class Remains {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ElementCollection
    private List<Double> remainsPerWeek = new ArrayList<>(52);
    /**
     * currentQty - текущие остатки товара (item)
     *
     * @return
     */
    @Column(name = "currentQty")
    private double currentQty;
    @OneToOne(mappedBy = "remains",
            cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.REFRESH})
    private Item item;

    public Remains() {}

    public Remains(double currentQty) {
        this.currentQty = currentQty;
        for (int i = 0; i < Parameters.getWeeksNum() * 2; i++) remainsPerWeek.add(0.0);
        remainsPerWeek.set(0, currentQty);
    }


}
