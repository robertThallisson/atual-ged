package com.atualged.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.atualged.model.Estado;
import com.atualged.repository.EstadoRepository;
import com.atualged.service.EstadoService;
import com.atualged.event.RecursoCriadoEvent;

@RestController
@RequestMapping("/estado")
public class EstadoResource {
	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private EstadoService estadoService;

	@GetMapping
	//@PreAuthorize("hasAuthority('ROLE_PESQUISAR_ESTADO')")
	public List<Estado> listar() {
		return estadoRepository.findAll();
	}
	
	
	@GetMapping("/{id}")
	//@PreAuthorize("hasAuthority('ROLE_PESQUISAR_ESTADO') and #oauth2.hasScope('read')")
	public ResponseEntity<Optional<Estado>> buscarPeloId(@PathVariable Long id) {
		Optional<Estado> estado = estadoRepository.findById(id);			
		return estado != null ? ResponseEntity.ok(estado) : ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Estado> criar(@RequestBody Estado estado, HttpServletResponse response) {
		Estado estadoSalvo = estadoRepository.save(estado);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, estadoSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(estadoSalvo);
	}
	
	@DeleteMapping("/{estado}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	//@PreAuthorize("hasAuthority('ROLE_REMOVER_ESTADO') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Estado estado) {
		estadoRepository.delete(estado);
	}

	@PutMapping("/{id}")
	//@PreAuthorize("hasAuthority('ROLE_CADASTRAR_ESTADO') and #oauth2.hasScope('write')")
	public ResponseEntity<Estado> atualizar(@PathVariable Long id, @Valid @RequestBody Estado estado) {
		Estado estadoSalvo = estadoService.atualizar(id, estado);
		return ResponseEntity.ok(estadoSalvo);
	}

	@PutMapping("/{id}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	//@PreAuthorize("hasAuthority('ROLE_CADASTRAR_ESTADO') and #oauth2.hasScope('write')")
	public void atualizarPropriedadeAtivo(@PathVariable Long id, @RequestBody Boolean ativo) {
		estadoService.atualizarPropriedadeAtivo(id, ativo);
	}


}
