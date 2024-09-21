package com.example.usdToNtd.vo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExchangeDataRepository extends MongoRepository<ExchangeData, String>{
    
}
