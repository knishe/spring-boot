package com.knishe;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.StringWriter;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class FreeMarkerControllerTest {

    @Autowired
    private Configuration configuration;

    @Autowired
    MockMvc mockMvc;

    @Test
    void testEmployee() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/employee"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("SampleName"))

                .andReturn();

    }


    @Test
    void testEmployeeWithJsonMatch() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/employee"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(parseTemplate("response.ftl", Map.of("name", "SampleName"))))
                .andReturn();

    }

    public String parseTemplate(String templateName, Map<String, Object> input) throws Exception {
        try {
            Template template = configuration.getTemplate(templateName);
            StringWriter writer = new StringWriter();

            template.process(input, writer);
            return writer.toString();
        } catch (Exception exception) {
            throw new RuntimeException("Processing failed for template", exception);
        }
    }
}