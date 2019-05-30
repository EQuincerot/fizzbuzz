package fr.manuqcr.fizzbuzz.service;

import fr.manuqcr.fizzbuzz.model.Request;
import org.springframework.stereotype.Service;

import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class FizzBuzzService implements IFizzBuzzService {

    @Override
    public Stream<String> fizzBuzz(Request request) {
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
                });
    }

    boolean isFactor(int i, int factor) {
        return i % factor == 0;
    }

}
