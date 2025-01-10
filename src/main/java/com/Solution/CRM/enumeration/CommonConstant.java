package com.Solution.CRM.enumeration;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class CommonConstant {

    public static final int SUCCESS_CODE = HttpStatus.OK.value();
    public static final int BAD_REQUEST_CODE = HttpStatus.BAD_REQUEST.value();
    public static final int NOT_FOUND_CODE = HttpStatus.NOT_FOUND.value();
    public static final int UNPROSS_ENTITY_CODE = HttpStatus.UNPROCESSABLE_ENTITY.value();
    public static final int TIME_OUT_CODE = HttpStatus.GATEWAY_TIMEOUT.value();

    //Messages
    public static final String CUS_EXISTS_MESSAGE = "Customer already exists.";
    public static final String CUS_NOT_FOUND_MESSAGE = "Customer not found.";
    public static final String CUS_CREATED_MESSAGE  = "Customer created successfully.";
    public static final String ADD_CREATED_MESSAGE  = "Address created successfully.";

    public static final String CUS_VIEW_MESSAGE  = "Customer is retrieved successfully.";
    public static final String ADD_VIEW_MESSAGE  = "All addresses are retrieved successfully.";
    public static final String CUS_EDIT_MESSAGE  = "Customer edited successfully.";
    public static final String ADD_EDIT_MESSAGE  = "Customer edited successfully.";
    public static final String CUS_CREATE_UP_ERROR_MESSAGE  = "Failed to create customer.";
    public static final String CUS_UP_ERROR_MESSAGE  = "Failed to process request";
    public static final String CUS_DELETE_MESSAGE  = "Customer deleted successfully.";
    public static final String ADD_DELETE_MESSAGE  = "Address deleted successfully.";
}