package com.Solution.CRM.dto;

import com.Solution.CRM.model.Address;
import com.google.gson.Gson;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.util.HashMap;

@Data
@RequiredArgsConstructor
public class CustomerCreateRequest {

    private String customerId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String mobile;
    private HashMap<String,Object> address;

    public String toJson(){
        return new Gson().toJson(this);
    }
    public HashMap toHashMap(){
        return new Gson().fromJson(this.toJson(), HashMap.class);
    }
}