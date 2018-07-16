/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cadastro.boundary;

import br.com.cadastro.model.City;
import br.com.cadastro.service.CityService;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Aluno
 */
@Path("city")
public class CityController {
    
    @Inject
    private CityService service;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get() {
        return Response.ok(service.getCities()).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getById(@PathParam("id") long id)
    {
        City city = service.getCity(id);
        if(Objects.nonNull(city)) {
            return Response.ok(city).build();    
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") long id, City city) {
        Optional<City> updatedCity = service.updateCity(id, city.getName(), city.getPopulation());
        if (updatedCity.isPresent()) {
            return Response.ok(updatedCity.get()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") long id) {
        service.removeCity(id);
        return Response.ok().build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(City city) {
        service.createCity(city.getName(), city.getPopulation());
        return Response.status(Response.Status.CREATED).build();
    }
}
