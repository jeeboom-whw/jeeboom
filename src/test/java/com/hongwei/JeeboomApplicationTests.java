package com.hongwei;

import com.hongwei.common.util.EhcacheUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JeeboomApplicationTests {

	@Test
	public void contextLoads() {
		EhcacheUtil.put("username","wanghongwei");
		System.out.println(EhcacheUtil.get("username"));
	}

}
