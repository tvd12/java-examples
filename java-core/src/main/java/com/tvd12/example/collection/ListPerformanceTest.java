package com.tvd12.example.collection;

import java.util.*;

public class ListPerformanceTest {
    public static void main(String[] args) {
        int count = 10_000_000;
        List<Object> arrayList = new ArrayList<>();
        long startArrayListAdd = System.currentTimeMillis();
        for(int i = 0 ; i < count ; ++i)
            arrayList.add(new Object());
        long endArrayListAdd = System.currentTimeMillis();

        List<Object> linkedList = new LinkedList<>();
        long startLinkedListAdd = System.currentTimeMillis();
        for(int i = 0 ; i < count ; ++i)
            linkedList.add(new Object());
        long endLinkedListAdd = System.currentTimeMillis();
        System.out.println("arrayList add time: " + (endArrayListAdd - startArrayListAdd));
        System.out.println("linkedList add time: " + (endLinkedListAdd - startLinkedListAdd));

        Object itemToRemoveInArrayList = arrayList.get(arrayList.size() / 2);
        long startArrayListRemove = System.currentTimeMillis();
        arrayList.remove(itemToRemoveInArrayList);
        long endArrayListRemove = System.currentTimeMillis();

        Object itemToRemoveInLinkedList = arrayList.get(linkedList.size() / 2);
        long startLinkedListRemove = System.currentTimeMillis();
        linkedList.remove(itemToRemoveInLinkedList);
        long endLinkedListRemove = System.currentTimeMillis();

        System.out.println("arrayList remove time: " + (endArrayListRemove - startArrayListRemove));
        System.out.println("linkedList remove time: " + (endLinkedListRemove - startLinkedListRemove));

        Set<Object> synchronizedSet = Collections.synchronizedSet(new HashSet<>());
        long startSynchronizedSetAdd = System.currentTimeMillis();
        for(int i = 0 ; i < count ; ++i)
            synchronizedSet.add(new Object());
        long endSynchronizedSetAdd = System.currentTimeMillis();
        System.out.println("synchronizedSet add time: " + (endSynchronizedSetAdd - startSynchronizedSetAdd));

        Object itemToRemoveInSet = new ArrayList<>(synchronizedSet).get(synchronizedSet.size() / 2);
        long startSetRemove = System.currentTimeMillis();
        synchronizedSet.remove(itemToRemoveInSet);
        long endSetRemove = System.currentTimeMillis();
        System.out.println("set remove time: " + (endSetRemove - startSetRemove));

        long startArrayListCopy = System.currentTimeMillis();
        new ArrayList<>(synchronizedSet);
        long endArrayListCopy = System.currentTimeMillis();

        List<Object> buffer = new ArrayList<>();
        for(int i = 0 ; i < count ; ++i)
            buffer.add(new Object());
        buffer.clear();
        long startBufferCopy = System.currentTimeMillis();
        buffer.addAll(synchronizedSet);
        long endBufferCopy = System.currentTimeMillis();

        System.out.println("arrayList copy time: " + (endArrayListCopy - startArrayListCopy));
        System.out.println("buffer copy time: " + (endBufferCopy - startBufferCopy));
    }
}
