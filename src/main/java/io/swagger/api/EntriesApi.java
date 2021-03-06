/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.21).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.model.Entry;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-07-08T23:51:07.282Z")

@Validated
@Api(value = "entries", description = "the entries API")
@RequestMapping(value = "")
public interface EntriesApi {

    @ApiOperation(value = "List all unique entries across all addressbooks", nickname = "getUniqueEntries", notes = "Gets a list of unqiue `Entry` entities.", response = Entry.class, responseContainer = "List", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful response - returns an array of `Entry` entities.", response = Entry.class, responseContainer = "List") })
    @RequestMapping(value = "/entries",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<List<Entry>> getUniqueEntries();

}
