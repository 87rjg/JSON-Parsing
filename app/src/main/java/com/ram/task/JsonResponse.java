package com.ram.task;

import android.content.Context;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by RAMJEE on 16-02-2018.
 */

public class JsonResponse {


    @SerializedName("worldpopulation")
    @Expose
    private ArrayList<Country> worldpopulation = new ArrayList<>();

    public ArrayList<Country> getWorldpopulation() {
        return worldpopulation;
    }

    public void setWorldpopulation(ArrayList<Country> worldpopulation) {
        this.worldpopulation = worldpopulation;
    }
}
