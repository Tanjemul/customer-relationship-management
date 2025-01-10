package com.Solution.CRM.dto;

import com.google.gson.Gson;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;

@Data
@RequiredArgsConstructor
public class AddressDeleteRequest {

    private String addressId;
    private String email;

    public String toJson(){
        return new Gson().toJson(this);
    }
    public HashMap toHashMap(){
        return new Gson().fromJson(this.toJson(), HashMap.class);
    }
}