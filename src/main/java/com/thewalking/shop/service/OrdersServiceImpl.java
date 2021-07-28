package com.thewalking.shop.service;

import com.thewalking.shop.entity.Customer;
import com.thewalking.shop.entity.OrderItems;
import com.thewalking.shop.entity.Orders;
import com.thewalking.shop.repository.OrdersRepository;
import com.thewalking.shop.utilityservices.ShippingAndHandlingCost;
import com.thewalking.shop.utilityservices.TaxRates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrdersServiceImpl implements OrdersService{

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    OrderItemsService orderItemsService;

    @Autowired
    CustomerService customerService;

    @Override
    public Orders save(Orders orders) {
        return ordersRepository.save(orders);
    }

    @Transactional
    @Override
    public Orders checkoutAllItemsInCart(Customer customer) {
        return ordersRepository.save(processOrderRequestForCustomer(customer));
    }

    @Override
    public List<Orders> findAll() {
        List<Orders> list = new ArrayList<>();
        ordersRepository.findAll().forEach(each -> list.add(each));
        return list;
    }

    @Override
    public Optional<Orders> findById(Long id) {
        return ordersRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        ordersRepository.deleteById(id);
    }

    @Override
    public List<Orders> findAllByOrderDate(LocalDate date) {
        return ordersRepository.findAllByTimestampIsAfterAndTimestampIsBefore(date.atTime(0,0,1),date.atTime(23,59,59));
    }

    @Transactional(value = Transactional.TxType.REQUIRED)
    private Orders processOrderRequestForCustomer(Customer customer){
        customer = customerService.findById(customer.getId()).get();

        //Open an empty order first to get the order id
        Orders orders = save(new Orders());

        List<OrderItems> orderItemsList = orderItemsService.findAllByCustomerIdAndOrderIsNull(customer.getId());
        if(orderItemsList.size()==0) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No item Found in the Cart!");

        orderItemsList.forEach(each -> {
            each.setOrder(orders); //set order id for order items
            each.setPaidSubtotal(each.getQuantity()*each.getStock().getPrice()); // set subtotal for each item
        });
        double subTotal =orderItemsList.stream().mapToDouble(each -> each.getPaidSubtotal()).sum();
        double shippingHandling = ShippingAndHandlingCost.province(customer.getAddress().getProvince());
        double estimatedTaxToBeCollected = TaxRates.province(customer.getAddress().getProvince()) * subTotal;
        double grandTotal = subTotal +estimatedTaxToBeCollected + shippingHandling;
        orders.setCustomer(customer);
        orders.setGrandTotal(grandTotal);
        orders.setShippingHandling(shippingHandling);
        orders.setEstimatedTaxToBeCollected(estimatedTaxToBeCollected);
        orders.setOrderItems(orderItemsList);
        orders.setStatus("Order Placed!");
        orders.setTotalBeforeTax(subTotal);
        return orders;
    }
}
