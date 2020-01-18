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
import com.luizeduardo.gerenciadordespesas.api.model.Categoria;
import com.luizeduardo.gerenciadordespesas.api.repositories.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ApplicationEventPublisher publisher;

	@PostMapping
	public ResponseEntity<?> criarCategoria(@RequestBody @Valid Categoria categoria, HttpServletResponse response) {

		Categoria categoriaSalva = categoriaRepository.save(categoria);

		publisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaSalva.getId()));

		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> buscarCategoria(@PathVariable Long id) {
		Categoria categoriaPesquisada = categoriaRepository.findOne(id);

		return categoriaPesquisada != null ? ResponseEntity.ok(categoriaPesquisada) : ResponseEntity.notFound().build();
	}

	@GetMapping
	public List<Categoria> listarCategorias() {
		return categoriaRepository.findAll();
	}

}
