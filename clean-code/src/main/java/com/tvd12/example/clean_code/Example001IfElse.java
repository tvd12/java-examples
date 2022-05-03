package com.tvd12.example.clean_code;

import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Example001IfElse {

    public static void main(String[] args) {
        MySuccessHandlerClean handler = new MySuccessHandlerClean();
        System.out.println(handler.determineTargetUrl());
    }

    public static enum Role {
        MANAGER("/manager"),
        ADMIN("/admin"),
        USER("/user");

        @Getter
        String targetURL;

        private Role(String targetURL) {
            this.targetURL = targetURL;
        }
    }

    private static class MySuccessHandler {
        public String determineTargetUrl(Authentication authentication) {
            String url = "";
            List<String> roles = SecurityUtils.getAuthorities();
            if (isManager(roles)) {
                url = "/manager";
            } else if (isAdmin(roles)) {
                url = "/admin";
            } else if (isUser(roles)) {
                url = "/user";
            }
            return url;
        }

        private boolean isUser(List<String> roles) {
            return roles.contains("USER");
        }

        private boolean isAdmin(List<String> roles) {
            return roles.contains("ADMIN");
        }

        private boolean isManager(List<String> roles) {
            return roles.contains("MANAGER");
        }
    }

    private static class MySuccessHandlerClean {
        public String determineTargetUrl() {
            return SecurityUtils.getAuthorities()
                .stream()
                .map(Role::valueOf)
                .sorted()
                .findFirst().get().getTargetURL();
        }
    }

    public static class SecurityUtils {

        public static List<String> getAuthorities() {
            return Collections.singletonList("USER");
        }
    }

    public static class Authentication {
    }
}
