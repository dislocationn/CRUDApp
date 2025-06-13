package config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.sql.DriverManager;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = {"dao", "service"})
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
public class AppConfig {
    @Value("${hibernate.connection.url}")
    private String url;

    @Value("${hibernate.connection.driver_class}")
    private String driver;

    @Value("${hibernate.connection.username}")
    private String username;

    @Value("${hibernate.connection.password}")
    private String password;

    @Value("${hibernate.dialect}")
    private String dialect;

    @Value("${hibernate.show_sql}")
    private String showSQL;

    @Value("${hibernate.format_sql}")
    private String formatSQl;

    @Value("${hibernate.hbm2ddl.auto}")
    private String auto;

    @Bean
    public DataSource getConnection() {
        DriverManagerDataSource source = new DriverManagerDataSource();
        source.setDriverClassName(driver);
        source.setUsername(username);
        source.setUrl(url);
        source.setPassword(password);
        return source;
    }

    @Bean
    public LocalSessionFactoryBean getSesFacBean() {
        LocalSessionFactoryBean bean = new LocalSessionFactoryBean();
        bean.setDataSource(getConnection());
        bean.setPackagesToScan("model");
        Properties prop = new Properties();
        prop.put("hibernate.dialect", dialect);
        prop.put("hibernate.show_sql", showSQL);
        prop.put("hibernate.format_sql", formatSQl);
        prop.put("hibernate.hbm2ddl.auto", auto);
        bean.setHibernateProperties(prop);
        return bean;
    }

    @Bean
    public HibernateTransactionManager transactionManager (SessionFactory sessionFactory) {
        HibernateTransactionManager trMan = new HibernateTransactionManager();
        trMan.setSessionFactory(sessionFactory);
        return trMan;
    }
}
