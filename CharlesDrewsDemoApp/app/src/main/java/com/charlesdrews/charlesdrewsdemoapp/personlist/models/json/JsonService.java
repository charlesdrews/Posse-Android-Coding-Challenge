package com.charlesdrews.charlesdrewsdemoapp.personlist.models.json;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by charlie on 8/13/16.
 */
//@Generated("org.jsonschema2pojo")
public class JsonService {

//    @SerializedName("platform")
//    @Expose
    private String platform;

//    @SerializedName("programmers")
//    @Expose
    private List<JsonProgrammer> programmers = new ArrayList<JsonProgrammer>();

    /**
     *
     * @return
     * The platform
     */
    public String getPlatform() {
        return platform;
    }

    /**
     *
     * @param platform
     * The platform
     */
    public void setPlatform(String platform) {
        this.platform = platform;
    }

    /**
     *
     * @return
     * The programmers
     */
    public List<JsonProgrammer> getProgrammers() {
        return programmers;
    }

    /**
     *
     * @param programmers
     * The programmers
     */
    public void setProgrammers(List<JsonProgrammer> programmers) {
        this.programmers = programmers;
    }
}
