package com.tvd12.example.rxjava;

import io.reactivex.rxjava3.core.Single;

import java.util.HashMap;
import java.util.Map;

public class ZipExample {

    public static void main(String[] args) {
        System.out.println(
            sum(1, 2).blockingGet()
        );

        System.out.println(
            sum(null, -2).blockingGet()
        );
    }

    private static Single<Integer> sum(Integer a, Integer b) {
        return Single.zip(
            validateValue(a),
            validateValue(b),
            (errorsA, errorsB) -> {
                Map<String, String> errors = new HashMap<>();
                errors.putAll(errorsA);
                errors.putAll(errorsB);
                if (errors.isEmpty()) {
                    return a + b;
                }
                throw new IllegalArgumentException(errors.toString());
            });
    }

    private static Single<Map<String, String>> validateValue(Integer input) {
        return Single.fromCallable(() -> {
            Map<String, String> errors = new HashMap<>();
            if (input == null) {
                errors.put("input", "required");
            } else if (input < 0) {
                errors.put("input", "negative");
            }
            return errors;
        });
    }
}
