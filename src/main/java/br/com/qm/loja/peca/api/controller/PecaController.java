package br.com.qm.loja.peca.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.qm.loja.peca.api.entity.Peca;
import br.com.qm.loja.peca.api.exception.ErroDeNegocioException;
import br.com.qm.loja.peca.api.service.PecaService;

@RestController
@RequestMapping("/pecas")
public class PecaController {
	
	@Autowired
	PecaService pecaService;
	
	@PostMapping
	public Peca cadastroPeca(@Valid @RequestBody Peca peca) throws ErroDeNegocioException{
		return pecaService.cadastraPeca(peca);		
	}
	
	@GetMapping(path = "/{codBarras}")
	public Optional<Peca> consultaPeca(@PathVariable Long codBarras){
		return pecaService.consultaPeca(codBarras);
	}
	
	@GetMapping
	public List<Peca> listaPecas(){
		return pecaService.listaPecas();
	}
	
	@DeleteMapping(path = "/{codBarras}")
	public boolean removePeca(@PathVariable Long codBarras) throws ErroDeNegocioException {
		return pecaService.removePeca(codBarras);
	}
	
	@PutMapping(path = "/{codBarras}")
	public Peca alteraPeca(@PathVariable Long codBarras, @RequestBody Peca peca) throws ErroDeNegocioException{
		return pecaService.alteraPeca(codBarras, peca);
	}
	
	@GetMapping("/{texto}/comeco")
	public List<Peca> listaPecasComecadasCom(@PathVariable String texto){
		return pecaService.listaPecasComecadasCom(texto);
	}
	
	@GetMapping(path = "/{modeloCarro}/modeloCarro")
	public List<Peca> listaPecasPorModeloCarro(@PathVariable String modeloCarro) throws ErroDeNegocioException {
		return this.pecaService.listaPecasPorModeloCarro(modeloCarro);
	}
	
	@GetMapping(path = "/{categoria}/categoria")
	public List<Peca> listaPecasPorCategoria(@PathVariable String categoria) throws ErroDeNegocioException {
		return this.pecaService.listaPecasPorCategoria(categoria);
	}
	

	
		
}
