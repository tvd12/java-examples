package com.tvd12.example.aes;

import com.tvd12.ezyfox.security.EzyAesCrypt;
import com.tvd12.ezyfox.security.EzyBase64;

public class AliceAESEncrypt {

    public static void main(String[] args) throws Exception{
        EzyAesCrypt ezyAesCrypt = EzyAesCrypt.builder()
            .build();
        byte[] decryptedData = ezyAesCrypt.decrypt(
            EzyBase64.decode("vceywIbQWLsEYju9VPzTI+9PyxvNmfN0m4HXR5zOC+E="),
            EzyBase64.decode("velEHPXYROgXUT8wFdbYL4Guw5CVaxKnp/4lSwSyeV0=")
        );
        System.out.println(new String(decryptedData));
    }
}
