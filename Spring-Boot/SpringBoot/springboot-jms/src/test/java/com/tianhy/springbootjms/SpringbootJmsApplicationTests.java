package com.tianhy.springbootjms;

import com.tianhy.springbootjms.service.AsyncService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringbootJmsApplicationTests {


    @Autowired
    private AsyncService service = null;

    @Autowired
    private TestRestTemplate template = null;

    @Test
    public void syncTest() {
        String forObject = template.getForObject("/sync/page", String.class);
        System.out.println(forObject);
    }


}
