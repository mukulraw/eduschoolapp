package com.eduschool.eduschoolapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mukul on 20/11/17.
 */

public class transportBean {

    @SerializedName("transport_type")
    @Expose
    private String transportType;
    @SerializedName("driver_id")
    @Expose
    private String driverId;
    @SerializedName("driver_name")
    @Expose
    private String driverName;
    @SerializedName("driver_number")
    @Expose
    private String driverNumber;
    @SerializedName("conductor_id")
    @Expose
    private String conductorId;
    @SerializedName("conductor_name")
    @Expose
    private String conductorName;
    @SerializedName("conductor_phone")
    @Expose
    private String conductorPhone;
    @SerializedName("security_id")
    @Expose
    private String securityId;
    @SerializedName("security_name")
    @Expose
    private String securityName;
    @SerializedName("security_phone")
    @Expose
    private String securityPhone;
    @SerializedName("vehicle_id")
    @Expose
    private String vehicleId;
    @SerializedName("vehicle_number")
    @Expose
    private String vehicleNumber;
    @SerializedName("registerd_name")
    @Expose
    private String registerdName;
    @SerializedName("registered_year")
    @Expose
    private String registeredYear;
    @SerializedName("engine_type")
    @Expose
    private String engineType;
    @SerializedName("no_of_seats")
    @Expose
    private String noOfSeats;
    @SerializedName("pickup_point")
    @Expose
    private String pickupPoint;
    @SerializedName("pickup_time")
    @Expose
    private String pickupTime;
    @SerializedName("drop_point")
    @Expose
    private String dropPoint;
    @SerializedName("drop_time")
    @Expose
    private String dropTime;

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverNumber() {
        return driverNumber;
    }

    public void setDriverNumber(String driverNumber) {
        this.driverNumber = driverNumber;
    }

    public String getConductorId() {
        return conductorId;
    }

    public void setConductorId(String conductorId) {
        this.conductorId = conductorId;
    }

    public String getConductorName() {
        return conductorName;
    }

    public void setConductorName(String conductorName) {
        this.conductorName = conductorName;
    }

    public String getConductorPhone() {
        return conductorPhone;
    }

    public void setConductorPhone(String conductorPhone) {
        this.conductorPhone = conductorPhone;
    }

    public String getSecurityId() {
        return securityId;
    }

    public void setSecurityId(String securityId) {
        this.securityId = securityId;
    }

    public String getSecurityName() {
        return securityName;
    }

    public void setSecurityName(String securityName) {
        this.securityName = securityName;
    }

    public String getSecurityPhone() {
        return securityPhone;
    }

    public void setSecurityPhone(String securityPhone) {
        this.securityPhone = securityPhone;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getRegisterdName() {
        return registerdName;
    }

    public void setRegisterdName(String registerdName) {
        this.registerdName = registerdName;
    }

    public String getRegisteredYear() {
        return registeredYear;
    }

    public void setRegisteredYear(String registeredYear) {
        this.registeredYear = registeredYear;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(String noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    public String getPickupPoint() {
        return pickupPoint;
    }

    public void setPickupPoint(String pickupPoint) {
        this.pickupPoint = pickupPoint;
    }

    public String getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime;
    }

    public String getDropPoint() {
        return dropPoint;
    }

    public void setDropPoint(String dropPoint) {
        this.dropPoint = dropPoint;
    }

    public String getDropTime() {
        return dropTime;
    }

    public void setDropTime(String dropTime) {
        this.dropTime = dropTime;
    }

}
