package cardoso.felipe.controller;

import cardoso.felipe.domain.Cars;
import cardoso.felipe.service.CarService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.UUID;

@Path("/cars")
public class CarsController {

    private final CarService carService;

    public CarsController(CarService carService) {
        this.carService = carService;
    }

    @GET
    public Response findAllCars(@QueryParam("page") @DefaultValue("0") Integer page,
                                @QueryParam("limit") @DefaultValue("10") Integer limit) {
        var cars = carService.findAllCars(page, limit);
        return Response.ok(cars).build();
    }

    @GET
    @Path("/{id}")
    public Response findCarById(@PathParam("id") UUID id) {
        return Response.ok(carService.findCarById(id)).build();
    }

    @PUT
    @Path("/update/{id}")
    @Transactional
    public Response updateCars(@PathParam("id") UUID id, Cars cars) {
        return Response
                .ok(carService.updateCar(id, cars))
                .build();
    }

    @POST
    @Transactional
    public Response createCars(Cars cars) {
        return Response
                .ok(carService.createCar(cars))
                .build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Transactional
    public Response deleteCars(@PathParam("id") UUID id) {
        carService.deleteCar(id);
        return Response
                .noContent()
                .build();
    }
}
