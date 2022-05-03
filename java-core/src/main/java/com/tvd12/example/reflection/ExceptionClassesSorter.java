package com.tvd12.example.reflection;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class ExceptionClassesSorter {

    public static void main(String[] args) {
        List<Class<?>> list = new ArrayList<>();
        list.add(Ex2Branch2Exception.class);
        list.add(Ex2Branch1Exception.class);
        list.add(Ex1Branch2Exception.class);
        list.add(Ex1Branch1Exception.class);
        list.add(Branch2Exception.class);
        list.add(Branch1Exception.class);
        list.add(Exception.class);
        list.add(RootException.class);

        list.sort(new Compa());

        System.out.println(String.join("\n", list.stream()
            .map(t -> t.toString())
            .collect(Collectors.toList())));

        TreeMap<Class<?>, Class<?>> map = new TreeMap<>(new Compa());
        map.put(Ex2Branch2Exception.class, Ex2Branch2Exception.class);
        map.put(Ex2Branch2Exception.class, Ex2Branch2Exception.class);
        map.put(Ex2Branch2Exception.class, Ex2Branch2Exception.class);
        map.put(Ex1Branch1Exception.class, Ex1Branch1Exception.class);
        map.put(Branch2Exception.class, Branch2Exception.class);
        map.put(Branch1Exception.class, Branch1Exception.class);
        map.put(Exception.class, Exception.class);
        map.put(RootException.class, RootException.class);

        System.out.println("\n================================");
        System.out.println(String.join("\n", map.keySet().stream()
            .map(t -> t.toString())
            .collect(Collectors.toList())));

        System.out.println("\n================================");
        ExceptionTree tree = new ExceptionTree();
        tree.add(Ex2Branch2Exception.class);
        tree.add(Ex2Branch1Exception.class);
        tree.add(Ex1Branch2Exception.class);
        tree.add(Ex1Branch1Exception.class);
        tree.add(Branch2Exception.class);
        tree.add(Branch1Exception.class);
        tree.add(Exception.class);
        tree.add(RootException.class);
        System.out.println(tree);

        System.out.println("\n================================");
        System.out.println(String.join("\n", tree.toList().stream()
            .map(t -> t.toString())
            .collect(Collectors.toList())));
    }

    public static class Compa implements Comparator<Class<?>> {

        @Override
        public int compare(Class<?> a, Class<?> b) {
            if (a == b) {
                return 0;
            }
            if (a.isAssignableFrom(b)) {
                return 1;
            }
            if (b.isAssignableFrom(a)) {
                return -1;
            }
            return a.getName().compareTo(b.getName());
        }

    }

    public static class Ex2Branch2Exception extends Branch2Exception {
        private static final long serialVersionUID = -6068208036555261492L;
    }

    public static class Ex1Branch2Exception extends Branch2Exception {
        private static final long serialVersionUID = -6068208036555261492L;
    }

    public static class Ex2Branch1Exception extends Branch1Exception {
        private static final long serialVersionUID = -6068208036555261492L;
    }

    public static class Ex1Branch1Exception extends Branch1Exception {
        private static final long serialVersionUID = -6068208036555261492L;
    }

    public static class Branch2Exception extends RootException {
        private static final long serialVersionUID = -6068208036555261492L;
    }

    public static class Branch1Exception extends RootException {
        private static final long serialVersionUID = -6068208036555261492L;
    }

    public static class RootException extends Exception {
        private static final long serialVersionUID = -6068208036555261492L;
    }

}
