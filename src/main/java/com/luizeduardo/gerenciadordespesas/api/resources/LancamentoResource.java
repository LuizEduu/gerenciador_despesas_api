package com.luizeduardo.gerenciadordespesas.api.resources;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luizeduardo.gerenciadordespesas.api.event.RecursoCriadoEvent;
import com.luizeduardo.gerenciadordespesas.api.model.Lancamento;
import com.luizeduardo.gerenciadordespesas.api.repositories.LancamentoRepository;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

	@Autowired
	private LancamentoRepository lancamentoRepository;

	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@PostMapping
	public ResponseEntity<?> cadastrarLancamento(@Valid @RequestBody Lancamento lancamento,
			HttpServletResponse response) {
		Lancamento lancamentoCadastrado = lancamentoRepository.save(lancamento);

		eventPublisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoCadastrado.getId()));

		return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoCadastrado);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> buscarLancamentoPorId(@PathVariable Long id) {
		Lancamento lancamento = lancamentoRepository.findOne(id);

		return lancamento != null ? ResponseEntity.ok(lancamento) : ResponseEntity.notFound().build();

	}

	@GetMapping
	public List<Lancamento> listarLancamentos() {
		return lancamentoRepository.findAll();
	}
}
