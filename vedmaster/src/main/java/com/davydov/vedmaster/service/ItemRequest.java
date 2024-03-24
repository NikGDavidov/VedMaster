package com.davydov.vedmaster.service;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ItemRequest {
    private String name;
    private String article;
    private PriceRequest priceRequest;
    private List<Double> salesPerWeek = new ArrayList<>(52);
    private RemainsRequest remainsRequest;

}