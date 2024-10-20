package com.example.persistences;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import com.example.contracts.IRepository;
import com.example.estructuras.listas.ListaSimple;
import com.example.models.User;
import com.example.utils.Persistence;

public class UserPersistence implements IRepository<User>
{
    public String filePath;

    public UserPersistence(String filePath) throws IOException
    {
        this.filePath = filePath;
        File file = new File(filePath);

        if (! file.exists()) {
            if (! file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            file.createNewFile();
        }
    }

    @Override
    public ListaSimple<User> all()
    {
        return read();
    }

    @Override
    public void add(User e)
    {
        ListaSimple<User> list = all();
        boolean existe = false;

        if (! list.estaVacia()) {
            Iterator<User> it = list.iterator();

            while (it.hasNext() && ! existe) {
                existe = it.next().getEmail().equalsIgnoreCase(e.getEmail());
            }
        }

        if (! existe) {
            list.agregar(e);
            write(list);
        }
    }

    @Override
    public void remove(User e)
    {
        ListaSimple<User> list = all();
        list.remover(e);
        write(list);
    }

    private void write(ListaSimple<User> datos)
    {
        Persistence.writeFromModel(filePath, datos);
    }

    private ListaSimple<User> read()
    {
        return Persistence.readForModel(filePath, User.class);
    }
}