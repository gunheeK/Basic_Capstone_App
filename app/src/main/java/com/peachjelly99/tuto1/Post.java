package com.peachjelly99.tuto1;

import com.google.gson.annotations.SerializedName;

public class Post {
    @SerializedName("co2")
    private int co2;
    private double temperature;
    private int humi;
    private int waterLevel;

    private int autoMode;
    private int relay1;
    private int relay2;
    private int relay3;
    private int relay4;

    public Post(int co2, double temperature, int humi, int waterLevel, int autoMode, int relay1, int relay2, int relay3, int relay4) {
        this.co2 = co2;
        this.temperature = temperature;
        this.humi = humi;
        this.waterLevel = waterLevel;
        this.autoMode = autoMode;
        this.relay1 = relay1;
        this.relay2 = relay2;
        this.relay3 = relay3;
        this.relay4 = relay4;
    }

    public int getCo2() {
        return co2;
    }

    public double getTemperature() {
        return temperature;
    }

    public int getHumi() {
        return humi;
    }

    public int getWaterLevel() {
        return waterLevel;
    }

    public int getAutoMode() {
        return autoMode;
    }

    public int getRelay1() {
        return relay1;
    }

    public int getRelay2() {
        return relay2;
    }

    public int getRelay3() {
        return relay3;
    }

    public int getRelay4() {
        return relay4;
    }
}
