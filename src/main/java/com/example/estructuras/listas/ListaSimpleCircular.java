package com.example.estructuras.listas;

import java.util.Iterator;

import com.example.estructuras.nodos.NodoSimple;

public class ListaSimpleCircular<E> extends ListaSimple<E>
{
    @Override
    public void agregar(int posicion, E e)
    {
        this.agregarEn(posicion, new NodoSimple<E>(e, primero()));
    }

    public Iterator<E> iteradorCircular(int inicio)
    {
        return new IteradorCircular(inicio);
    }

    private class IteradorCircular implements Iterator<E>
    {
        private NodoSimple<E> inicio;
        private NodoSimple<E> actual;

        /**
         * contador para los recorridos restantes
         */
        private int recorridos;

        public IteradorCircular(int inicio, int reccoridos)
        {
            this.inicio = obtenerNodo(inicio);
            this.actual = this.inicio;
            this.recorridos = reccoridos;
        }

        public IteradorCircular(int inicio)
        {
            this(inicio, 1);
        }

        public boolean tieneRecorridos()
        {
            return this.recorridos > 0;
        }

        @Override
        public boolean hasNext()
        {
            return this.tieneRecorridos() || this.actual != this.inicio;
        }

        @Override
        public E next()
        {
            if (this.actual.equals(this.inicio)) {
                this.recorridos -= 1;
            }

            E elemento = this.actual.dato;
            this.actual = this.actual.siguiente();

            return elemento;
        }
    }
}