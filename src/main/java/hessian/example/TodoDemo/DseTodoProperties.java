package hessian.example.TodoDemo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
//@PropertySource("classpath:application-dev.properties")
@ConfigurationProperties(prefix = "vcap.services.dsecups.credentials")
public class DseTodoProperties {
    private String hostname;
    private Integer port;
    private String cass_username;
    private String cass_password;
    private String trust_password;
    private String truststore;
    private String key_password;
    private String keystore;
    private String keystore2;
    private String keyspace;
    private Boolean usessl;
    private Boolean usepwd;

    public DseTodoProperties() { }

    public DseTodoProperties(String hostname, Integer port, String cass_username, String cass_password, String trust_password, String truststore, String key_password, String keystore, String keystore2, String keyspace, Boolean usessl, Boolean usepwd) {
        this.hostname = hostname;
        this.port = port;
        this.cass_username = cass_username;
        this.cass_password = cass_password;
        this.trust_password = trust_password;
        this.truststore = truststore;
        this.key_password = key_password;
        this.keystore = keystore;
        this.keystore2 = keystore2;
        this.keyspace = keyspace;
        this.usessl = usessl;
        this.usepwd = usepwd;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getCass_username() {
        return cass_username;
    }

    public void setCass_username(String cass_username) {
        this.cass_username = cass_username;
    }

    public String getCass_password() {
        return cass_password;
    }

    public void setCass_password(String cass_password) {
        this.cass_password = cass_password;
    }

    public String getTrust_password() {
        return trust_password;
    }

    public void setTrust_password(String trust_password) {
        this.trust_password = trust_password;
    }

    public String getTruststore() {
        return truststore;
    }

    public void setTruststore(String truststore) {
        this.truststore = truststore;
    }

    public String getKey_password() {
        return key_password;
    }

    public void setKey_password(String key_password) {
        this.key_password = key_password;
    }

    public String getKeystore() {
        return keystore;
    }

    public void setKeystore(String keystore) {
        this.keystore = keystore;
    }

    public String getKeystore2() {
        return keystore2;
    }

    public void setKeystore2(String keystore2) {
        this.keystore2 = keystore2;
    }

    public String getKeyspace() {
        return keyspace;
    }

    public void setKeyspace(String keyspace) {
        this.keyspace = keyspace;
    }

    public Boolean getUsessl() {
        return usessl;
    }

    public void setUsessl(Boolean usessl) {
        this.usessl = usessl;
    }

    public Boolean getUsepwd() {
        return usepwd;
    }

    public void setUsepwd(Boolean usepwd) {
        this.usepwd = usepwd;
    }

    @Override
    public String toString() {
        return "DseTodoProperties{" +
                "hostname='" + hostname + '\'' +
                ", port=" + port +
                ", cass_username='" + cass_username + '\'' +
                ", cass_password='" + cass_password + '\'' +
                ", trust_password='" + trust_password + '\'' +
                ", truststore='" + truststore + '\'' +
                ", key_password='" + key_password + '\'' +
                ", keystore='" + keystore + '\'' +
                ", keystore2='" + keystore2 + '\'' +
                ", keyspace='" + keyspace + '\'' +
                ", usessl=" + usessl +
                ", usepwd=" + usepwd +
                '}';
    }
}
