package pratik.com.retrofitmastersample.Model;

import com.google.gson.annotations.SerializedName;

public class IndiaResponse {

    @SerializedName("RestResponse")
    private RestResponse restResponse;

    public void setRestResponse(RestResponse restResponse) {
        this.restResponse = restResponse;
    }

    public RestResponse getRestResponse() {
        return restResponse;
    }

    @Override
    public String toString() {
        return
                "IndiaResponse{" +
                        "restResponse = '" + restResponse + '\'' +
                        "}";
    }
}