import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VK_API_Logic<VK_Oauth> {
    Map<String, String> api_info = new HashMap<>();
    private final static String baseVkUrl = "https://api.vk.com/method/";

    private VK_Oauth auth;
    private static Retrofit retrofit;

    public VK_API_Logic() {
        this.auth = new VK_Oauth();
        api_info.put("client_id", "*******");
        api_info.put("v", "5.131");
        api_info.put("fields", "photo_400, bdate");
        getRetrofit();
    }

    public VK_Oauth getAuth() {
        return auth;
    }

    public static Retrofit getRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseVkUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    public void getProfile(String token, MutableLiveData<UserResponse> userResponse){
        VK_API api = retrofit.create(VK_API.class);
        api.getUserInfo(api_info, token).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(@NonNull Call<UserResponse> call, @NonNull Response<UserResponse> response) {
                if(response.body() != null){
                    userResponse.setValue(response.body());
                    System.out.println(call.request());
                    System.out.println(userResponse.getValue().getResponse());
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserResponse> call, Throwable t) {
                Log.e(TAG, "Error VK Response " + t.getMessage());
            }
        });
    }
}
