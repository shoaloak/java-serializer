package com.axelkoolhaas.serializer;

//import data.Foo;
import data.productcatalog.ProductTemplate;

import java.io.*;
import java.util.Base64;

public class App
{
    /*
    public static void main(String[] args) throws Exception
    {
        Foo originalObject = new Foo("str", 123);

        String serializedObject = serialize(originalObject);

        System.out.println("Serialized object: " + serializedObject);

        Foo deserializedObject = deserialize(serializedObject);

        System.out.println("Deserialized data str: " + deserializedObject.str + ", num: " + deserializedObject.num);
    }
    */

    public static void main(String[] args) throws Exception
    {
        ProductTemplate productTemplate = new ProductTemplate("payload");

        String serializedObject = serialize(productTemplate);

        System.out.println("Serialized object: " + serializedObject);

        ProductTemplate deserializedObject = deserialize(serializedObject);

        System.out.println("Deserialized id str: " + deserializedObject.getId());
    }

    private static String serialize(Serializable obj) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(512);
        try (ObjectOutputStream out = new ObjectOutputStream(baos)) {
            out.writeObject(obj);
        }
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }

    private static <T> T deserialize(String base64SerializedObj) throws Exception {
        try (ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(Base64.getDecoder().decode(base64SerializedObj)))) {
            @SuppressWarnings("unchecked")
            T obj = (T) in.readObject();
            return obj;
        }
    }

}
