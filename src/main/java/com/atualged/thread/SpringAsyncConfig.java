package com.atualged.thread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import com.atualged.find.FindDFe;

@Configuration
@EnableAsync
public class SpringAsyncConfig {
	
	@Autowired
	private FindDFe find;
	
		@Bean(name = "threadPoolTaskExecutor")
	    public void threadPoolTaskExecutor() {
			
			find.buscar();
	    }
}
