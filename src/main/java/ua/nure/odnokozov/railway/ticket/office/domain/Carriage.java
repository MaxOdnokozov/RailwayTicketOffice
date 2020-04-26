package ua.nure.odnokozov.railway.ticket.office.domain;

import java.math.BigDecimal;

import ua.nure.odnokozov.railway.ticket.office.enums.ComfortType;

public class Carriage extends Entity {

    private static final long serialVersionUID = -3184362512441098355L;
    
    private String model;
    private String image;
    private int totalSeats;
    private ComfortType comfortType;
    private BigDecimal priceCoefficient;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public ComfortType getComfortType() {
        return comfortType;
    }

    public void setComfortType(ComfortType comfortType) {
        this.comfortType = comfortType;
    }

    public BigDecimal getPriceCoefficient() {
        return priceCoefficient;
    }

    public void setPriceCoefficient(BigDecimal priceCoefficient) {
        this.priceCoefficient = priceCoefficient;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Carriage [id=").append(getId())
                .append(", model=").append(model)
                .append(", image=").append(image)
                .append(", comfortType=").append(comfortType)
                .append(", totalSeats=").append(totalSeats)
                .append("]").toString();
    }
}
