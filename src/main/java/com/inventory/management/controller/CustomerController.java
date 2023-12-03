package com.inventory.management.controller;

import com.inventory.management.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping("/api/customers")
public class CustomerController {


    @GetMapping(value = "/getAllOrders/{userName}",
            produces =APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Order>> getOrderHistory(@PathVariable String userName){
        return ResponseEntity.ok(null);
      }
}
