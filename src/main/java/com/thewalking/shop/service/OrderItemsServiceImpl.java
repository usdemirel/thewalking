package com.thewalking.shop.service;

import com.thewalking.shop.entity.OrderItems;
import com.thewalking.shop.repository.OrderItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderItemsServiceImpl implements OrderItemsService{

    @Autowired
    OrderItemsRepository orderItemsRepository;

    @Override
    public OrderItems save(OrderItems orderItems) {
        return orderItemsRepository.save(orderItems);
    }

    @Override
    public List<OrderItems> findAll() {
        List<OrderItems> list = new ArrayList<>();
        orderItemsRepository.findAll().iterator().forEachRemaining(item -> list.add(item));
        return list;
    }

    @Override
    public Optional<OrderItems> findById(Long id) {
        return orderItemsRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        orderItemsRepository.deleteById(id);
    }

    /**
     *
     * @param customerId
     * @return items in the customer's cart.
     */
    @Override
    public List<OrderItems> findAllByCustomerIdAndOrderIsNull(Long customerId) {
        List<OrderItems> list = new ArrayList<>();
        orderItemsRepository.findAllByCustomerIdAndOrderIsNull(customerId)
        .forEach(each -> list.add(each));
        return list;
    }
}
