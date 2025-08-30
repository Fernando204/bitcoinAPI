package com.example.bitcoin_value.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bitcoin_value.Models.BtcEntity;
import com.example.bitcoin_value.repositories.BtcRepository;

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
        Map<String,Integer> responseMap = new HashMap<>();
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

    private Map<String,Integer> filterValues(List<BtcEntity> valueList,LocalDateTime minDate){
        Map<String,Integer> responseMap = new HashMap<>();
        valueList.stream()
                .filter(v -> v.getData() != null && v.getData().isAfter(minDate))
                .forEach(v ->{
                    responseMap.put(v.getData().format(formatter), v.getBtcMap().get("brl"));
                });

        return  responseMap;
    }
    
}
