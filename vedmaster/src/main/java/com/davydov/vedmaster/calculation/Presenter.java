package com.davydov.vedmaster.calculation;



import com.davydov.vedmaster.models.Parameters;
import com.davydov.vedmaster.models.Supplier;
import com.davydov.vedmaster.repository.JpaItemRepository;
import com.davydov.vedmaster.repository.JpaOrderRepository;
import com.davydov.vedmaster.repository.JpaSupplierRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
@Data
public class Presenter {

    private final JpaSupplierRepository supplierRepository;
    private final JpaItemRepository itemRepository;
    private final JpaOrderRepository orderRepository;

    private List<Supplier> suppliers = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();

       public void ordersCalculation(){
           suppliers = supplierRepository.findAll();
        for (Supplier supplier : suppliers){
            Order order = new Order(supplier);
            order.orderCalculation();
            orders.add(order);
        }
    }
    public void saveOrders(){
        for (Order order : orders) {
            List<List<ItemOrder>> list = order.getOrders();
            saveOrder(list);
        }
       }
    public void saveOrder(List<List<ItemOrder>> list){

        for (List<ItemOrder> listIO : list) {
            OrderDAO orderDAO = new OrderDAO();
            orderDAO.setSupplierName(listIO.get(0).getItem().getSupplier().getName());
            orderDAO.setCurrency(listIO.get(0).getItem().getSupplier().getCurrency());
            orderDAO.setWeek(listIO.get(0).getWeek());
            List<String>itemArticles = new ArrayList<>();
            List <String> itemNames = new ArrayList<>();
            List<Double>itemQuantities = new ArrayList<>();
            List<Double>itemPurchasePrices = new ArrayList<>();
            List<Double>amounts = new ArrayList<>();
            double sum = 0;

             for (ItemOrder io : listIO) {

                itemNames.add(io.getItem().getName());
                itemArticles.add(io.getItem().getArticle());
                itemQuantities.add(io.getQuantity());
                double price = io.getItem().getPrice().getPurchasePrice();
                itemPurchasePrices.add(price);
                double amount = price * io.getQuantity();
                amounts.add(amount);
                sum +=amount;
            }
             orderDAO.setItemArticles(itemArticles);
             orderDAO.setItemNames(itemNames);
             orderDAO.setItemQuantities(itemQuantities);
             orderDAO.setItemPurchasePrices(itemPurchasePrices);
             orderDAO.setAmounts(amounts);
             orderDAO.setOrderTotalAmount(sum);

             orderRepository.save(orderDAO);
        }
    }

    public void printOrders() {
        for (Order order : orders) {
            List<List<ItemOrder>> list = order.getOrders();
            System.out.println("Кол-во заказов " + list.size());
             printOrder(list);
        }
        }

        public void printOrder(List<List<ItemOrder>> list){
        for (List<ItemOrder> listIO : list){
            for (ItemOrder io:listIO){
                System.out.println(io.getItem().getName() + " кол-во " + (int) io.getQuantity()+" неделя " + io.getWeek());
              List<Double> remains = io.getItem().getRemains().getRemainsPerWeek();

                for (int i = 0; i< Parameters.getWeeksNum(); i++){
                    System.out.println("неделя "+ i + " oст "+ remains.get(i));
                }
                System.out.println("********");
          }
        }
        }


    public void setSuppliers(List<Supplier> suppliers) {
        this.suppliers = suppliers;
    }
}
