package com.charlesdrews.charlesdrewsdemoapp.personlist.models.json;

/**
 * Created by charlie on 8/13/16.
 */
//@Generated("org.jsonschema2pojo")
public class JsonRoot {

//    @SerializedName("status")
//    @Expose
    private int status;

//    @SerializedName("response")
//    @Expose
    private JsonResponse response;

    /**
     *
     * @return
     * The status
     */
    public int getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     *
     * @return
     * The response
     */
    public JsonResponse getResponse() {
        return response;
    }

    /**
     *
     * @param response
     * The response
     */
    public void setResponse(JsonResponse response) {
        this.response = response;
    }
}
