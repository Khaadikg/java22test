package com.example.demo.templates;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JsonTemplate implements StringTemplate.Processor<JsonNode,Throwable> {

    @Override
    public JsonNode process(StringTemplate tmpl) throws Throwable {

            String jsonStr = tmpl.interpolate();
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readTree(jsonStr);
    }
}
