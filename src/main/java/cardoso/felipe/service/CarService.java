package cardoso.felipe.service;

import cardoso.felipe.domain.Cars;
import cardoso.felipe.exception.CarNotFoundException;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class CarService {

    public Cars createCar(Cars car) {
        Cars.persist(car);
        return car;
    }

    public List<Cars> findAllCars(Integer page, Integer limit) {
        return Cars.findAll()
                .page(page, limit)
                .list();
    }

    public Cars findCarById(UUID id) {
        return (Cars) Cars.findByIdOptional(id)
                .orElseThrow(CarNotFoundException::new);
    }

    public Cars updateCar(UUID id, Cars cars) {
        var car = findCarById(id);
        car.brand = cars.brand;
        car.color = car.color;
        car.name = cars.name;
        car.description = cars.description;
        car.year = cars.year;
        Cars.persist(car);
        return car;
    }

    public void deleteCar(UUID id) {
        var car = findCarById(id);
        car.delete();
    }
}
