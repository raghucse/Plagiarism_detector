package edu.neu;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.neu.user.ApplicationUser;
import edu.neu.user.Role;
import edu.neu.user.UserInfoRes;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
@RunWith(SpringRunner.class)
//@TestPropertySource(properties = {"spring.jpa.hibernate.ddl-auto=create"})
@Ignore
public class AbstractMvc {
    protected MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();
    private static Set<Class> inited = new HashSet<>();

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        mockMvc = webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
    }

    @Before
    public void init() throws Exception {
        if (!inited.contains(getClass())) {
            doInit();
            inited.add(getClass());
        }
    }

    protected void doInit() throws Exception {
    }

    protected String json(Object o) throws IOException {
        return mapper.writeValueAsString(o);
    }

    protected void register(String username, String password, String role) throws Exception {
        mockMvc.perform(post("/registration")
                .param("username",username)
                .param("password", password)
                .param("role",role))
                .andExpect(status().isOk());
    }


    protected ResultActions login(String username, String password) throws Exception {
        ApplicationUser user = new ApplicationUser();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(Role.PROFESSOR);

        ResultActions result = mockMvc.perform(
                post("/login")
                        .content(json(user))
                        .contentType(MediaType.APPLICATION_JSON));
        return result;
    }

    protected String extractToken(ResultActions result) throws UnsupportedEncodingException {
       return result.andReturn().getResponse().getHeader("Authorization");

    }

    protected String extractUserName(ResultActions result) throws UnsupportedEncodingException {
        return result.andReturn().getResponse().getHeader("User");
    }

    protected UserInfoRes getUserInfo(String userName, String token) throws Exception {
        ObjectMapper obj = new ObjectMapper();
        String url = "/user?userName="+userName;
        ResultActions result = mockMvc.perform(get(url)
                .header("Authorization", token));
        return obj.readValue(result.andReturn().getResponse().getContentAsString(), UserInfoRes.class);

    }


}
