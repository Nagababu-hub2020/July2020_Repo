package com.hcl.sample;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@SpringBootTest
@ComponentScan(basePackages = { "com.hcl.sample","com.hcl.sample.controller" })
class SampleAppApplicationTests {

	@Test
	void contextLoads() {

	}
}
