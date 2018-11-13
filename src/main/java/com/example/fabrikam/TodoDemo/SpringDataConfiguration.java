package com.example.fabrikam.TodoDemo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

/*
@Configuration
@EnableCassandraRepositories
public class SpringDataConfiguration extends AbstractCassandraConfiguration {

    @Value("${dse.contactPoints}")
    public String contactPoints;

    @Value("${dse.port}")
    private int port;

    @Value("${dse.keyspace}")
    private String keySpace;

    @Value("${springdata.basepackage}")
    private String basePackages;
  
    @Value("${dse.username}")
    public Optional < String > dseUsername;
 
    @Value("${dse.password}")
    public Optional < String > dsePassword;
  
    // {@inheritDoc} 
    @Override
    protected String getKeyspaceName() {
	return keySpace;
    }

    // {@inheritDoc} 
    @Override
    protected String getContactPoints() {
	return contactPoints;
    }

    // {@inheritDoc} 
    @Override
    protected int getPort() {
	return port;
    }

    // {@inheritDoc} 
    @Override
    public SchemaAction getSchemaAction() {
	return SchemaAction.CREATE_IF_NOT_EXISTS;
    }

    // {@inheritDoc} 
    @Override
    public String[] getEntityBasePackages() {
	return new String[] {basePackages};
    }
  
}
*/

@Configuration
public class SpringDataConfiguration extends AbstractCassandraConfiguration {
    public String getContactPoints() {
	return "node0";
    }

    public String getKeyspaceName() {
	return "demo_sdc";
    }
}
