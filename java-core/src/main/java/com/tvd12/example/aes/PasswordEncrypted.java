package com.tvd12.example.aes;

import com.tvd12.ezyfox.security.BCrypt;
import com.tvd12.ezyfox.security.EzySHA256;

public class PasswordEncrypted {

    public static void main(String[] args) {
        String salt = BCrypt.gensalt();
        String ecyptedPassword = BCrypt.hashpw("Techmaster", salt);
        System.out.println(ecyptedPassword);
        boolean correct = BCrypt.checkpw(
            "Techmaster",
            ecyptedPassword
        );
        System.out.println(correct);
        String hash = EzySHA256.cryptUtf("Techmaster");
        System.out.println(hash);
    }
}
