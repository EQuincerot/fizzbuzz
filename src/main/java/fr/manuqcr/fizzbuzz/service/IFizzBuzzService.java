package fr.manuqcr.fizzbuzz.service;

import fr.manuqcr.fizzbuzz.model.Request;

import java.util.stream.Stream;

public interface IFizzBuzzService {
    Stream<String> fizzBuzz(Request request);
}
