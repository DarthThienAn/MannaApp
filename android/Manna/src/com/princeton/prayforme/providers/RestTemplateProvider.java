package com.princeton.prayforme.providers;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class RestTemplateProvider {

    public static RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();

//        objectMapper.configure(DeserializationConfig.Feature.READ_ENUMS_USING_TO_STRING, true);
//        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
//        // should fail during development
//        objectMapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);

//        List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
//        for (HttpMessageConverter<?> messageConverter : messageConverters) {
//            if (messageConverter instanceof MappingJacksonHttpMessageConverter) {
//                ((MappingJacksonHttpMessageConverter) messageConverter).setObjectMapper(objectMapper);
//            }
//        }

        MappingJacksonHttpMessageConverter jsonConverter = new MappingJacksonHttpMessageConverter();
        jsonConverter.setObjectMapper(objectMapper);
//        restTemplate.getMessageConverters().add(jsonConverter);


        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

        return restTemplate;
    }
}
