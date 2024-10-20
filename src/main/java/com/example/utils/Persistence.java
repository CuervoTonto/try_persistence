package com.example.utils;

import java.beans.Encoder;
import java.beans.Expression;
import java.beans.PersistenceDelegate;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import com.example.contracts.IModel;
import com.example.estructuras.listas.ListaSimple;

final public class Persistence
{
    private Persistence() {};

    public static void write(String path, String content)
    {
        try {
            // Files.write(new File(path).toPath(), lines, StandardCharsets.UTF_8);
            Files.writeString(new File(path).toPath(), content, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Files.writeString(new File(path).getPath(), lines);
    }

    public static List<String> read(String path)
    {
        try {
            return Files.readAllLines(FileSystems.getDefault().getPath(path));
        } catch (IOException e) {
            return null;
        }
    }

    public static <E extends IModel<E>> void writeFromModel(String path, ListaSimple<E> lista)
    {
        String[] lineas = new String[lista.longitud()];
        Iterator<E> it = lista.iterator();
        int indice = 0;

        while (indice < lineas.length) {
            lineas[indice] = String.join("@@", it.next().toPersistence());
            indice++;
        }

        Persistence.write(path, String.join("\n", lineas));
    }

    public static <E extends IModel<E>> ListaSimple<E> readForModel(String path, Class<E> model)
    {
        ListaSimple<E> lista = new ListaSimple<>();

        try {
            Constructor<E> constr = model.getConstructor();
            Object[] content = Files.lines(new File(path).toPath()).toArray();

            for (Object line : content) {
                E inst = constr.newInstance();
                inst.fromPersistence(((String) line).split("@@"));
                lista.agregar(inst);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return lista;
    }


    public static Stream<String> readStream(String path)
    {
        try {
            return Files.lines(FileSystems.getDefault().getPath(path));
        } catch (IOException e) {
            return null;
        }
    }

    public static void writeXML(String path, Object object)
    {
        try (XMLEncoder encoder = makeEncoder(path)) {
            encoder.writeObject(object);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Object readXML(String path)
    {
        if (! new File(path).exists()) {
            Persistence.writeXML(path, null);
        }

        try (XMLDecoder dec = new XMLDecoder(new FileInputStream(path))) {
            return dec.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static XMLEncoder makeEncoder(String path) throws FileNotFoundException
    {
        XMLEncoder encoder = new XMLEncoder(new FileOutputStream(path));
        
        encoder.setPersistenceDelegate(ListaSimple.class, new PersistenceDelegate() {
            @Override
            protected Expression instantiate(Object oldInstance, Encoder out) {
                ListaSimple<?> inst = (ListaSimple<?>) oldInstance;

                return new Expression(
                    new ListaSimple<>(),
                    inst,
                    "agregar",
                    inst.toArray()
                );
            }
        });

        return encoder;
    }
}