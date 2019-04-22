package com.chenjt;

import com.chenjt.controller.HelloController;
import com.chenjt.service.JustForTestService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest
public class DemoApplicationTests {

//    private MockMvc mockMvc;
//    @Autowired
//    private HelloController helloController;
//
//    @Before
//    public void setUp(){
//        mockMvc = MockMvcBuilders.standaloneSetup(helloController).build();
//    }
//
//	@Test
//	public void getHello() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().string(equalTo("chen")));
//        System.out.println("Hello");
//	}

//	@Autowired
//	private JustForTestService justForTestService;
//
//	@Test
//	public void test(){
//		String result = justForTestService.justForTest("chenjt");
//		System.out.println(result);
//	}

}
