package KeeperGroup.KeeperBack.BasicAuth;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

@Configuration
@EnableWebSecurity
public class SpringSecutiryConfigurationBasic extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS,"/**").permitAll().anyRequest().authenticated().and().httpBasic();

        //force put post no need csrf token
        //http.csrf().disable();
        //Prefligth request ok to all url

        //http.authorizeRequests();
        //http.authorizeRequests().antMatchers(HttpMethod.OPTIONS,"/**").permitAll();
        //http.authorizeRequests().anyRequest().authenticated();


        /*http.authorizeRequests((requests) -> {
            ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)requests.anyRequest()).authenticated();
        });*/
        //Disable the form
        //http.formLogin();
        //http.httpBasic();

    }
}
