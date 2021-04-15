package au.com.worryfreeconsulting.DIObernasbeermanagement.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api("Beer Stock Management")
public interface BeerControllerDocs {

    @ApiOperation(value = "Ready to Drink?")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns a simple string = Ready to Drink?")
    })
    String helloAPI();
}
