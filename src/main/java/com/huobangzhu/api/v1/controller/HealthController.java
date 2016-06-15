package com.huobangzhu.api.v1.controller;

import com.huobangzhu.foundation.model.StatusResponse;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static com.huobangzhu.foundation.model.StatusResponse.success;

@Path("health")
@Api(value = "health", description = "Health Check", position = 0)
@Consumes("application/json")
@Produces("application/json")
@RestController
@RequestMapping(value = "health", consumes = "application/json", produces = "application/json")
public class HealthController{

    @GET
    @Path("/success")
    @ApiOperation(value = "Health Check Success", response = StatusResponse.class, position = 0)
    @RequestMapping(value = "success", method = RequestMethod.GET)
    public
    @ResponseBody
    StatusResponse hello(){
        return success();
    }
}
