package com.thewalking.shop.contoller;

import com.thewalking.shop.entity.Customer;
import com.thewalking.shop.entity.OrderItems;
import com.thewalking.shop.entity.Orders;
import com.thewalking.shop.exception.ErrorMessages;
import com.thewalking.shop.exception.UserException;
import com.thewalking.shop.service.CustomerService;
import com.thewalking.shop.service.OrderItemsService;
import com.thewalking.shop.service.OrdersService;
import com.thewalking.shop.utilityservices.ShippingAndHandlingCost;
import com.thewalking.shop.utilityservices.TaxRates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/orders")
@RestController
public class OrdersController {

    @Autowired
    OrdersService ordersService;

    @Autowired
    OrderItemsService orderItemsService;

    @Autowired
    CustomerService customerService;

    @Transactional
    @RequestMapping(value="", method= RequestMethod.POST)
    public ResponseEntity<Orders> save2(@RequestBody Customer customer){

        /*
        //This piece is just to help targeting more customers
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(email);
        customer = customerService.findByEmail(email).orElseThrow(
                new UserException(ErrorMessages.NO_RECORD_FOUND.name())
        );
        */

        customer = customerService.findById(customer.getId()).get();

        //Open an empty order first to get the order id
        Orders orders = ordersService.save(new Orders());



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
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(ordersService.save(orders));
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.PAYMENT_REQUIRED, "Transaction FAILED",e);
        }

    }

    @RequestMapping(value="", method= RequestMethod.GET)
    public List<Orders> findAll(){
        return ordersService.findAll();
    }
}
