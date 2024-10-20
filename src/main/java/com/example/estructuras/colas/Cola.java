package com.example.estructuras.colas;

import java.util.NoSuchElementException;

import com.example.estructuras.nodos.NodoSimple;

public class Cola<E>
{
    /**
     * primero elemento en la cola
     */
    private NodoSimple<E> primero;

    /**
     * ultimo elemento en la cola
     */
    private NodoSimple<E> ultimo;

    /**
     * longitud de la cola
     */
    private int longitud;

    /**
     * agrega un elemento al final de la cla
     * 
     * @param elemento
     */
    public void encolar(E elemento)
    {
        if (this.estaVacia()) {
            primero = ultimo = new NodoSimple<E>(elemento);
        } else {
            NodoSimple<E> nodo = new NodoSimple<E>(elemento);
            ultimo.setSiguiente(nodo);
            ultimo = nodo;
        }

        this.longitud++;
    }

    /**
     * obtiene y remueve el primer elemento de la cola
     * 
     * @return primer elemento en la cola
     * 
     * @throws NoSuchElementException si no hay elementos en la cola
     */
    public E desencolar()
    {
        if (this.estaVacia()) {
            throw new NoSuchElementException();
        }

        E elemento = primero.dato;
        primero = primero.siguiente();
        longitud--;

        return elemento;
    }

    /**
     * obtiene el primer elemento de la cola
     * 
     * @return primer elemento en la cola
     * 
     * @throws NoSuchElementException si no hay elementos en la cola
     */
    public E elemento()
    {
        if (this.estaVacia()) {
            throw new NoSuchElementException();
        }

        return primero.dato;
    }

    /**
     * comprueba si la cola se encuentra vacia
     */
    public boolean estaVacia()
    {
        return this.longitud == 0;
    }

    /**
     * comprueba si la cola no esta vacia
     */
    public boolean noEstaVacia()
    {
        return this.longitud > 0;
    }

    /**
     * obtiene la longitud de la cola
     * 
     * @return cantidad de elementos en la cola
     */
    public int longitud()
    {
        return this.longitud;
    }

    @Override
    protected Cola<E> clone() throws CloneNotSupportedException
    {
        Cola<E> nueva = new Cola<E>();
        NodoSimple<E> aux = primero; 

        while (aux != null) {
            nueva.encolar(aux.dato);
            aux = aux.siguiente();
        }

        return nueva;
    }
}