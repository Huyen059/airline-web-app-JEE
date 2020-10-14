package com.airline.service;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless(name = "FlightServiceEJB")
@LocalBean
public class FlightServiceBean {
    private Integer id = 123456;
    private String from = "LA";
    private String to = "London";
    private Integer price = 400;
    private Integer numOfSeats = 200;
    private String airplaneType = "Airbus 300";

    public FlightServiceBean() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getNumOfSeats() {
        return numOfSeats;
    }

    public void setNumOfSeats(Integer numOfSeats) {
        this.numOfSeats = numOfSeats;
    }

    public String getAirplaneType() {
        return airplaneType;
    }

    public void setAirplaneType(String airplaneType) {
        this.airplaneType = airplaneType;
    }

    @Override
    public String toString() {
        return "FlightServiceBean{" +
                "id=" + id +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", price=" + price +
                ", numOfSeats=" + numOfSeats +
                ", airplaneType='" + airplaneType + '\'' +
                '}';
    }
}
