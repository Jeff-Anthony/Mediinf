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

    static {
        users.add(new User("Jeff Anthony"," Llanos Orihuela", "75692456","jeffllanos21@gmail.com", "Polvo","jeffllanos"));
    }

    public static User Login(String Correo, String Contraseña){

        for(User user : users){

            if(user.getCorreo().equalsIgnoreCase(Correo) && user.getContraseña().equals(Contraseña)){

                return user;

            }

        }
        return null;
    }

    public static void create(String nombre, String apellido, String dni, String correo, String alergia, String contraseña){
        User user = new User(nombre, apellido, dni, correo, alergia, contraseña);
        SugarRecord.save(user);
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
