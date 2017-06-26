package comics.com.app.data.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by alessandro.candolini on 26/06/2017.
 */

public class Envelop<T> {

    @SerializedName("code")
    private String code;

    @SerializedName("status")
    private String status;

    @SerializedName("data")
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
