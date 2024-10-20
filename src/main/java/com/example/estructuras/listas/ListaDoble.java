package com.example.estructuras.listas;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.example.estructuras.nodos.NodoDoble;

public class ListaDoble<E> implements Iterable<E>
{
    /**
     * el primer nodo (inicial)
     */
    protected NodoDoble<E> primero;

    /**
     * el ultimo nodo (final)
     */
    protected NodoDoble<E> ultimo;

    /**
     * cantidad de elementos en lista
     */
    protected int longitud;

    /**
     * construye una nueva instancia
     */
    public ListaDoble()
    {
        this.primero = null;
        this.ultimo = null;
        this.longitud = 0;
    }

    /**
     * agrega un elemento al final de la lista
     * 
     * @param e el elemento a agregar
     */
    public void agregar(E e)
    {
        this.agregar(longitud, e);
    }

    /**
     * agrega un elemento a la lista en la posicion dada
     * 
     * @param posicion la posicion para el elemento
     * @param e el elemento a agregar
     */
    public void agregar(int posicion, E e)
    {
        // validar la posicion
        if (! (posicion >= 0 && posicion <= longitud)) {
            throw new IndexOutOfBoundsException();
        }

        this.agregarEn(posicion, new NodoDoble<E>(e));
    }

    /**
     * remueve un nodo de una posicion dada
     * 
     * @param posicion la posicion del nodo a remover
     */
    public void remover(int posicion)
    {
        // validar la posicion
        if (! posicionValida(posicion)) {
            throw new NoSuchElementException();
        }

        this.removerNodo(posicion);
    }

    /**
     * obtiene un elemento de una posicion
     * 
     * @param posicion la posicion del elemento
     */
    public E obtener(int posicion)
    {
        // validar la posicion
        if (! posicionValida(posicion)) {
            throw new NoSuchElementException();
        }

        return this.obtenerNodo(posicion).dato;
    }

    /**
     * limpia la lista de elementos (los remueve todos)
     */
    public void limpiar()
    {
        this.primero = this.ultimo = null;
        this.longitud = 0;
    }

    /**
     * agrega un nodo en la primera posicion
     * 
     * @param nodo el nodo a agregar
     */
    private void agregarPrimero(NodoDoble<E> nodo)
    {
        // if (primero != null) {
        //     primero.setAnterior(nodo);
        // } else {
        //     ultimo = nodo;
        // }

        // nodo.setSiguiente(primero);
        // primero = nodo;

        if (longitud == 0) {
            primero = ultimo = nodo;
        } else {
            primero.setAnterior(nodo);
            nodo.setSiguiente(nodo);
            primero = nodo;
        }

        longitud++;
    }

    /**
     * agrega un nodo en la ultima posicion
     * 
     * @param nodo el nodo a agregar
     */
    private void agregarUltimo(NodoDoble<E> nodo)
    {
        // if (ultimo != null) {
        //     ultimo.setSiguiente(nodo);
        // } else {
        //     primero = nodo;
        // }

        // nodo.setAnterior(ultimo);
        // ultimo = nodo;

        if (longitud == 0) {
            primero = ultimo = nodo;
        } else {
            ultimo.setSiguiente(nodo);
            nodo.setAnterior(ultimo);
            ultimo = nodo;
        }

        longitud++;
    }

    /**
     * agrega un nodo en una dada posicion valida
     * 
     * @param posicion la posicion donde agregar el nodo
     * @param nodo el nodo a agregar
     */
    protected void agregarEn(int posicion, NodoDoble<E> nodo)
    {
        if (posicion == 0) {
            this.agregarPrimero(nodo);
        } else if (posicion == longitud) {
            this.agregarUltimo(nodo);
        } else {
            NodoDoble<E> pre = obtenerNodo(posicion - 1);

            nodo.setSiguiente(pre.siguiente());
            nodo.setAnterior(pre);
            pre.siguiente().setAnterior(nodo);
            pre.setSiguiente(nodo);

            longitud++;
        }
    }

    /**
     * remueve el primer nodo de la lista
     */
    private void removerPrimero()
    {
        primero.siguiente().setAnterior(primero.anterior());
        primero = primero.siguiente();

        longitud--;
    }

    /**
     * remueve el ultimo nodo de la lista
     */
    private void removerUltimo()
    {
        ultimo.anterior().setSiguiente(ultimo.siguiente());
        ultimo = ultimo.anterior();

        longitud--;
    }

    /**
     * remueve el nodo de una posicion valida
     * 
     * @param posicion la posicion del nodo
     */
    protected void removerNodo(int posicion)
    {
        if (posicion == 0) {
            removerPrimero();
        } else if (posicion == longitud - 1) {
            removerUltimo();
        } else {
            NodoDoble<E> pre = obtenerNodo(posicion - 1);

            pre.siguiente().siguiente().setAnterior(pre);
            pre.setSiguiente(pre.siguiente().siguiente());

            longitud--;
        }
    }

    /**
     * obtiene el nodo de una posicion valida
     * 
     * @param posicion la posicion del nodo
     * 
     * @return el nodo en la posicion
     */
    protected NodoDoble<E> obtenerNodo(int posicion)
    {
        NodoDoble<E> nodo = (longitud >> 1) < posicion
            ? this.obtenerNodoDerecha(posicion)
            : this.obtenerNodoIzquierda(posicion);

        return nodo;
    }

    /**
     * obtiene el nodo de una posicion contando desde el primer nodo
     * 
     * @param posicion la posicion del nodo
     * 
     * @return el nodo en la posicion dada
     */
    private NodoDoble<E> obtenerNodoIzquierda(int posicion)
    {
        NodoDoble<E> nodo = primero;

        for (int i = 0; i < posicion; i++) nodo = nodo.siguiente();

        return nodo;
    }

    /**
     * obtiene el nodo de una posicion contando desde el ultimo nodo
     * 
     * @param posicion la posicion del nodo
     * 
     * @return el nodo en la posicion dada
     */
    private NodoDoble<E> obtenerNodoDerecha(int posicion)
    {
        NodoDoble<E> nodo = ultimo;

        for (int i = longitud - 1; i > posicion; i--) nodo = nodo.anterior();

        return nodo;
    }

    /**
     * comprueba si una posicion es valida
     * 
     * @param posicion la posicion a comprobar
     * 
     * @return "true" si es valida o "false" en otro caso
     */
    public boolean posicionValida(int posicion)
    {
        return posicion >= 0 && posicion < longitud;
    }

    @Override
    public Iterator<E> iterator()
    {
        return new IteradorDoble(0);
    }

    public Iterator<E> iteradorInverso()
    {
        return new IteradorDobleInverso(this.longitud - 1);
    }

    /**
     * comprueba si no hay elementos
     * 
     * @return "true" si no hay elementos, "false" deo otra forma
     */
    public boolean estaVacia()
    {
        return longitud == 0;
    }

    /**
     * obtiene el primer nodo
     * 
     * @return primer nodo de la lista
     */
    protected NodoDoble<E> primero()
    {
        return this.primero;
    }

    /**
     * obtiene el ultimo nodo
     * 
     * @return ultimo nodo de la lista
     */
    protected NodoDoble<E> ultimo()
    {
        return this.ultimo;
    }

    /**
     * obtiene la cantidad de elementos
     * 
     * @return cantidad de elementos
     */
    public int longitud()
    {
        return this.longitud;
    }


    // inner classes

    private class IteradorDoble implements Iterator<E>
    {
        private NodoDoble<E> siguiente;
        private int indice;

        public IteradorDoble(int indice)
        {
            if (! posicionValida(indice)) throw new NoSuchElementException();

            this.siguiente = obtenerNodo(indice);
            this.indice = indice - 1;
        }

        @Override
        public boolean hasNext()
        {
            return this.indice < longitud - 1;
        }

        @Override
        public E next()
        {
            E dato = this.siguiente.dato;
            this.siguiente = this.siguiente.siguiente();
            this.indice++;

            return dato;
        }
    }

    private class IteradorDobleInverso implements Iterator<E>
    {
        private NodoDoble<E> siguiente;
        private int indice;

        public IteradorDobleInverso(int indice)
        {
            if (! posicionValida(indice)) throw new NoSuchElementException();

            this.siguiente = obtenerNodo(indice);
            this.indice = indice + 1;
        }

        @Override
        public boolean hasNext()
        {
            return this.indice > 0;
        }

        @Override
        public E next()
        {
            E dato = this.siguiente.dato;
            this.siguiente = this.siguiente.anterior();
            this.indice--;

            return dato;
        }
    }
}