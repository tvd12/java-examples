package com.tvd12.example.aes;

import com.tvd12.ezyfox.security.EzyAsyCrypt;
import com.tvd12.ezyfox.security.EzyBase64;
import com.tvd12.ezyfox.security.EzySHA256;
import lombok.AllArgsConstructor;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class DigitalCentral {

    @AllArgsConstructor
    public static class Message {
        private String publicKey;
        private String message;
        private String signature;
    }

    public static void main(String[] args) throws Exception {
        Map<String, String> userByPublicKey = new HashMap<>();
        userByPublicKey.put(
            "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCi8dFF+o+rgr4sFPQBGMJNBRz+PMFr238jeLakfbfu4M0nOakhnDupbWznrIiUNALubKxbe/l4SYPpxHBOvgtRW9oywNJ7nJGHAtcAIhmpVWrK8x6wkaQsubbpSIb85ptCgPz0PXu+EcW/+Vb+GxRxg7Cj9CydOmYWCkZF3S1PfHNXobK/Fsi5l5foYb0j1ZYBNFc1PFjfYobap3WmAN49hdtFgy3FN6NRJ71Aq9gmbWT/HzOc09EE4hCQ9eeQRWmrhiDuJTrd8RuUD8Icu15CMAqfG5pbntRjsGN7dF+yOvNoWnIM+fIwHzHV+ACCR9031VAN0Uwlf5LkSKJCrlAnAgMBAAECggEAGj9z457qF8m20qkJaUfQvNFvCzFEQxiXkWI3oaWyxGMoCQKL0SM2pSNAYM11LtJ2CmwtE4uT8yF8g2KmaLjKX++45WYUQd9FvgbiFV5o3dMpocmqDu1XEfFUmp2ZSRHovrzCgqGI1mRMg5Epx0MbdvGsuaxyp10p5rD/AvAPBXeWTCksGmmurll2xJOsEhrMCZzl8dgZaLQIBjDzIn5NBerUfiR8eEwJa0llfmFZHHA+3MQVWcoIU7kPSHJdDeYUmtBj/ppnQ5HZ1a3SXUftRo2oOXb/rB79fl7IHXMVHj184SFB9sUsWxiExQwocN9At36vHwX4WLgcgCdst9lsYQKBgQD92QDCjSdIppNP5aDlegNWH1bMMDqcQTk58aoJkO2zFZDNgAcdAGxmWp6NCghp8pM7Seb4ZdQuDfVihTpZ32hTBzdmpbLWcXbRYlbyZ7piDtvNI/lvKMAJMtpryRpbEbLIpP6eKrn5V17mMkmu93Xj3HhpBTVjaoaiNmuVm9ku0QKBgQCkU4CCsfokufIK/51NLlKfOHxKLuJgVGiS0MP3uS8EvWjJK7hiCI72Ew68EIvrxUp6e2kddsoK85fOn+eNyBEjtvrH++gHZ25Zbydc6IbNuigpCS43or2U3RJu/2lB0xO4DIP7dT7zVeenxeYB4e5hRXS/tVWsk0oY5v8lpWX9dwKBgQC4LIKVpQ30cw78b/FdrTnQeWpjOTa8+cGIXeLtj942CZaG0rXWf+EmHeddEwNpwxq5cHQIkvDv/SalNV5RqHqritfSPLRVS/tWDVBiDYo37Onim5A0607xHZlFjKH3ow6g7rzhisRluNNclu3I6Tf6e5JNN/81Qyutc2h5IprfkQKBgFwA2IHUTf5EEa1wwkyVM/MreAM8zsgqWkZhFmdbSmT3+3Quw1Q4AHrr3qMTDJQb6QA8mnTBrouFKSipxMZQDrCVYUGY26dRlKoEHuDY26jRomyfVD4YItb3E7wLyo3tNSwl7btbQLyTC1mW5pmFOqdBcQVo7Kfr61+X7aDRQxIvAoGBANXjxE9beApLa1sPAyxKyrzrLETBFKDQQtT8Szs0V5+1uQjT9kNfDUGS3rnfp9K4Ryr37VR1zSYAZB2OUP0sQOIyTpJOjUjBt0bcGQxuGzE923KjhFWwxXZac0R9lvyEp0S8XONTNgEqLX7SWPKGEKLQ+qgfhmIZ1dRHR2OFXvHu",
            "Techmaster"
        );
        Message message = new Message(
            "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCi8dFF+o+rgr4sFPQBGMJNBRz+PMFr238jeLakfbfu4M0nOakhnDupbWznrIiUNALubKxbe/l4SYPpxHBOvgtRW9oywNJ7nJGHAtcAIhmpVWrK8x6wkaQsubbpSIb85ptCgPz0PXu+EcW/+Vb+GxRxg7Cj9CydOmYWCkZF3S1PfHNXobK/Fsi5l5foYb0j1ZYBNFc1PFjfYobap3WmAN49hdtFgy3FN6NRJ71Aq9gmbWT/HzOc09EE4hCQ9eeQRWmrhiDuJTrd8RuUD8Icu15CMAqfG5pbntRjsGN7dF+yOvNoWnIM+fIwHzHV+ACCR9031VAN0Uwlf5LkSKJCrlAnAgMBAAECggEAGj9z457qF8m20qkJaUfQvNFvCzFEQxiXkWI3oaWyxGMoCQKL0SM2pSNAYM11LtJ2CmwtE4uT8yF8g2KmaLjKX++45WYUQd9FvgbiFV5o3dMpocmqDu1XEfFUmp2ZSRHovrzCgqGI1mRMg5Epx0MbdvGsuaxyp10p5rD/AvAPBXeWTCksGmmurll2xJOsEhrMCZzl8dgZaLQIBjDzIn5NBerUfiR8eEwJa0llfmFZHHA+3MQVWcoIU7kPSHJdDeYUmtBj/ppnQ5HZ1a3SXUftRo2oOXb/rB79fl7IHXMVHj184SFB9sUsWxiExQwocN9At36vHwX4WLgcgCdst9lsYQKBgQD92QDCjSdIppNP5aDlegNWH1bMMDqcQTk58aoJkO2zFZDNgAcdAGxmWp6NCghp8pM7Seb4ZdQuDfVihTpZ32hTBzdmpbLWcXbRYlbyZ7piDtvNI/lvKMAJMtpryRpbEbLIpP6eKrn5V17mMkmu93Xj3HhpBTVjaoaiNmuVm9ku0QKBgQCkU4CCsfokufIK/51NLlKfOHxKLuJgVGiS0MP3uS8EvWjJK7hiCI72Ew68EIvrxUp6e2kddsoK85fOn+eNyBEjtvrH++gHZ25Zbydc6IbNuigpCS43or2U3RJu/2lB0xO4DIP7dT7zVeenxeYB4e5hRXS/tVWsk0oY5v8lpWX9dwKBgQC4LIKVpQ30cw78b/FdrTnQeWpjOTa8+cGIXeLtj942CZaG0rXWf+EmHeddEwNpwxq5cHQIkvDv/SalNV5RqHqritfSPLRVS/tWDVBiDYo37Onim5A0607xHZlFjKH3ow6g7rzhisRluNNclu3I6Tf6e5JNN/81Qyutc2h5IprfkQKBgFwA2IHUTf5EEa1wwkyVM/MreAM8zsgqWkZhFmdbSmT3+3Quw1Q4AHrr3qMTDJQb6QA8mnTBrouFKSipxMZQDrCVYUGY26dRlKoEHuDY26jRomyfVD4YItb3E7wLyo3tNSwl7btbQLyTC1mW5pmFOqdBcQVo7Kfr61+X7aDRQxIvAoGBANXjxE9beApLa1sPAyxKyrzrLETBFKDQQtT8Szs0V5+1uQjT9kNfDUGS3rnfp9K4Ryr37VR1zSYAZB2OUP0sQOIyTpJOjUjBt0bcGQxuGzE923KjhFWwxXZac0R9lvyEp0S8XONTNgEqLX7SWPKGEKLQ+qgfhmIZ1dRHR2OFXvHu",
            "Techmaster",
            "MhGbicASGbLaiVbV10mAuQD5on5ATiDWBxuP2zpPdEo7gv/NcX8u8GSM/derbyyxcayFErrjsZ5XqCcuqFUZRSl/+1KrbsI91seqGLA21WSS30CHctb9YZkjUtCH9ME8AVCaQIC6QPGDOGH+a+4z9RpUSr/CnK4qX6MjT4CYiUNyocUvPwfDqiktCxNslRVVI6ohh+64UeNvVV7a2DIVIqI2/PjhnG9G0JioKs5piCPZLA9kUJDAaxh5mMm+j5pC1k6wFDN0dVat3Cyw4xBn+wINfODxHf77gapJz1OZNG7rbm+NGnPdQ3MKDVbzT/InQVSdcGdxvUSlQXRPShlFkA=="
        );
        String who = userByPublicKey.get(message.publicKey);
        System.out.println(who);
        EzyAsyCrypt ezyAsyCrypt = EzyAsyCrypt.builder()
            .privateKey(EzyBase64.decode(message.publicKey))
            .build();
        byte[] decryptedHash = ezyAsyCrypt.decrypt(message.signature);
        byte[] hash = EzySHA256.cryptUtf(message.message).getBytes(StandardCharsets.UTF_8);
        System.out.println(new String(decryptedHash).equals(new String(hash)));
    }
}
