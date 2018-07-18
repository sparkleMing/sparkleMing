package com.sumixer.ys.api.web;

import org.junit.Test;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.http.MediaType;

//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author : coderWu
 * @date : Created on 20:19 2018/6/12
 */
@AutoConfigureRestDocs("target/generated-snippets")
public class AuthControllerTest extends BaseTest {


    @Test
    public void weiXinTest() throws Exception {
//        mvc.perform(get("/token/weixin/{code}", "081TZ8Ol1ctgvl0mSIPl10DoOl1TZ8O9").accept(MediaType.APPLICATION_JSON_UTF8))
//                .andExpect(status().isOk())
//                .andDo(document("/token/weixin/"));
    }

    @Test
    public void refreshTest() throws Exception {
//        this.mockMvc.perform(post(""))
    }
}