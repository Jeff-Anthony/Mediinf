package com.example.mediinf.repositories;

import com.example.mediinf.models.User;
import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private static List<User> users = new ArrayList<>();
    public static List<User> list(){
        List<User> users = SugarRecord.listAll(User.class);
        return users;
    }





    public static User findByCorreo(String Correo){

        for(User user : users){

            if(user.getCorreo().equalsIgnoreCase(Correo)){

                return user;

            }

        }
        return null;

    }

}
