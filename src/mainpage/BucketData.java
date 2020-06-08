package mainpage;

import java.io.Serializable;

public class BucketData implements Serializable {
    private String manufacturerCountry;
    private String manufacturerBrand;
    private String model;
    private int cpuCores;
    private double screenSize;
    private String os;
    private double accumulator;
    private double autonomousWorkTime;

    public BucketData() {
        manufacturerCountry = "";
        manufacturerBrand = "";
        model = "";
        cpuCores = 0;
        screenSize = 0;
        os = "";
        accumulator = 0;
        autonomousWorkTime = 0;
    }

    public double getAccumulator() {
        return accumulator;
    }

    public double getAutonomousWorkTime() {
        return autonomousWorkTime;
    }

    public double getScreenSize() {
        return screenSize;
    }

    public int getCpuCores() {
        return cpuCores;
    }

    public String getManufacturerBrand() {
        return manufacturerBrand;
    }

    public String getManufacturerCountry() {
        return manufacturerCountry;
    }

    public String getModel() {
        return model;
    }

    public String getOs() {
        return os;
    }

    public void setCpuCores(int cpuCores) {
        this.cpuCores = cpuCores;
    }

    public void setAccumulator(double accumulator) {
        this.accumulator = accumulator;
    }

    public void setAutonomousWorkTime(double autonomousWorkTime) {
        this.autonomousWorkTime = autonomousWorkTime;
    }

    public void setManufacturerBrand(String manufacturerBrand) {
        this.manufacturerBrand = manufacturerBrand;
    }

    public void setManufacturerCountry(String manufacturerCountry) {
        this.manufacturerCountry = manufacturerCountry;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public void setScreenSize(double screenSize) {
        this.screenSize = screenSize;
    }

}
