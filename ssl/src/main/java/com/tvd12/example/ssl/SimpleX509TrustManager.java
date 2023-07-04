package com.tvd12.example.ssl;

import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class SimpleX509TrustManager implements X509TrustManager {
    @Override
    public void checkClientTrusted(
        X509Certificate[] chain,
        String authType
    ) throws CertificateException {
        // Không thực hiện xác thực client
    }

    @Override
    public void checkServerTrusted(
        X509Certificate[] chain,
        String authType
    ) throws CertificateException {
        // Không thực hiện xác thực server
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }
}
