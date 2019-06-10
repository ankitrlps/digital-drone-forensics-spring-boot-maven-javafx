package dforensics.dji.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;
import javax.servlet.*;


@Slf4j
@Configuration
public class BeanConfig implements ServletContextInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        initH2Console(servletContext);
    }

    private void initH2Console(ServletContext servletContext) {
        log.info("Starting H2 console");
        ServletRegistration.Dynamic h2ConsoleServlet = servletContext.addServlet("H2Console", new org.h2.server.web.WebServlet());
        h2ConsoleServlet.addMapping("/h2console/*");
        h2ConsoleServlet.setLoadOnStartup(1);
    }

/*    @Bean
    public SampleService sampleService(){
        return new SampleService();
    }*/
}
