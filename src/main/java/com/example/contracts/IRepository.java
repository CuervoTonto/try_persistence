package com.example.contracts;

import com.example.estructuras.listas.ListaSimple;

public interface IRepository<E>
{
    /**
     * obtains all records from repo
     */
    public ListaSimple<E> all();

    /**
     * remove a record from repo
     */
    public void remove(E e);

    /**
     * add a record from repo
     */
    public void add(E e);
}