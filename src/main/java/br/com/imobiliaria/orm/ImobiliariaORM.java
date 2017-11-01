package br.com.imobiliaria.orm;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableJpaRepositories("br.com.imobiliaria")
@EnableTransactionManagement
public class ImobiliariaORM {
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource ds) {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(ds);
		em.setPackagesToScan("br.com.imobiliaria");
		JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(adapter);
		em.setJpaProperties(addictionalProperties());
		em.setPersistenceUnitName("imobiliariaPU");
		return em;
	}
	
	@Bean
	public DataSource imobiliariaDS() throws PropertyVetoException {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setDriverClass("com.mysql.jdbc.Driver");
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/imobiliaria?useSSL=false");
		dataSource.setUser("root");
		dataSource.setPassword("1234");
		dataSource.setMinPoolSize(1);
		dataSource.setMaxPoolSize(5);
		dataSource.setMaxStatements(50);
		dataSource.setTestConnectionOnCheckout(true);
		dataSource.setIdleConnectionTestPeriod(3000);
		return dataSource;
	}
	
	@Bean
	public PlatformTransactionManager imobiliariaTX(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}
	
	private Properties addictionalProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
		properties.put("hibernate.show_sql", "false");
		properties.put("hibernate.format_sql", "true");
		properties.put("hibernate.connection.autocommit", "true");
		properties.put("hibernate.use_sql_comments", "true");
		properties.put("hibernate.enable_lazy_load_no_trans", "true");
		return properties;
	}

}