package com.example.bitcoin_value.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.bitcoin_value.Models.BtcEntity;
import com.example.bitcoin_value.repositories.BtcRepository;
import com.example.bitcoin_value.utils.Console;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;

@Component
public class BitcoinValue {
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper mapper = new ObjectMapper();
    private final ZoneId zone = ZoneId.of("America/Sao_Paulo");

    @Value("${bitcoin.api.url}")
    private String btcUrl;


    private BtcRepository btcRepository;

    public BitcoinValue(BtcRepository btcRepository){
        this.btcRepository = btcRepository;
    }

    @Scheduled(fixedRate = 1_200_000)
    @SchedulerLock(
        name = "bitcoinPriceTask",
        lockAtMostFor = "2m",   
        lockAtLeastFor = "5s"   
    )
    public void saveBitcoinValue(){
        System.out.println("fazendo chamada da api");
        ResponseEntity<String> response = restTemplate.getForEntity(btcUrl, String.class);
        try{
            Map<String,Object> btcMap = mapper.readValue(response.getBody(), new TypeReference<Map<String,Object>>(){});
            btcMap.remove("symbol");

            BtcEntity entity = new BtcEntity(btcMap,ZonedDateTime.now(zone).toLocalDateTime());
            btcRepository.save(entity);
            Console.log("Valores do bitcoin atualizados!  "+btcMap);
        }catch(Exception ex){
            Console.error("Erro ao atualizar valores do bitcoin "+ex.getMessage());
        }
        
    }

    @Scheduled(fixedRate = 86_400_000)
    public void deleteValues(){
        List<BtcEntity> allValues = btcRepository.findAll();
        List<BtcEntity> oldValues = allValues.stream()
                .filter(v -> v.getData() != null && v.getData().isBefore(LocalDateTime.now().minusYears(1)))
                .toList();

        oldValues.forEach(btcRepository::delete);
    }
}
