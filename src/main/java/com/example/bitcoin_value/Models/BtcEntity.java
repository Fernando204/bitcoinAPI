package com.example.bitcoin_value.Models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Bitcoin")
public class BtcEntity {
    
    @Id
    private String id;

    Map<String,Object> btcMap;
    LocalDateTime data;

    public BtcEntity(){}
    public BtcEntity(Map<String,Object> btcMap,LocalDateTime data){
        this.btcMap = btcMap;
        this.data = data;
    }

    public LocalDateTime getData() {
        return data;
    }
    public void setData(LocalDateTime data) {
        this.data = data;
    }
    public Map<String, Object> getBtcMap() {
        return btcMap;
    }
    public void setBtcMap(Map<String, Object> btcMap) {
        this.btcMap = btcMap;
    }
}
