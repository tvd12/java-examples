package com.tvd12.example.aes;

import com.tvd12.ezyfox.security.EzyAesCrypt;
import com.tvd12.ezyfox.security.EzyAsyCrypt;
import com.tvd12.ezyfox.security.EzyBase64;

import java.nio.charset.StandardCharsets;

public class BodRsaDecrypt {

    public static void main(String[] args) throws Exception {
        EzyAsyCrypt ezyAsyCrypt = EzyAsyCrypt.builder()
            .privateKey(EzyBase64.decode("MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCi8dFF+o+rgr4sFPQBGMJNBRz+PMFr238jeLakfbfu4M0nOakhnDupbWznrIiUNALubKxbe/l4SYPpxHBOvgtRW9oywNJ7nJGHAtcAIhmpVWrK8x6wkaQsubbpSIb85ptCgPz0PXu+EcW/+Vb+GxRxg7Cj9CydOmYWCkZF3S1PfHNXobK/Fsi5l5foYb0j1ZYBNFc1PFjfYobap3WmAN49hdtFgy3FN6NRJ71Aq9gmbWT/HzOc09EE4hCQ9eeQRWmrhiDuJTrd8RuUD8Icu15CMAqfG5pbntRjsGN7dF+yOvNoWnIM+fIwHzHV+ACCR9031VAN0Uwlf5LkSKJCrlAnAgMBAAECggEAGj9z457qF8m20qkJaUfQvNFvCzFEQxiXkWI3oaWyxGMoCQKL0SM2pSNAYM11LtJ2CmwtE4uT8yF8g2KmaLjKX++45WYUQd9FvgbiFV5o3dMpocmqDu1XEfFUmp2ZSRHovrzCgqGI1mRMg5Epx0MbdvGsuaxyp10p5rD/AvAPBXeWTCksGmmurll2xJOsEhrMCZzl8dgZaLQIBjDzIn5NBerUfiR8eEwJa0llfmFZHHA+3MQVWcoIU7kPSHJdDeYUmtBj/ppnQ5HZ1a3SXUftRo2oOXb/rB79fl7IHXMVHj184SFB9sUsWxiExQwocN9At36vHwX4WLgcgCdst9lsYQKBgQD92QDCjSdIppNP5aDlegNWH1bMMDqcQTk58aoJkO2zFZDNgAcdAGxmWp6NCghp8pM7Seb4ZdQuDfVihTpZ32hTBzdmpbLWcXbRYlbyZ7piDtvNI/lvKMAJMtpryRpbEbLIpP6eKrn5V17mMkmu93Xj3HhpBTVjaoaiNmuVm9ku0QKBgQCkU4CCsfokufIK/51NLlKfOHxKLuJgVGiS0MP3uS8EvWjJK7hiCI72Ew68EIvrxUp6e2kddsoK85fOn+eNyBEjtvrH++gHZ25Zbydc6IbNuigpCS43or2U3RJu/2lB0xO4DIP7dT7zVeenxeYB4e5hRXS/tVWsk0oY5v8lpWX9dwKBgQC4LIKVpQ30cw78b/FdrTnQeWpjOTa8+cGIXeLtj942CZaG0rXWf+EmHeddEwNpwxq5cHQIkvDv/SalNV5RqHqritfSPLRVS/tWDVBiDYo37Onim5A0607xHZlFjKH3ow6g7rzhisRluNNclu3I6Tf6e5JNN/81Qyutc2h5IprfkQKBgFwA2IHUTf5EEa1wwkyVM/MreAM8zsgqWkZhFmdbSmT3+3Quw1Q4AHrr3qMTDJQb6QA8mnTBrouFKSipxMZQDrCVYUGY26dRlKoEHuDY26jRomyfVD4YItb3E7wLyo3tNSwl7btbQLyTC1mW5pmFOqdBcQVo7Kfr61+X7aDRQxIvAoGBANXjxE9beApLa1sPAyxKyrzrLETBFKDQQtT8Szs0V5+1uQjT9kNfDUGS3rnfp9K4Ryr37VR1zSYAZB2OUP0sQOIyTpJOjUjBt0bcGQxuGzE923KjhFWwxXZac0R9lvyEp0S8XONTNgEqLX7SWPKGEKLQ+qgfhmIZ1dRHR2OFXvHu"))
            .build();
        String encryptedText = "D0DvzQfGg0L04v9D9TD0YLdkH1yQC4yGOUAE4nGviikOCoGxY0YeC28z6SsgXNJ5hFduE1eEuSI1ka+LhM6/gOJO4lDrS+xIg+O6e7GThpmoG4X3fqjy7JEv3hqX8OGAtSemBlmeq1DtoGi5CeqBSMjrqlmm4mGS+84Uc2UKSE1yORaOVS2gglvMbt1J1s/JGrzesx03Wdau1hD9oo3mrtBCbx7Z5y0ZYtBvf90n6Mcphx42K1ClkPDRDAdtoibTY83Yl7zMU5W4k1xWzJgBmKB1XkPp/cpI1Qq9A+OTy32v6EwqUnXUI2TXnfhHbbvUffl4yqJNeFh3e1sbuAF4wQ==";
        byte[] decryptedKey = ezyAsyCrypt.decrypt(EzyBase64.decode(encryptedText));
        System.out.println(new String(decryptedKey));

        String text = "TextMaster";
        EzyAesCrypt ezyAesCrypt = EzyAesCrypt.builder()
            .build();
        byte[] encryptedData = ezyAesCrypt.encrypt(
            text.getBytes(StandardCharsets.UTF_8),
            decryptedKey
        );
        System.out.println(EzyBase64.encode2utf(encryptedData));
    }
}
