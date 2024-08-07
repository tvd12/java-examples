package com.tvd12.example.aes;

import com.tvd12.ezyfox.security.EzyAsyCrypt;
import com.tvd12.ezyfox.security.EzyBase64;
import com.tvd12.ezyfox.security.EzySHA256;

public class PublicKeySecurityAlice {

    public static void main(String[] args) throws Exception {
        EzyAsyCrypt ezyAsyCrypt = EzyAsyCrypt.builder()
            .privateKey(EzyBase64.decode("MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCi8dFF+o+rgr4sFPQBGMJNBRz+PMFr238jeLakfbfu4M0nOakhnDupbWznrIiUNALubKxbe/l4SYPpxHBOvgtRW9oywNJ7nJGHAtcAIhmpVWrK8x6wkaQsubbpSIb85ptCgPz0PXu+EcW/+Vb+GxRxg7Cj9CydOmYWCkZF3S1PfHNXobK/Fsi5l5foYb0j1ZYBNFc1PFjfYobap3WmAN49hdtFgy3FN6NRJ71Aq9gmbWT/HzOc09EE4hCQ9eeQRWmrhiDuJTrd8RuUD8Icu15CMAqfG5pbntRjsGN7dF+yOvNoWnIM+fIwHzHV+ACCR9031VAN0Uwlf5LkSKJCrlAnAgMBAAECggEAGj9z457qF8m20qkJaUfQvNFvCzFEQxiXkWI3oaWyxGMoCQKL0SM2pSNAYM11LtJ2CmwtE4uT8yF8g2KmaLjKX++45WYUQd9FvgbiFV5o3dMpocmqDu1XEfFUmp2ZSRHovrzCgqGI1mRMg5Epx0MbdvGsuaxyp10p5rD/AvAPBXeWTCksGmmurll2xJOsEhrMCZzl8dgZaLQIBjDzIn5NBerUfiR8eEwJa0llfmFZHHA+3MQVWcoIU7kPSHJdDeYUmtBj/ppnQ5HZ1a3SXUftRo2oOXb/rB79fl7IHXMVHj184SFB9sUsWxiExQwocN9At36vHwX4WLgcgCdst9lsYQKBgQD92QDCjSdIppNP5aDlegNWH1bMMDqcQTk58aoJkO2zFZDNgAcdAGxmWp6NCghp8pM7Seb4ZdQuDfVihTpZ32hTBzdmpbLWcXbRYlbyZ7piDtvNI/lvKMAJMtpryRpbEbLIpP6eKrn5V17mMkmu93Xj3HhpBTVjaoaiNmuVm9ku0QKBgQCkU4CCsfokufIK/51NLlKfOHxKLuJgVGiS0MP3uS8EvWjJK7hiCI72Ew68EIvrxUp6e2kddsoK85fOn+eNyBEjtvrH++gHZ25Zbydc6IbNuigpCS43or2U3RJu/2lB0xO4DIP7dT7zVeenxeYB4e5hRXS/tVWsk0oY5v8lpWX9dwKBgQC4LIKVpQ30cw78b/FdrTnQeWpjOTa8+cGIXeLtj942CZaG0rXWf+EmHeddEwNpwxq5cHQIkvDv/SalNV5RqHqritfSPLRVS/tWDVBiDYo37Onim5A0607xHZlFjKH3ow6g7rzhisRluNNclu3I6Tf6e5JNN/81Qyutc2h5IprfkQKBgFwA2IHUTf5EEa1wwkyVM/MreAM8zsgqWkZhFmdbSmT3+3Quw1Q4AHrr3qMTDJQb6QA8mnTBrouFKSipxMZQDrCVYUGY26dRlKoEHuDY26jRomyfVD4YItb3E7wLyo3tNSwl7btbQLyTC1mW5pmFOqdBcQVo7Kfr61+X7aDRQxIvAoGBANXjxE9beApLa1sPAyxKyrzrLETBFKDQQtT8Szs0V5+1uQjT9kNfDUGS3rnfp9K4Ryr37VR1zSYAZB2OUP0sQOIyTpJOjUjBt0bcGQxuGzE923KjhFWwxXZac0R9lvyEp0S8XONTNgEqLX7SWPKGEKLQ+qgfhmIZ1dRHR2OFXvHu"))
            .build();
        String encryptedText = "YHMpxd2X6NEJmIZsXF3kZ7MyFwHGvh3mczDomDvsIKK3R/ak0/Fhof/hXUipPtxCZCTcjR1M3CFsOXcCfc9ccvHNVMfzTSQzXivvjU7dVmTiT+Nx1yt266BuDLQjEAZwik64LJXGnmcVuy3ID50q0l9J364e9e5dOlE+HzQ3t5Qm75hvdWHVK1LImIOs7SfAeeUOb/nfpI/p3VkKTvk2kfPt73NjDzDB2xl4CTvbmULToSc7FjVce3/l3bfirATtKaAeQXqCZ2C4wW6oR79+JxhLn4QAvR2gOL1x3GiEv+XU+1GZdkO9nh5p8nn6l5eGjRjgWFy1J84YhXr3c/1h+g==";
        byte[] decryptedData = ezyAsyCrypt.decrypt(encryptedText);
        System.out.println(new String(decryptedData));
        String hash = EzySHA256.cryptUtf(decryptedData);
        System.out.println(hash.equals("4735D6C2A518C0A694984D7576D97DD794D87B158C6E9773B090336F65AA0D0E"));
    }
}
