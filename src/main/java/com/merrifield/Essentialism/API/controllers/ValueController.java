package com.merrifield.Essentialism.API.controllers;


import com.merrifield.Essentialism.API.services.ValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/values")
public class ValueController {

    @Autowired
    ValueService valueService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> findAllValues() {
        return new ResponseEntity<>(valueService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> findValueById(@PathVariable long id) {
        return new ResponseEntity<>(valueService.findValueById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/{value}", produces = "application/json")
    public ResponseEntity<?> addValue(@PathVariable String value){
       return new ResponseEntity<>(valueService.addValue(value), HttpStatus.CREATED);
    };

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteValue(@PathVariable long id){
        valueService.deleteValue(id);
        return new ResponseEntity<>("success", HttpStatus.NO_CONTENT);
    }
}
