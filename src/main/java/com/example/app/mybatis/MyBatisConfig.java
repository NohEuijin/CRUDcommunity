package com.example.app.mybatis;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class MyBatisConfig {
//    ApplicationContext객체는 스프링 부트의 핵심 인터페이스이다.
//    우리가 스프링 컨테이너라고 부르는 논리적 구조를 실체화한 것이 ApplicationContext이다.
//    즉, Bean관리, DI, AOP지원 등을 담당한다.
    private final ApplicationContext applicationContext;

    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    @Bean
    public HikariConfig hikariConfig(){
//        Hikari란?
//        HikariCP(히카리 커넥션 풀) 라이브러리를 의미한다.
//        빠르고, 가볍고, 설정이 쉽고, 안정성이 높다는 장점이 있다.
//        JSP에서 사용한 DBCP는 아파치톰캣에서 지원하는 라이브러리였으나
//        스프링에서는 Hikari를 사용한다. 성능이 상대적으로 더 좋아 MyBatis와 함께 많이 사용된다.
        return new HikariConfig();
    }

    @Bean
    public DataSource dataSource(){
        return new HikariDataSource(hikariConfig());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception{
//        JSP에서는 우리가 직접 SqlSessionFactory 객체를 설정하여 만들어 사용했었다.
//        SqlSessionFactoryBuilder에 설정파일을 제공하면 SqlSessionFactory를 만들어 줬었다.
//        그리고 SqlSessionFactory객체를 사용하여 SqlSession객체를 꺼내 사용했었다.

//        spring에서는 SqlSessionFactory객체도 Bean으로 만들어 스프링 컨테이너가 관리하도록 한다.
//        SqlSessionFactoryBean 객체에 설정을 해주면 팩토리를 지어주는 SqlSessionFactoryBuilder의 역할을 수행하며
//        그렇게 만들어진 SqlSessionFactory를 스프링 컨테너가 관리하게 된다.
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());

        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath*:/mapper/*.xml"));
        sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:/config/config.xml"));

        try {
            SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
            sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);
            return sqlSessionFactory;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
