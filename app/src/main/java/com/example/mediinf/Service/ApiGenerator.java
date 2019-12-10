package com.example.mediinf.Service;

import android.content.Context;

import com.example.mediinf.BuildConfig;
import com.example.mediinf.models.ApiError;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.lang.annotation.Annotation;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiGenerator {

    private  static Retrofit retrofit;

    private static Picasso picasso;

    public static Picasso createPicasso(final Context context) {
        if(picasso == null) {

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

            httpClient.readTimeout(60, TimeUnit.SECONDS).connectTimeout(60, TimeUnit.SECONDS);

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(logging);


            picasso = new Picasso.Builder(context)
                    .downloader(new OkHttp3Downloader(httpClient.build()))
                    .build();
        }
        return picasso;
    }


    public  static <S> S createService(Class<S> serviceClass){


        if (retrofit == null){

            OkHttpClient.Builder httclient = new OkHttpClient.Builder();

            httclient.readTimeout(60, TimeUnit.SECONDS).connectTimeout(60, TimeUnit.SECONDS);

            if (BuildConfig.DEBUG){

                httclient.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));

            }

            retrofit = new Retrofit.Builder().baseUrl(ApiService.API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httclient.build()) // Esto es Adicional
                    .build();

        }
        return  retrofit.create(serviceClass);
    }

    public static ApiError parseError (Response<?> response){

        try{
            Converter<ResponseBody, ApiError> converter = retrofit.responseBodyConverter(ApiError.class, new Annotation[0]);
            return converter.convert(response.errorBody());


        }catch (Exception e){

            return new ApiError("Error  desconocido del servicio");

        }

    }
}
