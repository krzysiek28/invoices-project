package web.mvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfLogoutHandler;
import web.mvc.MyController;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource);
    }

//    @Bean
//    public DriverManagerDataSource dataSource(){
//        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
//        driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
//        driverManagerDataSource.setUrl("jdbc:postgresql://localhost:5432/invoices");
//        driverManagerDataSource.setUsername("postgres");
//        driverManagerDataSource.setPassword("postgres");
//        return driverManagerDataSource;
//    }

    @Bean
    public DriverManagerDataSource dataSource(){
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
        driverManagerDataSource.setUrl("jdbc:postgresql://ec2-54-204-13-130.compute-1.amazonaws.com:5432/d3j96ks988gugb?sslmode=require");
        driverManagerDataSource.setUsername("cvrvgvgcfitbtd");
        driverManagerDataSource.setPassword("d6aebf23506b8ef75231c767bcf11578952a596ab24ff43c60c5344a5c79443e");
        return driverManagerDataSource;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        http.authorizeRequests()
                .antMatchers("/log")
                .access("hasRole('ROLE_USER')")
                .and().formLogin().loginPage("/login")
                .usernameParameter("email").passwordParameter("password")
                /*.loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/",true)
                .failureUrl("/login.jsp?error=true")*/
                .and().logout().logoutSuccessUrl("/")
                .and().csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select email,password,enabled from users where email=?")
                .authoritiesByUsernameQuery("select email,role from user_roles where email=?");
                //.authoritiesByUsernameQuery("select email, role from user_roles where username=?");
    }

}
