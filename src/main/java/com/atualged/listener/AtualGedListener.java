package com.atualged.listener;
import javax.servlet.ServletContextEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoaderListener;

import com.atualged.find.FindDFe;

@Service
public class AtualGedListener extends ContextLoaderListener{
	@Autowired
	private FindDFe find;
	 @Override
	    public void contextInitialized(ServletContextEvent arg0) {
	        super.contextInitialized(arg0);
	        find.buscar();
	    }

	    @Override
	    public void contextDestroyed(ServletContextEvent arg0) {
	        super.contextDestroyed(arg0);
	    }
}
