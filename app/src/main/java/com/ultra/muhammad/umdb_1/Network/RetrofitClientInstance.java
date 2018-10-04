package com.ultra.muhammad.umdb_1.Network;

import android.util.Log;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    public static final String POSTER_BASE_URL = "https://image.tmdb.org/t/p/w185/";
    private static final String TAG = "RetrofitClientInstance";
    private static final String MOVIE_BASE_URL = "https://api.themoviedb.org/3/";


    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance() {
        Log.wtf(TAG, "getRetrofitInstance: getting retrofit instance...");
        if (retrofit == null) {
            retrofit =
                    new Retrofit.Builder()
                            .baseUrl(MOVIE_BASE_URL)
                            .client(getUnsafeOkHttpClient().build())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
        }

        return retrofit;
    }

    private static OkHttpClient.Builder getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts =
                    new TrustManager[]{
                            new X509TrustManager() {
                                @Override
                                public void checkClientTrusted(
                                        java.security.cert.X509Certificate[] chain, String authType) {
                                }

                                @Override
                                public void checkServerTrusted(
                                        java.security.cert.X509Certificate[] chain, String authType) {
                                }

                                @Override
                                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                                    return new java.security.cert.X509Certificate[]{};
                                }
                            }
                    };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier(
                    new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    });
            return builder;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
