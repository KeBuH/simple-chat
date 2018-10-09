package ru.tretyakov.controllers;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.testng.Assert.*;

@WebAppConfiguration
@ContextConfiguration(locations = "classpath:spring-mvc-context.xml")
public class WebControllerTest extends AbstractTestNGSpringContextTests {

    @InjectMocks
    WebController controller;
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @BeforeTest
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenContextThenControllerNonNull() {
        assertNotNull(controller);
    }

    @Test
    public void whenContextThenWACNonNull() {
        assertNotNull(wac);
    }

    @Test
    public void whenContextThenMockNonNull() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        assertNotNull(mockMvc);
    }

    @Test
    public void whenGetIndexPageThenRedirectToSignin() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        mockMvc.perform(get("/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/signin"));
    }

    @Test
    public void whenSigninThenRedirectTiIndexPage() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        mockMvc.perform(post("/signin")
                .param("username", "alex"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        "{\"status\":\"ok\"}"));
    }
}