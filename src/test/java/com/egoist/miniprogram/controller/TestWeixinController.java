package com.egoist.miniprogram.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
//测试环境使用，用来表示测试环境使用的ApplicationContext将是WebApplicationContext类型的
@WebAppConfiguration
public class TestWeixinController {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        //MockMvcBuilders.webAppContextSetup(WebApplicationContext context)：指定WebApplicationContext，将会从该上下文获取相应的控制器并得到相应的MockMvc；
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();//建议使用这种
    }


    @Test
    public void testLogin() throws Exception {
        /**
         * 1、mockMvc.perform执行一个请求。
         * 2、MockMvcRequestBuilders.get("XXX")构造一个请求。
         * 3、ResultActions.param添加请求传值
         * 4、ResultActions.accept(MediaType.TEXT_HTML_VALUE))设置返回类型
         * 5、ResultActions.andExpect添加执行完成后的断言。
         * 6、ResultActions.andDo添加一个结果处理器，表示要对结果做点什么事情
         *   比如此处使用MockMvcResultHandlers.print()输出整个响应结果信息。
         * 5、ResultActions.andReturn表示执行完成后返回相应的结果。
         */
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/miniprogram/login")
                .param("code", "1234")
                .accept(MediaType.APPLICATION_JSON))
                // .andExpect(MockMvcResultMatchers.status().isOk())             //等同于Assert.assertEquals(200,status);
                // .andExpect(MockMvcResultMatchers.content().string("hello lvgang"))    //等同于 Assert.assertEquals("hello lvgang",content);
                //.andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果
        System.out.println("#############################################################");
        System.out.println(status);
        System.out.println(content);
//        Assert.assertEquals(200, status);                        //断言，判断返回代码是否正确
//        Assert.assertEquals("hello lvgang", content);            //断言，判断返回的值是否正确
    }

    @Test
    public void test() throws Exception {
        String userId = java.util.UUID.randomUUID().toString();
        System.out.println(userId);
    }
}
