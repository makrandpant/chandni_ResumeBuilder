/**
 * 
 */
package SpringBoot;

/**
 * @author ChandniJ
 *
 */
import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.boot.context.web.SpringBootServletInitializer;
/*import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;
*/
@SpringBootApplication
//@EnableAutoConfiguration
@ComponentScan(basePackages={"resumeBuilder.*"})

public class MainApplication {
	
	
	
  public static void main(String[] args) {
      SpringApplication.run(MainApplication.class, args);
  }
  

  /*Thymeleaf View Resolver  */
 		 /*	@Bean
 		    public TemplateResolver templateResolver(){
 		        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
 		        templateResolver.setPrefix("/WEB-INF/templates/");
 		        templateResolver.setSuffix(".html");
 		        templateResolver.setTemplateMode("HTML5");
 		        templateResolver.setCacheable(false);

 		        return templateResolver;
 		    }
 		 
 		 
 		   @Bean
 		   public SpringTemplateEngine templateEngine(){
 		         SpringTemplateEngine templateEngine = new SpringTemplateEngine();
 		         templateEngine.setTemplateResolver(templateResolver());
 		        // templateEngine.addDialect(new SpringSecurityDialect());
 		         return templateEngine;
 		   }

 		     @Bean
 		     public ViewResolver viewResolver(){
 		         ThymeleafViewResolver viewResolver = new ThymeleafViewResolver() ;
 		         viewResolver.setTemplateEngine(templateEngine());
 		         viewResolver.setOrder(0);

 		         return viewResolver;
 		      }
 		      Thymeleaf View Resolver 
*/   			 
 		
}
