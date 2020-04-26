package ua.nure.odnokozov.railway.ticket.office.domain.builder;

import java.math.BigDecimal;

import ua.nure.odnokozov.railway.ticket.office.domain.Carriage;
import ua.nure.odnokozov.railway.ticket.office.enums.ComfortType;

public class CarriageBuilder {

    private Carriage carriage;

    private CarriageBuilder() {
        this.carriage = new Carriage();
    }

    public static CarriageBuilder getInstance() {
        return new CarriageBuilder();
    }

    public CarriageBuilder id(long id) {
        carriage.setId(id);
        return this;
    }

    public CarriageBuilder model(String model) {
        carriage.setModel(model);
        return this;
    }

    public CarriageBuilder image(String image) {
        carriage.setImage(image);
        return this;
    }
    
    public CarriageBuilder totalSeats(int totalSeats) {
        carriage.setTotalSeats(totalSeats);
        return this;
    }

    public CarriageBuilder comfortType(ComfortType type) {
        carriage.setComfortType(type);
        return this;
    }
    
    public CarriageBuilder priceCoefficient(BigDecimal priceCoefficient) {
        carriage.setPriceCoefficient(priceCoefficient);
        return this;
    }

    public Carriage build() {
        return carriage;
    }
}