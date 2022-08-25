package org.jelly.frame.core.autoconfigure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * knife4j 自动配置
 * @author zhangxb
 */
@Configuration
@EnableSwagger2WebMvc
public class Knife4jAutoConfiguration {

    private Docket defaultDocketBean(String groupName,String basePack){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder().build())
                .groupName(groupName)
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePack))
                .paths(PathSelectors.any()).build();
    }


    @Bean(value = "minioDocketBean")
    public Docket minioDocketBean(){
        return defaultDocketBean("Minio文件服务","org.jelly.frame.minio.controller");
    }
}
