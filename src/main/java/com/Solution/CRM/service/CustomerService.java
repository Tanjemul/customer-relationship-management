package com.Solution.CRM.service;

import com.Solution.CRM.dto.BaseResponse;
import com.Solution.CRM.dto.CustomerCreateRequest;
import com.Solution.CRM.dto.CustomerDeleteRequest;
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

import static com.Solution.CRM.enumeration.CommonConstant.CUS_VIEW_MESSAGE;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {
    @Autowired
    EntityFinderService entityFinderService;
    @Autowired
    EntityFinder entityFinder;
    @Autowired
    Gson gson;

    public BaseResponse createCustomer(CustomerCreateRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            Customer customer = entityFinderService.customerByEmail(request.getEmail());
            if(null != customer.getEmail()) {
                baseResponse.setResponseCode(CommonConstant.BAD_REQUEST_CODE);
                baseResponse.setResponseMessage(CommonConstant.CUS_EXISTS_MESSAGE );
                return baseResponse;

            } else {

                customer.setEmail(request.getEmail());
                customer.setMobile(request.getMobile());
                customer.setCreatedTime(LocalDateTime.now());
                customer.setFirstName(request.getFirstName());
                customer.setMiddleName(request.getMiddleName());
                customer.setLastName(request.getLastName());

                entityFinderService.customerRepository.save(customer);

                Address address = new Address();
                address.setAddressLine1(request.getAddress().get("addressLine1").toString());
                address.setAddressLine2(request.getAddress().get("addressLine2").toString());
                address.setCity(request.getAddress().get("city").toString());
                address.setState(request.getAddress().get("state").toString());
                address.setZipCode(request.getAddress().get("zipCode").toString());
                address.setSpecialMark(request.getAddress().get("specialMark").toString());
                address.setCreatedTime(LocalDateTime.now());
                address.setCustomer(customer);

                entityFinderService.addressRepository.save(address);
                baseResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
                baseResponse.setResponseMessage(CommonConstant.CUS_CREATED_MESSAGE
                        + "Customer Id: " + customer.getId() +", "
                        + "Address Id: " + address.getId() );

            }
            return baseResponse;
        } catch (Exception e) {
            baseResponse.setResponseCode(CommonConstant.UNPROSS_ENTITY_CODE);
            baseResponse.setResponseMessage(CommonConstant.CUS_CREATE_UP_ERROR_MESSAGE );
            baseResponse.setResponseMessage(CommonConstant.CUS_UP_ERROR_MESSAGE);

            return baseResponse;
        }
    }

    public BaseResponse editCustomer(CustomerCreateRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            BigInteger customerId = new BigInteger(request.getCustomerId());
            Customer customer = entityFinderService.customerRepository.findById(customerId).orElse(new Customer());
            if(null != customer.getEmail()) {
                if(null != request.getEmail()) {
                    customer.setEmail(request.getEmail());
                }
                if(null != request.getMobile()) {
                    customer.setMobile(request.getMobile());
                }
                if(null != request.getFirstName()) {
                    customer.setFirstName(request.getFirstName());
                }
                if (null != request.getMiddleName()) {
                    customer.setMiddleName(request.getMiddleName());
                }
                if (null!= request.getLastName()) {
                    customer.setLastName(request.getLastName());
                }
                customer.setLastUpdatedTime(LocalDateTime.now());
                entityFinderService.customerRepository.save(customer);
                baseResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
                baseResponse.setResponseMessage(CommonConstant.CUS_EDIT_MESSAGE);

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

    public BaseResponse deleteCustomer(CustomerDeleteRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            BigInteger customerId = new BigInteger(request.getCustomerId());
            Customer customer = entityFinderService.customerRepository.findById(customerId).orElseThrow();
            entityFinderService.customerRepository.delete(customer);

            baseResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
            baseResponse.setResponseMessage(CommonConstant.CUS_DELETE_MESSAGE);
            return  baseResponse;
        } catch (Exception e) {
            baseResponse.setResponseCode(CommonConstant.UNPROSS_ENTITY_CODE);
            baseResponse.setResponseMessage(CommonConstant.CUS_UP_ERROR_MESSAGE);
            return baseResponse;

        }
    }

    public BaseResponse viewCustomer(String customerId) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            BigInteger customerID = new BigInteger(customerId);
            Customer customer = entityFinderService.customerRepository.findById(customerID).orElseThrow();

            List<HashMap <String, Object>> payload = new ArrayList<>();

            HashMap customerHashMap = new HashMap<>();
            customerHashMap.put("customerDetails", customer);
            payload.add(customerHashMap);

            baseResponse.setResponseCode(CommonConstant.SUCCESS_CODE);
            baseResponse.setResponseMessage(CommonConstant.CUS_VIEW_MESSAGE);
            baseResponse.setPayload(payload);
            return baseResponse;

        } catch (Exception e) {
            baseResponse.setResponseCode(CommonConstant.UNPROSS_ENTITY_CODE);
            baseResponse.setResponseMessage(CommonConstant.CUS_UP_ERROR_MESSAGE);
            return baseResponse;
        }
    }
}