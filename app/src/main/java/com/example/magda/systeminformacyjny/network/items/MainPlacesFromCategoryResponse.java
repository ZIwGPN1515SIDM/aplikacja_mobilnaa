package com.example.magda.systeminformacyjny.network.items;

import com.example.magda.systeminformacyjny.models.MainPlace;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Wojciech on 13.04.2017.
 */

public class MainPlacesFromCategoryResponse {

    @SerializedName("namespaces")
    @Expose
    private List<MainPlace> mainPlaces;


    public List<MainPlace> getMainPlaces() {
        return mainPlaces;
    }

    public void setMainPlaces(List<MainPlace> mainPlaces) {
        this.mainPlaces = mainPlaces;
    }
}
