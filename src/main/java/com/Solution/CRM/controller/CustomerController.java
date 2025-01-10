package com.Solution.CRM.controller;

import com.Solution.CRM.dto.BaseResponse;
import com.Solution.CRM.dto.CustomerCreateRequest;
import com.Solution.CRM.dto.CustomerDeleteRequest;
import com.Solution.CRM.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @ResponseBody
    @PostMapping("/v1/crete-customer")
    public  ResponseEntity<BaseResponse> createCustomer(@RequestBody CustomerCreateRequest request) {
        return ResponseEntity.ok(customerService.createCustomer(request));

    }
    @ResponseBody
    @PostMapping("/v1/edit-customer")
    public  ResponseEntity<BaseResponse> editCustomer(@RequestBody CustomerCreateRequest request) {
        return ResponseEntity.ok(customerService.editCustomer(request));
    }

    @ResponseBody
    @PostMapping("/v1/delete-customer")
    public  ResponseEntity<BaseResponse> deleteCustomer(@RequestBody CustomerDeleteRequest request) {
        return ResponseEntity.ok(customerService.deleteCustomer(request));
    }

    @ResponseBody
    @PostMapping("/v1/view-customer/{customerId}")
    public  ResponseEntity<BaseResponse> viewCustomer(@PathVariable String customerId) {
        return ResponseEntity.ok(customerService.viewCustomer(customerId));
    }
}
