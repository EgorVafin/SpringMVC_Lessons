package app.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) {

        Person person = new Person();

        Class clazz = person.getClass();
        System.out.println(clazz.getName());

        System.out.println("-----------------------");
        Field[] fields = clazz.getDeclaredFields();
        for( Field f: fields) {
            System.out.println(f);
        }

        System.out.println("-----------------------");
        Method[] methods = clazz.getDeclaredMethods();
        for( Method m: methods) {
            System.out.println(m);
        }

        System.out.println("-----------------------");
        try {
            Field field = clazz.getDeclaredField("password");
            field.setAccessible(true);

            System.out.println(field.get(person));

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        System.out.println("-----------------------");
        try {
            Field field = clazz.getDeclaredField("password");
            field.setAccessible(true);

            System.out.println(field.get(person));
            field.set(person, "newSuperPassword");
            System.out.println(field.get(person));


        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }




    }
}
