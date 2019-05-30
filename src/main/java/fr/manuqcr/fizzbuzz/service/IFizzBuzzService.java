package fr.manuqcr.fizzbuzz.service;

import fr.manuqcr.fizzbuzz.model.Request;

import java.util.List;

public interface IFizzBuzzService {
    List<String> fizzBuzz(Request request);
}
