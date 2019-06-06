package com.chenjt;

import com.chenjt.common.Trie;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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
  public void test() throws IOException {
    String[] arrs = new String[]{"hot.csv", "8hr.csv", "imagerank.csv", "text.csv"};
    Set<String> nameSet = new HashSet<>();
    for (String item : arrs) {
      List<String> lines = FileUtils.readLines(new File("C:\\Users\\chenjianting_sx\\Desktop\\工作\\用户评价\\UserInfo\\" + item), Charsets.toCharset("utf-8"));
      for (String line : lines) {
        String[] element = line.split(",");
        String nickName = element[0];
        String avatar = element[1];
        if (!nameSet.contains(nickName)) {
          nameSet.add(nickName);
          writeToFile("C:\\Users\\chenjianting_sx\\Desktop\\工作\\用户评价\\UserInfo\\total.csv", nickName + "," + avatar + "\n", Charsets.toCharset("utf-8"), true);
        }

      }

    }

		String str = "陈剑挺帅的一比";
		Trie trie = new Trie();
		trie.insert("傻逼");
		trie.insert("你打球真像蔡徐坤");
		trie.insert("傻逼啊");
		trie.insert("傻逼傻逼");

    trie.insert("蠢");

		List<Trie.FilterResultElement> ret = trie.filter("卧槽，你打球真像蔡徐坤啊，大傻逼");
    trie.filterString("卧槽，你打球真像蔡徐坤啊，大傻逼");
    trie.filterString("陈剑挺不是傻逼啊哈哈哈");
		RandomUtils.nextInt(100, 200);//左闭右开
		System.out.println(RandomStringUtils.randomNumeric(8));
	}


  private void writeToFile(String path, String str, Charset charset, boolean flag) {
    File file = new File(path);
    try {
      if (!file.exists())
        file.createNewFile();
      FileUtils.writeStringToFile(file, str, charset, flag);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
