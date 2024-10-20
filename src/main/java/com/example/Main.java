package com.example;

import com.example.estructuras.listas.ListaSimple;
import com.example.models.User;
import com.example.persistences.UserPersistence;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        System.out.println("--------------- Users on list ----------------");
        readUsers();
        System.out.println("---------------------------------------------- \n");

        addUsers();

        System.out.println("------------ Users on list (added) -----------");
        readUsers();
        System.out.println("---------------------------------------------- \n");

        removeUser();

        System.out.println("--------------- Users on list ----------------");
        readUsers();
        System.out.println("---------------------------------------------- \n");
    }

    private static void readUsers() throws Exception
    {
        UserPersistence pers = new UserPersistence(
            System.getProperty("user.dir") + "/src/main/resources/users.txt"
        );

        ListaSimple<User> users = pers.all();

        if (! users.estaVacia()) {
            pers.all().forEach((u) -> {
                System.out.println(u.getEmail() + " => " + u.getPassword());
            });
        }
    }

    private static void addUsers() throws Exception
    {
        UserPersistence pers = new UserPersistence(
            System.getProperty("user.dir") + "/src/main/resources/users.txt"
        );

        pers.add(new User("example@test.com", "123"));
        pers.add(new User("example@test.com", "124"));
        pers.add(new User("example2@test.com", "124"));
        pers.add(new User("example5@test.com", "124"));
    }

    private static void removeUser() throws Exception
    {
        UserPersistence pers = new UserPersistence(
            System.getProperty("user.dir") + "/src/main/resources/users.txt"
        );

        pers.remove(new User("example2@test.com", "124"));
    }
}