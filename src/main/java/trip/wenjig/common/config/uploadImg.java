package trip.wenjig.common.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

@Configuration
public class uploadImg {

    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("100MB");
        //总上传数据总大小
        factory.setLocation("D://Document//WenJig//Temp//");
        factory.setMaxRequestSize("100MB");
        return factory.createMultipartConfig();
    }

}
