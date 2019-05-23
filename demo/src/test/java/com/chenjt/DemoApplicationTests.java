package com.chenjt;

import com.chenjt.common.Trie;
import com.chenjt.controller.HelloController;
import com.chenjt.service.JustForTestService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
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

import java.util.List;
import java.util.Map;

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

	@Test
	public void test(){
		String str = "陈剑挺帅的一比";
		Trie trie = new Trie();
		trie.insert("傻逼");
		trie.insert("你打球真像蔡徐坤");
		trie.insert("傻逼啊");
		trie.insert("傻逼傻逼");

		List<Trie.FilterResultElement> ret = trie.filter("卧槽，你打球真像蔡徐坤啊，大傻逼");
		RandomUtils.nextInt(100, 200);//左闭右开
		System.out.println(RandomStringUtils.randomNumeric(8));
	}

}
