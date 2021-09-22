package cl.tofcompany.drivindesafio.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MainData {
    @SerializedName("message")
    List<String> message;
    @SerializedName("status")
    String status;
    @SerializedName("hound")
    String houndafghan;

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHoundafghan() {
        return houndafghan;
    }

    public void setHoundafghan(String houndafghan) {
        this.houndafghan = houndafghan;
    }

    @Override
    public String toString() {
        return "MainData{" +
                "message=" + message +
                ", status='" + status + '\'' +
                ", houndafghan='" + houndafghan + '\'' +
                '}';
    }
}
