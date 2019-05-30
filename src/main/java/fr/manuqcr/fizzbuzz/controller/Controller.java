package fr.manuqcr.fizzbuzz.controller;

import fr.manuqcr.fizzbuzz.helper.Helper;
import fr.manuqcr.fizzbuzz.model.Request;
import fr.manuqcr.fizzbuzz.service.IFizzBuzzService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Stream;

import static fr.manuqcr.fizzbuzz.helper.Helper.PATTERN;
import static fr.manuqcr.fizzbuzz.helper.Helper.isPositiveInt;

@RestController
public class Controller {

    private static final int MAX_LIMIT = 10000000;
    Logger logger = LoggerFactory.getLogger(Controller.class);

    final IFizzBuzzService service;

    @Autowired
    public Controller(IFizzBuzzService service) {
        this.service = service;
    }

    @ApiOperation(value = "Returns the famous Fizzbuzz result",
            notes = "Returns a list of strings with numbers from 1 to limit," +
                    " where: all multiples of int1 are replaced by str1, " +
                    "all multiples of int2 are replaced by str2, " +
                    "all multiples of int1 and int2 are replaced by str1str2.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad Request. Check the parameters"),
            @ApiResponse(code = 413, message = "Limit is too high. Decrease the limit")
    })
    @GetMapping(value = "/api/fizzbuzz", produces = "application/json")
    public Stream<String> fizzbuzz(
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
        if (!Stream.of(int1, int2).allMatch(Helper::isPositiveInt)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Int1 and int2 should be integer values greater than 0");
        }
        if (!isPositiveInt(limit)) {
            // Limit = 0 seems useless
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "limit should be integer values greater than 0");
        }
        if (limit > MAX_LIMIT) {
            throw new ResponseStatusException(HttpStatus.PAYLOAD_TOO_LARGE, "limit should be smaller than " + MAX_LIMIT);
        }
        if (!Stream.of(str1, str2).allMatch(Helper::isNotNullWord)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "String replacements should match this pattern: " + PATTERN);
        }
        Request request = new Request(int1, int2, limit, str1, str2);
        logger.info("Calling FizzBuzz for: {}", request);
        return service.fizzBuzz(request);
    }
}
