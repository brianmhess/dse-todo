package hessian.example.TodoDemo;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.Base64;

import com.datastax.driver.core.RemoteEndpointAwareJdkSSLOptions;
import com.datastax.driver.dse.DseCluster;
import com.datastax.driver.dse.DseSession;
import com.datastax.driver.dse.auth.DsePlainTextAuthProvider;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;


@Configuration
public class DseTodoConfiguration {
    @Autowired
    private DseTodoProperties dseTodoProperties;

    @Bean
    public DseCluster dseCluster() {
        String host = dseTodoProperties.getHostname();
        Integer port = dseTodoProperties.getPort();
        Boolean usessl = dseTodoProperties.getUsessl();
        Boolean usepwd = dseTodoProperties.getUsepwd();

        if (null == usessl) usessl = false;
        if (null == usepwd) usepwd = false;

        DseCluster.Builder cluster = DseCluster.builder().addContactPoint(host).withPort(port);

        if (usessl) {
            String truststore = dseTodoProperties.getTruststore();
            String keystore = dseTodoProperties.getKeystore() + dseTodoProperties.getKeystore2();
            String trust_password = dseTodoProperties.getTrust_password();
            String key_password = dseTodoProperties.getKey_password();
            SSLContext sslContext = null;
            InputStream tss = null;
            InputStream kss = null;

            System.err.println("\n\n\nAAAAA\n" + keystore + "\n" + dseTodoProperties.getKeystore()+ "\n" + dseTodoProperties.getKeystore2()  + "\n\n\n");
            System.err.println("\n\n\nBBBBB\n" + dseTodoProperties.toString() + "\n\n\n");

            try {
                TrustManagerFactory tmf = null;
                KeyManagerFactory kmf = null;
                if ((null != truststore) && (null != trust_password)) {
                    tss = new ByteArrayInputStream(Base64.getDecoder().decode(truststore));
                    KeyStore tks = KeyStore.getInstance("JKS");
                    tks.load(tss, trust_password.toCharArray());
                    tmf = TrustManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
                    tmf.init(tks);
                }
                if ((null != keystore) && (null != key_password)) {
                    kss = new ByteArrayInputStream(Base64.getDecoder().decode(keystore));
                    KeyStore kks = KeyStore.getInstance("JKS");
                    kks.load(kss, key_password.toCharArray());
                    kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
                    kmf.init(kks, key_password.toCharArray());
                }
                sslContext = SSLContext.getInstance("TLS");
                sslContext.init(kmf != null ? kmf.getKeyManagers() : null,
                        tmf != null ? tmf.getTrustManagers() : null,
                        new SecureRandom());
            } catch (Exception e) {
                e.printStackTrace();
            }
            RemoteEndpointAwareJdkSSLOptions sslOptions =
                    (RemoteEndpointAwareJdkSSLOptions) RemoteEndpointAwareJdkSSLOptions.
                            builder().withSSLContext(sslContext).build();
            cluster.withSSL(sslOptions);
        }
        if (usepwd) {
            String cass_username = dseTodoProperties.getCass_username();
            String cass_password = dseTodoProperties.getCass_password();
            cluster.withAuthProvider(new DsePlainTextAuthProvider(cass_username, cass_password));
        }
        return cluster.build();
    }

    @Bean
    public DseSession dseSession(DseCluster dseCluster) {
        return dseCluster.connect(dseTodoProperties.getKeyspace());
    }

    @Bean
    public MappingManager mappingManager(DseSession session) {
        return new MappingManager(session);
    }
}
