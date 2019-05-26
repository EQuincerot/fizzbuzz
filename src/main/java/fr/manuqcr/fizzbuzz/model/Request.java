package fr.manuqcr.fizzbuzz.model;

import java.util.Objects;

public class Request {

    private final Integer int1;
    private final Integer int2;
    private final Integer limit;
    private final String str1;
    private final String str2;

    public Request(Integer int1, Integer int2, Integer limit, String str1, String str2) {
        this.int1 = int1;
        this.int2 = int2;
        this.limit = limit;
        this.str1 = str1;
        this.str2 = str2;
    }

    public Integer getInt1() {
        return int1;
    }

    public Integer getInt2() {
        return int2;
    }

    public Integer getLimit() {
        return limit;
    }

    public String getStr1() {
        return str1;
    }

    public String getStr2() {
        return str2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return Objects.equals(int1, request.int1) &&
                Objects.equals(int2, request.int2) &&
                Objects.equals(limit, request.limit) &&
                Objects.equals(str1, request.str1) &&
                Objects.equals(str2, request.str2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(int1, int2, limit, str1, str2);
    }

    @Override
    public String toString() {
        return "Request{" +
                "int1=" + int1 +
                ", int2=" + int2 +
                ", limit=" + limit +
                ", str1='" + str1 + '\'' +
                ", str2='" + str2 + '\'' +
                '}';
    }
}
