package com.shinelon.sharding.sphere;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SphereApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SphereApplicationTests {
	protected Logger logger = LoggerFactory.getLogger(getClass());

}
