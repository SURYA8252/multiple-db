package com.multiple.db.postgres2.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		   entityManagerFactoryRef = "secondEntityManagerFactoryBean",
		   basePackages = {"com.multiple.db.postgres2.repository"},
		   transactionManagerRef = "secondTransactionManager"
		)
public class SecondDBConfig {

	@Autowired
	private Environment environment;
	
	//Data Source
	@Bean(name = "secondDataSource")
	@Primary
	public DataSource dataSource () {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(environment.getProperty("second.datasource.url"));
		dataSource.setUsername(environment.getProperty("second.datasource.username"));
		dataSource.setPassword(environment.getProperty("second.datasource.password"));
		return dataSource;
	}
	//entityManagerFactory
	@Bean(name = "secondEntityManagerFactoryBean")
	@Primary
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
		LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
		bean.setDataSource(dataSource());
		JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		bean.setJpaVendorAdapter(adapter);
		Map<String, String> props = new HashMap<>();
		props.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		props.put("hibernate.show-sql", "true");
		props.put("hibernate.hbm2ddl.auto", "update");
		bean.setJpaPropertyMap(props);
		bean.setPackagesToScan("com.multiple.db.postgres2.entities");
		return bean;
	}
	//platformTransactionManager
	@Bean(name = "secondTransactionManager")
	@Primary
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager manager = new JpaTransactionManager();
		manager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
		return manager;
	}
}
