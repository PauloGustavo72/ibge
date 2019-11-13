package com.apiibge.controller;

import com.apiibge.service.JsonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(JsonController.class)
public class JsonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JsonService jsonService;

    @Test
    public void test_JsonController() throws Exception {
        this.mockMvc.perform(get("/v1/ibge/json")
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("charset", "utf-8"))
                    .andDo(print())
                    .andExpect(status().isOk());
    }


}