package cl.tofcompany.drivindesafio.Retrofit;

import cl.tofcompany.drivindesafio.response.MainData;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MainInterface {
    @GET("hound/images/random/10")
    Call<MainData> getResponse();
}
