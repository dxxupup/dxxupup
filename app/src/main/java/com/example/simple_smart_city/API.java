package com.example.simple_smart_city;

import com.example.simple_smart_city.bean_p.BannerBean;
import com.example.simple_smart_city.bean_p.ProjectBean;
import com.example.simple_smart_city.bean_p.ServerListBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {

    @GET("/prod-api/api/rotation/list?pageNum=1&pageSize=8&type=2")
    Call<BannerBean> banner();

    @GET("/prod-api/api/service/list?sRecommend=Y")
    Call<ServerListBean> serverList();

    @GET("/prod-api/press/press/list")
    Call<ProjectBean> project();

    @GET("/prod-api/press/press/list")
    Call<ProjectBean> getNews(@Query("type") String id);
}
