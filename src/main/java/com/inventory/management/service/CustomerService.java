package com.inventory.management.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inventory.management.entity.Event;
import com.inventory.management.entity.Order;
import com.inventory.management.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CustomerService {

    private static ObjectMapper mapper = new ObjectMapper();

    @Autowired
    CustomerRepository repository;

    public void syncCustomerOrder(Event event){

        Order order = null;
        try {
            order = mapper.readValue(event.getCommandObjStr(), Order.class);
            Optional<Order> orderRepo = repository.findById(event.getOrderId());
            if(orderRepo.isPresent()){
                orderRepo.get().setStatus(order.getStatus());
                repository.save(orderRepo.get());
            }else{
                repository.save(order);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Order> getOrderByUserName(String userName){
        List<Order> orders = repository.getOrderByUserName(userName);
        return orders;
    }

    public List<Order> getAllOrders(){
        List<Order> orders = repository.findAll();
        return orders;
    }

    /*
    public ResponseEntity<String> defaultFallback(Order order,Throwable t){

        return new ResponseEntity<>("Book service is down....." + "failed to process order for product id: " +
                order.getProductId(), HttpStatus.OK);
    }
    public ResponseEntity<String> rateLimitFallback(Order order,Throwable t){

        return new ResponseEntity<>("Too many attempts within a  time " + "failed to process order for product id: " +
                order.getProductId(), HttpStatus.OK);
    }

    public ResponseEntity<String> retryFallback(Order order,Throwable t){

        return new ResponseEntity<>("All retry exhausted for product id: " +
                order.getProductId(), HttpStatus.OK);
    }
    */
}