package com.example.restapi.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restapi.entities.Categoria;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	List<Categoria> categorias = new ArrayList<Categoria>();
	
	@PostMapping
	public ResponseEntity<Categoria> salvarProduto(@RequestBody Categoria categoria) {
		categoria.setId(categorias.size()+1);
		categorias.add(categoria);
		return ResponseEntity.status(HttpStatus.CREATED).body(categoria);
	}
	
	@GetMapping("/{idCategoria}")
	public ResponseEntity<Categoria> getCategoria(@PathVariable("idCategoria") int id) {
		Categoria categoria = null;
		for(Categoria cate : categorias) {
			if(cate.getId() == id) {
				categoria = cate;
			}
		}
		if(categoria != null) {
			return ResponseEntity.status(HttpStatus.OK).body(categoria);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		
	}
	
	@DeleteMapping("/{idCategoria}")
	public ResponseEntity<Categoria> deleteCategoria(@PathVariable("idCategoria") int id) {
		Categoria categoria = null;
		for(Categoria cate : categorias) {
			if(cate.getId() == id) {
				categoria = cate;
			}
		}
		if(categoria != null) {
			categorias.remove(categoria);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		
	}
	
	@PutMapping("/{idCategoria}")
	public ResponseEntity<Categoria> updateProduto(@PathVariable("idCategoria") int id, @RequestBody Categoria categoria) {
		Categoria cate = null;
		for(Categoria ct : categorias) {
			if(ct.getId() == id) {
				cate = ct;
			}
		}
		if(cate != null) {
			cate.setNome(categoria.getNome());
			cate.setStatus(categoria.getStatus());
			return ResponseEntity.status(HttpStatus.OK).body(cate);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		
	}
	
	@GetMapping
	public ResponseEntity<List<Categoria>> getCategoria() {
		return ResponseEntity.status(HttpStatus.OK).body(categorias);
	}
}
