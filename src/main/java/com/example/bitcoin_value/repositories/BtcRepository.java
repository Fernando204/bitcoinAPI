package com.example.bitcoin_value.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.bitcoin_value.Models.BtcEntity;

@Repository
public interface BtcRepository extends MongoRepository<BtcEntity,String>{
    public List<BtcEntity> findAll();
}
