package com.es.phoneshop.model.product;

import java.util.Objects;

public class Details {
    private String country;
    private int year;
    private String cpu;
    private String ram;
    private String flash;

    public Details() {
    }

    public Details(String country, int year, String cpu, String ram, String flash) {
        this.country = country;
        this.year = year;
        this.cpu = cpu;
        this.ram = ram;
        this.flash = flash;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getFlash() {
        return flash;
    }

    public void setFlash(String flash) {
        this.flash = flash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Details details = (Details) o;
        return year == details.year &&
                country.equals(details.country) &&
                cpu.equals(details.cpu) &&
                ram.equals(details.ram) &&
                flash.equals(details.flash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, year, cpu, ram, flash);
    }

    @Override
    public String toString() {
        return "Details{" +
                "country='" + country + '\'' +
                ", year=" + year +
                ", cpu='" + cpu + '\'' +
                ", ram='" + ram + '\'' +
                ", flash='" + flash + '\'' +
                '}';
    }
}
