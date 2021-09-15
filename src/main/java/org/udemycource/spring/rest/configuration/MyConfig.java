package org.udemycource.spring.rest.configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "org.udemycource.spring.rest")
@EnableWebMvc // вместо <mvc:annotation-driven/> из applicationContext.xml
@EnableTransactionManagement // вместо <tx:annotation-driven transaction-manager="transactionManager" />
public class MyConfig {

/**
 *Вместо создания бина через xml, делаем это с помощью java
 * <!--  Создает connection pool для того, чтобы не тратиться на постоянные создания сессий при работе с JDBC-->
 *     <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource"
 *           destroy-method="close">
 *         <property name="driverClass" value="com.mysql.cj.jdbc.Driver" />
 *         <property name="jdbcUrl" value="jdbc:mysql://localhost:3306/my_db?useSSL=false&amp;serverTimezone=UTC" />
 *         <property name="user" value="bestuser" />
 *         <property name="password" value="bestuser" />
 *     </bean>
 */

    @Bean
    public DataSource dataSource() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/my_db?useSSL=false&amp;serverTimezone=UTC");
            dataSource.setUser("bestuser");
            dataSource.setPassword("bestuser");


        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    /**
     * Настраиваем бин sessionFactory как в xml
     *
     * <!--    Создает сессию -->
     *     <bean id="sessionFactory"
     *           class="org.springframework.orm.hibernate5.LocalSessionFactoryBean">
     *         <property name="dataSource" ref="dataSource" />
     *         <property name="packagesToScan" value="org.udemycource.spring.hibernate_aop.entity" />
     *         <property name="hibernateProperties">
     *             <props>
     *                 <prop key="hibernate.dialect">org.hibernate.dialect.MySQLDialect</prop>
     *                 <prop key="hibernate.show_sql">true</prop>
     *             </props>
     *         </property>
     *     </bean>
     */
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("org.udemycource.spring.rest.entity");

        Properties hibernateProperties = new Properties();

        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        hibernateProperties.setProperty("hibernate.show_sql", "true");

        sessionFactory.setHibernateProperties(hibernateProperties);

        return sessionFactory;
    }

    /**
     * <!--    Позволяет явно не открывать/щакрывать транзакции в коде -->
     *     <bean id="transactionManager"
     *           class="org.springframework.orm.hibernate5.HibernateTransactionManager">
     *         <property name="sessionFactory" ref="sessionFactory"/>
     *     </bean>
     */
    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());

        return transactionManager;
    }
}
