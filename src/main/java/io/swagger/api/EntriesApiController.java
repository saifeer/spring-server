package io.swagger.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.model.Entry;
import io.swagger.service.AddressBookService;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-07-08T23:51:07.282Z")

@Controller
public class EntriesApiController implements EntriesApi {

    private static final Logger log = LoggerFactory.getLogger(EntriesApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
    @Autowired
    private AddressBookService addressBookService;

    @org.springframework.beans.factory.annotation.Autowired
    public EntriesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<Entry>> getUniqueEntries() {
		return new ResponseEntity<List<Entry>>(addressBookService.getUniqueEntries(),HttpStatus.OK);
    }

}
