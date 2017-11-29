package org.bluemixtestr.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.ConditionalOnEnabledInfoContributor;
import org.springframework.boot.actuate.endpoint.EnvironmentEndpoint;
import org.springframework.boot.actuate.endpoint.InfoEndpoint;
import org.springframework.boot.actuate.info.GitInfoContributor;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.boot.info.GitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class MicroserviceSecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired private GitProperties gitProperties;

     @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
            .antMatchers(HttpMethod.OPTIONS, "/**")
            .antMatchers("/swagger-ui/index.html")
            .antMatchers("/test/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf()
            .disable()
            .headers()
            .frameOptions()
            .disable()
        .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
            .authorizeRequests()
            .antMatchers("/**").permitAll();
    }
    
	@Bean
	@ConditionalOnMissingBean
	public EnvironmentEndpoint environmentEndpoint() {
		return new EnvironmentEndpoint();
	}
	
	@Bean
	@ConditionalOnEnabledInfoContributor("git")
	@ConditionalOnSingleCandidate(GitProperties.class)
	@ConditionalOnMissingBean
	public GitInfoContributor gitInfoContributor(GitProperties gitProperties) {
		return new GitInfoContributor(gitProperties);
	}
	
	@Bean
	@ConditionalOnMissingBean
	public InfoEndpoint infoEndpoint() throws Exception {
		InfoContributor info = new GitInfoContributor(gitProperties);
		List<InfoContributor> contributors = new ArrayList<>();
		contributors.add(info);
		return new InfoEndpoint(contributors);
	}



}
