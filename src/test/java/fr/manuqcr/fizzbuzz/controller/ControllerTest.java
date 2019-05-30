package fr.manuqcr.fizzbuzz.controller;

import com.google.common.base.Charsets;
import fr.manuqcr.fizzbuzz.model.Request;
import fr.manuqcr.fizzbuzz.service.IFizzBuzzService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

@ExtendWith(SpringExtension.class)
@WebMvcTest(Controller.class)
class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IFizzBuzzService service;

    @ParameterizedTest
    @MethodSource("invalidIntParam")
    void invalidInt1Param_throws_400_bad_request(String int1) throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/fizzbuzz")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .param("int1", int1)
                .param("int2", "5")
                .param("limit", "100")
                .param("str1", "str1")
                .param("str2", "str2")
        ).andReturn();

        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(400);
    }

    @ParameterizedTest
    @MethodSource("invalidIntParam")
    void invalidInt2Param_throws_400_bad_request(String int2) throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/fizzbuzz")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .param("int1", "3")
                .param("int2", int2)
                .param("limit", "100")
                .param("str1", "str1")
                .param("str2", "str2")
        ).andReturn();

        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(400);
    }

    static Stream<String> invalidIntParam() {
        return Stream.of("-100", "-1", null, "", "0", "100000000000000000000000000000000", "NaN", "1E1", "0.5");
    }

    @Test
    void validRequest_returns_200() throws Exception {
        // given
        Mockito.when(service.fizzBuzz(ArgumentMatchers.any())).thenReturn(Stream.of("1", "fizz", "buzz", "fizz", "5"));

        // when
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/fizzbuzz")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .param("int1", "2")
                .param("int2", "3")
                .param("limit", "5")
                .param("str1", "fizz")
                .param("str2", "buzz")
        ).andReturn();

        // then
        Mockito.verify(service)
                .fizzBuzz(ArgumentMatchers.eq(new Request(2, 3, 5, "fizz", "buzz")));
        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(200);
        Assertions.assertThat(result.getResponse().getContentAsString())
                .isEqualTo(loadFileContent("./expectedValidResponse.json"));

    }

    @ParameterizedTest
    @MethodSource("invalidIntParam")
    void invalidLimitParam_throws_400_bad_request(String limit) throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/fizzbuzz")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .param("int1", "3")
                .param("int2", "5")
                .param("limit", limit)
                .param("str1", "str1")
                .param("str2", "str2")
        ).andReturn();

        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(400);
    }


    @ParameterizedTest
    @MethodSource("invalidStrParam")
    void invalidStr1Param_throws_400_bad_request(String str1) throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/fizzbuzz")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .param("int1", "3")
                .param("int2", "5")
                .param("limit", "100")
                .param("str1", str1)
                .param("str2", "str2")
        ).andReturn();

        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(400);
    }

    @ParameterizedTest
    @MethodSource("invalidStrParam")
    void invalidStr2Param_throws_400_bad_request(String str2) throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/fizzbuzz")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .param("int1", "3")
                .param("int2", "5")
                .param("limit", "100")
                .param("str1", str2)
                .param("str2", "str2")
        ).andReturn();

        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(400);
    }
    private static Stream<String> invalidStrParam() {
        return Stream.of(null, "quote\"injection");
    }

    private String loadFileContent(String fileName) throws IOException, URISyntaxException {
        return String.join("\n",
                Files.readAllLines(Paths.get(this.getClass().getResource(fileName).toURI()),
                        Charsets.UTF_8));
    }


}