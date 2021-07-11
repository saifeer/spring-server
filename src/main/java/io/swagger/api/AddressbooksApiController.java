package io.swagger.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiParam;
import io.swagger.model.AddressBook;
import io.swagger.model.Entry;
import io.swagger.service.AddressBookService;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-07-08T23:51:07.282Z")

@Controller
public class AddressbooksApiController implements AddressbooksApi {

    private static final Logger log = LoggerFactory.getLogger(AddressbooksApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
    @Autowired
    private AddressBookService addressBookService;

    @org.springframework.beans.factory.annotation.Autowired
    public AddressbooksApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<AddressBook> createAddressBook(@ApiParam(value = "A new `AddressBook` to be created." ,required=true )  @Valid @RequestBody AddressBook body) {
        return new ResponseEntity<AddressBook>(addressBookService.createAddressBook(body), HttpStatus.OK);
    }

    public ResponseEntity<Entry> createEntry(@ApiParam(value = "A unique identifier for a `AddressBook`.",required=true) @PathVariable("addressbookId") Integer addressbookId,@ApiParam(value = "A new `Entry` to be created." ,required=true )  @Valid @RequestBody Entry body) {
        try {
			return new ResponseEntity<Entry>(addressBookService.createEntry(addressbookId, body),HttpStatus.OK);
		} catch (NotFoundException e) {
			return new ResponseEntity<Entry>(HttpStatus.NOT_FOUND);
		}
    }

    public ResponseEntity<Void> deleteAddressBook(@ApiParam(value = "A unique identifier for a `AddressBook`.",required=true) @PathVariable("addressbookId") Integer addressbookId) {
    	try {
			addressBookService.deleteAddressBook(addressbookId);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} catch (NotFoundException e) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
    }

    public ResponseEntity<Void> deleteEntry(@ApiParam(value = "A unique identifier for a `AddressBook`.",required=true) @PathVariable("addressbookId") Integer addressbookId,@ApiParam(value = "A unique identifier for a `Entry`.",required=true) @PathVariable("entryId") Integer entryId) {
    	try {
			addressBookService.deleteEntry(addressbookId, entryId);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} catch (NotFoundException e) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
    }

    public ResponseEntity<AddressBook> getAddressBook(@ApiParam(value = "A unique identifier for a `AddressBook`.",required=true) @PathVariable("addressbookId") Integer addressbookId) {
    	try {
			return new ResponseEntity<AddressBook>(addressBookService.getAddressBook(addressbookId),HttpStatus.OK);
		} catch (NotFoundException e) {
			return new ResponseEntity<AddressBook>(HttpStatus.NOT_FOUND);
		}
    }

    public ResponseEntity<List<Entry>> getEntries(@ApiParam(value = "A unique identifier for a `AddressBook`.",required=true) @PathVariable("addressbookId") Integer addressbookId) {
    	try {
			return new ResponseEntity<List<Entry>>(addressBookService.getEntries(addressbookId),HttpStatus.OK);
		} catch (NotFoundException e) {
			return new ResponseEntity<List<Entry>>(HttpStatus.NOT_FOUND);
		}
    }

    public ResponseEntity<Entry> getEntry(@ApiParam(value = "A unique identifier for a `AddressBook`.",required=true) @PathVariable("addressbookId") Integer addressbookId,@ApiParam(value = "A unique identifier for a `Entry`.",required=true) @PathVariable("entryId") Integer entryId) {
    	try {
			return new ResponseEntity<Entry>(addressBookService.getEntry(addressbookId, entryId),HttpStatus.OK);
		} catch (NotFoundException e) {
			return new ResponseEntity<Entry>(HttpStatus.NOT_FOUND);
		}
    }

    public ResponseEntity<List<AddressBook>> getaddressbooks() {
		return new ResponseEntity<List<AddressBook>>(addressBookService.getAddressBooks(),HttpStatus.OK);
    }

}
