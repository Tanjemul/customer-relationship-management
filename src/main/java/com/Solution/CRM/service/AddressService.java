package com.Solution.CRM.service;

import com.Solution.CRM.dto.*;
import com.Solution.CRM.enumeration.CommonConstant;
import com.Solution.CRM.model.Address;
import com.Solution.CRM.model.Customer;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddressService {
    @Autowired
    EntityFinderService entityFinderService;
    @Autowired
    EntityFinder entityFinder;
    @Autowired
    Gson gson;

    public BaseResponse createAddress(AddressCreateRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            BigInteger customerId = new BigInteger(request.getCustomerId());
            Customer customer = entityFinderService.customerRepository.findById(customerId).orElseThrow();
            if(null != customer.getEmail()) {
                Address address = new Address();
                address.setAddressLine1(request.getAddressLine1());
                address.setAddressLine2(request.getAddressLine2());
                address.setCity(request.getCity());
                address.setState(request.getState());
                address.setZipCode(request.getZipCode());
                address.setSpecialMark(request.getSpecialMark());
                address.setCreatedTime(LocalDateTime.now());
                address.setCustomer(customer);

                entityFinderService.addressRepository.save(address);
                baseResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
                baseResponse.setResponseMessage(CommonConstant.ADD_CREATED_MESSAGE +", " + "Address Id: " + address.getId() );

            } else {
                baseResponse.setResponseCode(CommonConstant.BAD_REQUEST_CODE);
                baseResponse.setResponseMessage(CommonConstant.CUS_EXISTS_MESSAGE );
                return baseResponse;
            }
            return baseResponse;
        } catch (Exception e) {
            baseResponse.setResponseCode(CommonConstant.UNPROSS_ENTITY_CODE);
            baseResponse.setResponseMessage(CommonConstant.CUS_CREATE_UP_ERROR_MESSAGE );
            baseResponse.setResponseMessage(CommonConstant.CUS_UP_ERROR_MESSAGE);

            return baseResponse;
        }
    }

    public BaseResponse editAddress(AddressCreateRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            BigInteger addressId = new BigInteger(request.getAddressId());
            Address address = entityFinderService.addressRepository.findById(addressId).orElse(new Address());

            if(null != address.getAddressLine1()) {

                if(null != request.getAddressLine1()) {
                    address.setAddressLine1(request.getAddressLine1());
                }
                if(null != request.getAddressLine2()) {
                    address.setAddressLine2(request.getAddressLine2());
                }
                if(null != request.getCity()) {
                    address.setCity(request.getCity());
                }
                if (null != request.getState()) {
                    address.setState(request.getState());
                }
                if (null != request.getZipCode()) {
                    address.setZipCode(request.getZipCode());
                }
                if (null!= request.getSpecialMark()) {
                    address.setSpecialMark(request.getSpecialMark());
                }

                address.setLastUpdatedTime(LocalDateTime.now());
                entityFinderService.addressRepository.save(address);
                baseResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
                baseResponse.setResponseMessage(CommonConstant.ADD_EDIT_MESSAGE);

            } else {
                baseResponse.setResponseCode(CommonConstant.NOT_FOUND_CODE);
                baseResponse.setResponseMessage(CommonConstant.CUS_NOT_FOUND_MESSAGE );
            }
            return baseResponse;
        } catch (Exception e) {
            baseResponse.setResponseCode(CommonConstant.UNPROSS_ENTITY_CODE);
            baseResponse.setResponseMessage(CommonConstant.CUS_UP_ERROR_MESSAGE);
            return baseResponse;
        }
    }

    public BaseResponse deleteAddress(AddressDeleteRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            BigInteger addressId = new BigInteger(request.getAddressId());
            Address address = entityFinderService.addressRepository.findById(addressId).orElseThrow();
            Customer customer = address.getCustomer();
            customer.getAddress().remove(address);
            entityFinderService.customerRepository.save(customer);
            entityFinderService.addressRepository.delete(address);

            baseResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
            baseResponse.setResponseMessage(CommonConstant.ADD_DELETE_MESSAGE);
            return  baseResponse;
        } catch (Exception e) {
            baseResponse.setResponseCode(CommonConstant.UNPROSS_ENTITY_CODE);
            baseResponse.setResponseMessage(CommonConstant.CUS_UP_ERROR_MESSAGE);
            return baseResponse;

        }
    }

    public BaseResponse viewAddress(String customerId) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            BigInteger customerID = new BigInteger(customerId);
            Customer customer = entityFinderService.customerRepository.findById(customerID).orElseThrow();

            List<Address> addresses = entityFinderService.addressByCustomer(customer);

            List<HashMap <String, Object>> payload = new ArrayList<>();

            HashMap addressHashMap = new HashMap<>();
            addressHashMap.put("addresses", addresses);
            payload.add(addressHashMap);

            baseResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
            baseResponse.setResponseMessage(CommonConstant.ADD_VIEW_MESSAGE);
            baseResponse.setPayload(payload);
            return baseResponse;

        } catch (Exception e) {
            baseResponse.setResponseCode(CommonConstant.UNPROSS_ENTITY_CODE);
            baseResponse.setResponseMessage(CommonConstant.CUS_UP_ERROR_MESSAGE);
            return baseResponse;
        }
    }
}