package com.example.fileupload.config;

import javax.sql.DataSource;

//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.annotation.MapperScan;
import com.example.fileupload.dao.FileUploadDAO;
import com.example.fileupload.dao.FileUploadDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;


@Configuration
public class AppConfig {
    /*
    @Autowired
    private DataSource dataSource;

    @Bean
    public FileUploadDAO fileUploadDAO() {
        FileUploadDAOImpl dao = new FileUploadDAOImpl(dataSource);
        return dao;
    }

     */
}





/*
@Configuration
@MapperScan(basePackages = "com.example.fileupload.dao") // MyBatis 매퍼 인터페이스 스캔 패키지 지정
public class AppConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath*:com/example/fileupload/mapper/*.xml"));
        return sessionFactory.getObject();
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer scannerConfigurer = new MapperScannerConfigurer();
        scannerConfigurer.setBasePackage("com.example.fileupload.dao");
        return scannerConfigurer;
    }

    @Bean
    public BoardDAO boardDAO() {
        // BoardDAOImpl 클래스의 생성자에서 SqlSessionFactory를 주입받도록 수정
        return new BoardDAOImpl(sqlSessionFactory());
    }
}

 */