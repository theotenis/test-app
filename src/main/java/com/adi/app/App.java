package com.adi.app;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.adi.app.web.Summary;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;


@Controller
@EnableAutoConfiguration
public class App {

    @RequestMapping("/hello")
    @ResponseBody
    public byte[] home() {
    	
    	byte[] result = null;
    	Summary summary1 = new Summary("1", "Kia", "Red");
    	Summary summary2 = new Summary("2", "Ford", "Blue");
    	List<Summary> resultList = new ArrayList<Summary>();
    	resultList.add(summary1);
    	resultList.add(summary2);
        try {
			result = filterSummaryResponse().writeValueAsBytes(resultList);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return result;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(App.class, args);
    }
    
    private ObjectWriter filterSummaryResponse() {
    	ObjectMapper mapper = new ObjectMapper();
    	
        mapper.getSerializationConfig().findMixInClassFor(Summary.class);  

        String[] ignorableFieldNames = { "id" };
        FilterProvider filters = new SimpleFilterProvider()  
          .addFilter("filter properties by name",   
              SimpleBeanPropertyFilter.serializeAllExcept(  
                  ignorableFieldNames));  
        ObjectWriter writer = mapper.writer(filters);
        
        return writer;
    }
}
