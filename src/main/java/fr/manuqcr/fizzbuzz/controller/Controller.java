package fr.manuqcr.fizzbuzz.controller;

import fr.manuqcr.fizzbuzz.model.Request;
import fr.manuqcr.fizzbuzz.service.IFizzBuzzService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    final IFizzBuzzService service;

    @Autowired
    public Controller(IFizzBuzzService service) {
        this.service = service;
    }

    @ApiOperation("Returns the famous Fizzbuzz result")
    @GetMapping(value = "/api/fizzbuzz", produces = "application/json")
    public List<String> fizzbuzz(
            @ApiParam(required = true)
            @RequestParam(value = "int1") Integer int1,

            @ApiParam(required = true)
            @RequestParam(value = "int2") Integer int2,

            @ApiParam(value = "Number of items to return", required = true)
            @RequestParam(value = "limit") Integer limit,

            @ApiParam(value = "Replacement for numbers divisible by int1", required = true)
            @RequestParam(value = "str1") String str1,

            @ApiParam(value = "Replacement for numbers divisible by int2", required = true)
            @RequestParam(value = "str2") String str2) {
        return service.fizzBuzz(new Request(int1, int2, limit, str1, str2));
    }
}
