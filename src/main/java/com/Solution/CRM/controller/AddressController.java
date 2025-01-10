package com.Solution.CRM.controller;

import com.Solution.CRM.dto.*;
import com.Solution.CRM.service.AddressService;
import com.Solution.CRM.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    CustomerService customerService;
    @Autowired
    AddressService addressService;

    @ResponseBody
    @PostMapping("/v1/crete-address")
    public  ResponseEntity<BaseResponse> createAddress(@RequestBody AddressCreateRequest request) {
        return ResponseEntity.ok(addressService.createAddress(request));

    }
    @ResponseBody
    @PostMapping("/v1/edit-address")
    public  ResponseEntity<BaseResponse> editAddress(@RequestBody AddressCreateRequest request) {
        return ResponseEntity.ok(addressService.editAddress(request));
    }
    @ResponseBody
    @PostMapping("/v1/delete-address")
    public  ResponseEntity<BaseResponse> deleteAddress(@RequestBody AddressDeleteRequest request) {
        return ResponseEntity.ok(addressService.deleteAddress(request));
    }
    @ResponseBody
    @PostMapping("/v1/view-all-address/{customerId}")
    public  ResponseEntity<BaseResponse> viewAddress(@PathVariable String customerId) {
        return ResponseEntity.ok(addressService.viewAddress(customerId));
    }
}