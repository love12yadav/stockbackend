package com.Stock.demo.DTO;


import java.util.List;

public class StockChartData {
    public String s; // status
    public List<Long> t; // timestamps
    public List<Double> c; // close prices

    // Optional: Add getters/setters if needed

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public List<Long> getT() {
        return t;
    }

    public void setT(List<Long> t) {
        this.t = t;
    }

    public List<Double> getC() {
        return c;
    }

    public void setC(List<Double> c) {
        this.c = c;
    }
}
