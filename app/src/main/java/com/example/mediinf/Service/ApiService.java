package com.example.mediinf.Service;

import com.example.mediinf.models.Enfermedades;
import com.example.mediinf.models.Medicamentos_gen;
import com.example.mediinf.models.Medicamentos_lab;
import com.example.mediinf.models.Medicamentos_nat;
import com.example.mediinf.models.User;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiService {

        String API_BASE_URL = "http://10.0.2.2:8090";

        @GET("/api/enfermedad/{id}")
       Call<Enfermedades> showEnfermedades(@Path("id") Long id);

        @GET("/api/enfermedades")
        Call<List<Enfermedades>> findAll();


         @GET("/api/medicamentos_gen/{id}")
         Call<Medicamentos_gen> showMedicamentos(@Path("id") Long id);

        @GET("/api/medicamentos_gen")
        Call<List<Medicamentos_gen>> findTodo();

         @GET("/api/medicamentos_lab/{id}")
         Call<Medicamentos_lab> showMedicamentosLab(@Path("id") Long id);


         @GET("/api/medicamentos_lab")
         Call<List<Medicamentos_lab>> findTodoLab();


        @GET("/api/medicamentos_nat/{id}")
        Call<Medicamentos_nat> showMedicamentosNat(@Path("id") Long id);

        @GET("/api/medicamentos_nat")
        Call<List<Medicamentos_nat>> findTodoNat();

         @FormUrlEncoded
         @POST("/api/login")
         Call<User> login(@Field("correo") String correo,
                     @Field("clave") String clave);


    @Multipart
    @POST("/api/usuarios")
    Call<User> createUsuario(@Part("nombres") RequestBody nombres,
                                 @Part("apellidos") RequestBody apellidos,
                                 @Part("dni") RequestBody dni,
                                 @Part("edad") RequestBody edad,
                                 @Part("correo") RequestBody correo,
                                 @Part("alergia") RequestBody alergia,
                                 @Part("clave") RequestBody clave
    );

    @FormUrlEncoded
    @POST("/api/usuarios")
    Call<User> createUsuario(@Field("nombres") String nombres,
                             @Field("apellidos") String apellidos,
                             @Field("dni") String dni,
                             @Field("edad") String edad,
                             @Field("correo")  String correo,
                             @Field("alergia") String alergia,
                             @Field("clave") String clave

    );


}
