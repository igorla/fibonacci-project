/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fibonacci.service;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes=com.fibonacci.service.ServiceControllerTests.class)
@ContextConfiguration(classes = {AppConfig.class})
public class ServiceControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void fibonacciNormalTest() throws Exception {
        this.mockMvc.perform(get("/fibonacci/0")).andDo(print()).andExpect(status().isOk())
        .andExpect(content().string("0"));
        this.mockMvc.perform(get("/fibonacci/1")).andDo(print()).andExpect(status().isOk())
        .andExpect(content().string("1"));
        this.mockMvc.perform(get("/fibonacci/3.8")).andDo(print()).andExpect(status().isOk())
        .andExpect(content().string("2"));
        this.mockMvc.perform(get("/fibonacci/30")).andDo(print()).andExpect(status().isOk())
        .andExpect(content().string("832040"));
    }

    @Test
    public void fibonacciInvalidParameterTest() throws Exception {
        this.mockMvc.perform(get("/fibonacci/-1")).andDo(print()).andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    public void fibonacciEmptyParameterTest() throws Exception {
        this.mockMvc.perform(get("/fibonacci/ ")).andDo(print()).andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }

  
}


