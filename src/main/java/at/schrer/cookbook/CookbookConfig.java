package at.schrer.cookbook;

import at.schrer.cookbook.interceptor.SidebarHandlerInterceptor;
import at.schrer.cookbook.repository.CategoryRepository;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.sql.DataSource;
import java.util.Locale;

@Configuration
public class CookbookConfig implements WebMvcConfigurer {

    @Value("${jdbc.url}")
    private String jdbcUrl;

    @Value("${jdbc.driver}")
    private String jdbcDriver;

    @Value("${jdbc.username}")
    private String dbUsername;

    @Value("${jdbc.password}")
    private String dbPassword;

    private ApplicationContext applicationContext;

    @Autowired
    public CookbookConfig(ApplicationContext applicationContext){
        this.applicationContext = applicationContext;
    }

    @Bean
    public LocaleResolver localeResolver(){
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.GERMAN);
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(localeChangeInterceptor());
        registry.addInterceptor(new SidebarHandlerInterceptor(applicationContext.getBean(CategoryRepository.class)));
    }

    @Bean
    @ConfigurationProperties(prefix = "app.datasource")
    public DataSource dataSource(){
        HikariConfig config = new HikariConfig();
        config.setUsername(dbUsername);
        config.setPassword(dbPassword);
        config.setJdbcUrl(jdbcUrl);
        config.setDriverClassName(jdbcDriver);
        config.setAutoCommit(true);
        return new HikariDataSource(config);
    }
}
