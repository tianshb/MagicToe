package com.crow;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HupuspiderApplicationTests {

	@Test
	public void contextLoads() {
		String word = "欢迎/v";
		if(word.matches("[\\u4e00-\\u9fa5]+/(n|v|a)")) {
			System.out.println(word.substring(0,word.length() - 2));
		}
	}

}
