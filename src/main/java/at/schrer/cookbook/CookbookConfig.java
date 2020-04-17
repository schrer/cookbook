package at.schrer.cookbook;

import at.schrer.cookbook.frontend.interceptor.BaseTemplateHandlerInterceptor;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.sql.DataSource;
import java.util.Locale;
import java.util.Set;

@Configuration
public class CookbookConfig implements WebMvcConfigurer {

    public static final String COOOKBOOK_CONVERTER_BEAN_NAME = "cookbookConverter";

    @Value("${jdbc.url}")
    private String jdbcUrl;

    @Value("${jdbc.driver}")
    private String jdbcDriver;

    @Value("${jdbc.username}")
    private String dbUsername;

    @Value("${jdbc.password}")
    private String dbPassword;

    private final ApplicationContext applicationContext;

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
        registry.addInterceptor(applicationContext.getBean(BaseTemplateHandlerInterceptor.class));
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

    @Bean(name = COOOKBOOK_CONVERTER_BEAN_NAME)
    public ConversionService conversionService(Set<Converter<?,?>> converters){
        ConversionServiceFactoryBean cfb = new ConversionServiceFactoryBean();
        cfb.setConverters(converters);
        cfb.afterPropertiesSet();
        return cfb.getObject();
    }
}
