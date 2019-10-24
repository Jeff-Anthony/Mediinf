package com.example.mediinf.repositories;

import com.example.mediinf.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User("Jeff Anthony"," Llanos Orihuela", 75692456,"jeffllanos21@gmail.com", "Polvo","jeffllanos"));
    }

    public static User Login(String Correo, String Contraseña){

        for(User user : users){

            if(user.getCorreo().equalsIgnoreCase(Correo) && user.getContraseña().equals(Contraseña)){

                return user;

            }

        }
        return null;
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
