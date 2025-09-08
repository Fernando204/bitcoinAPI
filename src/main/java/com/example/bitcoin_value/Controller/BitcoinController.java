package com.example.bitcoin_value.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bitcoin_value.Models.BtcEntity;
import com.example.bitcoin_value.repositories.BtcRepository;

import com.example.bitcoin_value.utils.Console;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/bitcoin")
@CrossOrigin("*")
public class BitcoinController {
    private BtcRepository btcRepository;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public BitcoinController(BtcRepository btcRepository){
        this.btcRepository = btcRepository;
    }

    @GetMapping("/get")
    @CrossOrigin("*")
    public ResponseEntity<?> getBitcoinValues(@RequestParam String param) {
        Map<String,Double> responseMap = new HashMap<>();
        List<BtcEntity> valueList = btcRepository.findAll();

        switch (param) {
            case "day" ->{
                responseMap = filterValues(valueList,LocalDate.now().atStartOfDay());
                return ResponseEntity.ok(responseMap);
            }
            case "month" ->{
                responseMap = filterValues(valueList,LocalDateTime.now().minusMonths(1));
                return ResponseEntity.ok(responseMap);
            }
            case "year" ->{
                responseMap = filterValues(valueList,LocalDateTime.now().minusYears(1));
                return ResponseEntity.ok(responseMap);
            }
            default ->{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: parametro invalido");
            }
        }
    }

    private Map<String,Double> filterValues(List<BtcEntity> valueList,LocalDateTime minDate){
        Map<String,Double> responseMap = new HashMap<>();
        valueList.stream()
                .filter(v -> v.getData() != null && v.getData().isAfter(minDate))
                .forEach(v ->{
                    Console.log(v.getBtcMap().get("BRL")+" R$");
                    responseMap.put(v.getData().format(formatter),v.getBtcMap().get("BRL"));
                });

        return  responseMap;
    }
    
}
