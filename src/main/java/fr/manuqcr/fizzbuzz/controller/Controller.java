package fr.manuqcr.fizzbuzz.controller;

import fr.manuqcr.fizzbuzz.model.Request;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    @RequestMapping("/fizzbuzz")
    public String fizzbuzz(@RequestParam(value = "int1") Integer int1,
                                 @RequestParam(value = "int2") Integer int2,
                                 @RequestParam(value = "limit") Integer limit,
                                 @RequestParam(value = "str1") String str1,
                                 @RequestParam(value = "str2") String str2) {
        return new Request(int1, int2, limit, str1, str2).toString();
    }
}
