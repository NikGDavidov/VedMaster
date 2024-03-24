package com.davydov.vedmaster.service;

import com.davydov.vedmaster.api.SupplierRequest;
import com.davydov.vedmaster.models.Item;
import com.davydov.vedmaster.models.Price;
import com.davydov.vedmaster.models.Remains;
import com.davydov.vedmaster.models.Supplier;
import com.davydov.vedmaster.models.Parameters;
import com.davydov.vedmaster.repository.JpaItemRepository;
import com.davydov.vedmaster.repository.JpaSupplierRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Data
public class SupplierService {
    private final JpaSupplierRepository supplierRepository;
    private final JpaItemRepository itemRepository;

    public void addSupplier(SupplierRequest request){
        Supplier supplier = new Supplier(request.getName(),request.getMinOrderAmount(),request.getLeadTime(),
                request.getDeliveryTime(), request.getOrderPeriod(),request.getDeliveryCostFactor(), request.getCurrency());
        supplierRepository.save(supplier);
    }
    public List<Supplier> getSuppliers(){
        return supplierRepository.findAll();
    }
    public void addItems(List<ItemRequest> requestList){
        List<Supplier> suppliers = getSuppliers();
        Supplier supplier = suppliers.getLast();
        List<Item> items = new ArrayList<>();
        for (ItemRequest request:requestList){
            Item item = new Item();
            Price price = new Price();
            //      Remains remains = new Remains();

            price.setItem(item);
            item.setName(request.getName());
            item.setArticle(request.getArticle());
            item.setId(Item.sequence++);
            item.setSupplier(supplier);

            price.setPurchasePrice(request.getPriceRequest().getPurchasePrice());
            item.setPrice(price);

            item.setSalesPerWeek(request.getSalesPerWeek());
            Remains remains = new Remains(request.getRemainsRequest().getCurrentQty());
            remains.setItem(item);
            for (int i = 1; i< Parameters.getWeeksNum(); i++) {
                 double remain = remains.getRemainsPerWeek().get(i - 1) - item.getSalesPerWeek().get(i);
                if (remain < 0) remain = 0;
                remains.getRemainsPerWeek().set(i, remain);
            }
          item.setRemains(remains);
          items.add(item);
        }
        supplier.setItems(items);
        supplierRepository.save(supplier);

    }
}