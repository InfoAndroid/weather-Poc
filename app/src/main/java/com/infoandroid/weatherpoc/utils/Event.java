package com.infoandroid.weatherpoc.utils;

import com.infoandroid.weatherpoc.models.Weather;

import java.util.List;

/**
 * Created by mukesh
 */

public class Event {

    public static abstract class E {
        // Empty
    }

    public static class Producat extends E {
        List<Weather> longTermWeather;

        int action;

        public Producat(List<Weather> longTermWeather, int action) {
            this.longTermWeather = longTermWeather;
            this.action = action;
        }

        public List<Weather> getLongTermWeather() {
            return longTermWeather;
        }



        public int getAction() {
            return action;
        }
    }
    public static class ProfileData extends E {

        String data;
        public ProfileData(String data){
            this.data = data;
        }

        public String getData() {
            return data;
        }
    }
}
