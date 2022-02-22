package com.atualged.find;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atualged.model.Empresa;
import com.atualged.repository.EmpresaRepository;

@Service
public class FindDFe {
	private ThreadPoolExecutor threadExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(20);
	@Autowired
	private EmpresaRepository er;

	public void buscar() {

		new Thread(new Runnable() {
			LocalDate time;

			@Override
			public void run() {
				// TODO Auto-generated method stub


				if (time == null || time != LocalDate.now()) {
					time = LocalDate.now();
					if(threadExecutor != null) {
						threadExecutor.shutdown();
					}
					threadExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(20);
					buscarEmpresas();
				}
			}

		}).start();
	}
	private void buscarEmpresas() {
		// TODO Auto-generated method stub
		List<Empresa> list = er.findAll();
		
		for (Empresa empresa : list) {
			Thread thread = new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					// LocalDate date = null;
					try {
						/// System.out.println(aux.getAttributes());
						buscarDFe(empresa);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			});
			threadExecutor.execute(thread);
		}
	}
	
	private void buscarDFe(Empresa empresa) {
		 
	}
}

