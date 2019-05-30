package fr.manuqcr.fizzbuzz.service;

import fr.manuqcr.fizzbuzz.model.Request;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FizzBuzzService {

    List<String> fizzBuzz(Request request) {
        return IntStream.range(1, request.getLimit() + 1)
                .mapToObj(i -> {
                    String result = "";
                    if (isFactor(i, request.getInt1())) {
                        result += request.getStr1();
                    }
                    if (isFactor(i, request.getInt2())) {
                        result += request.getStr2();
                    }
                    if (result.isEmpty()) {
                        result = "" + i;
                    }
                    return result;
                })
                .collect(Collectors.toList());
    }

    boolean isFactor(int i, int factor) {
        return i % factor == 0;
    }

}
