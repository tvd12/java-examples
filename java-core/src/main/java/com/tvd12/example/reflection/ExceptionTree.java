package com.tvd12.example.reflection;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExceptionTree {

    protected List<Node> roots = new ArrayList<>();

    public void add(Class<?> exceptionClass) {
        add(exceptionClass, roots);
    }

    public void add(Class<?> exceptionClass, List<Node> existedNodes) {
        if (existedNodes.isEmpty()) {
            existedNodes.add(new Node(exceptionClass));
            return;
        }
        for (Node node : existedNodes) {
            if (node.clazz.equals(exceptionClass)) {
                return;
            }
        }
        List<Node> children = new ArrayList<>();
        for (Node node : existedNodes) {
            if (exceptionClass.isAssignableFrom(node.clazz)) {
                children.add(node);
            }
        }
        if (children.size() > 0) {
            Node newRootNode = new Node(exceptionClass, children);
            existedNodes.removeAll(children);
            existedNodes.add(newRootNode);
            return;
        }
        for (Node node : existedNodes) {
            if (node.clazz.isAssignableFrom(exceptionClass)) {
                add(exceptionClass, node.children);
                return;
            }
        }
        existedNodes.add(new Node(exceptionClass));
    }

    public List<Class<?>> toList() {
        List<Class<?>> list = new ArrayList<>();
        for (Node root : roots) {
            root.appendToList(list);
        }
        return list;
    }

    @Override
    public String toString() {
        return String.join("\n", roots.stream()
            .map(t -> t.toString())
            .collect(Collectors.toList()));
    }

    private static class Node {
        protected Class<?> clazz;
        protected List<Node> children = new ArrayList<>();

        private Node(Class<?> clazz) {
            this.clazz = clazz;
        }

        private Node(Class<?> clazz, List<Node> children) {
            this.clazz = clazz;
            this.children.addAll(children);
        }

        private void appendToList(List<Class<?>> list) {
            for (Node child : children) {
                child.appendToList(list);
            }
            list.add(clazz);
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder()
                .append(clazz.getName());
            if (children.size() > 0) {
                builder.append(" => ").append(children);
            }
            return builder.toString();
        }
    }

}
