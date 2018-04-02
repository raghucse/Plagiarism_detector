package edu.neu;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import edu.neu.user.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;

import static java.util.Collections.emptyList;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource(properties = {"spring.jpa.hibernate.ddl-auto=create"})
@Ignore
public class AbstractMvc {
    protected MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();
    private static Set<Class> inited = new HashSet<>();

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String token;

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

        register("sa@example.com","123456","PROFESSOR");
        extractToken(login("sa@example.com", "123456"));
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
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.msg", is("registration successful")));
    }


    protected ResultActions login(String username, String password) throws Exception {
        final AuthenticationRequest auth = new AuthenticationRequest();
        auth.setUserName(username);
        auth.setPassword(password);

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

    protected void extractToken(ResultActions result) throws UnsupportedEncodingException {
       setToken(result.andReturn().getResponse().getHeader("Authorization"));

    }

}
