package com.example.simple_smart_city;

import com.example.simple_smart_city.bean_p.BannerBean;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {

    @GET("/prod-api/api/rotation/list?pageNum=1&pageSize=8&type=2")
    Call<BannerBean> banner();

}
