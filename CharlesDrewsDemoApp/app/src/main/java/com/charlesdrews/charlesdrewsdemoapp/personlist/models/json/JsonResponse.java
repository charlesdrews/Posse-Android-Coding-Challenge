package com.charlesdrews.charlesdrewsdemoapp.personlist.models.json;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by charlie on 8/13/16.
 */
//@Generated("org.jsonschema2pojo")
public class JsonResponse {

//    @SerializedName("locations")
//    @Expose
    private List<JsonLocation> locations = new ArrayList<JsonLocation>();

    /**
     *
     * @return
     * The locations
     */
    public List<JsonLocation> getLocations() {
        return locations;
    }

    /**
     *
     * @param locations
     * The locations
     */
    public void setLocations(List<JsonLocation> locations) {
        this.locations = locations;
    }
}
