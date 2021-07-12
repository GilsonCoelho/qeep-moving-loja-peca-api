package br.com.qm.loja.peca.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.qm.loja.peca.api.entity.Peca;
import br.com.qm.loja.peca.api.exception.ErroDeNegocioException;
import br.com.qm.loja.peca.api.repository.PecaRepository;

@Service
public class PecaService {
	
	@Autowired
	PecaRepository pecaRepository;
	
	public Peca cadastraPeca(Peca peca)throws ErroDeNegocioException {
		
		if(pecaRepository.existsById(peca.getCodBarras())) {
			throw new ErroDeNegocioException("A peça já existe!");
		}
		
		return pecaRepository.save(peca);
	}
	
	public Optional<Peca> consultaPeca(Long codBarras){
		return pecaRepository.findById(codBarras);
	}
	
	public List<Peca> listaPecas() {
		return (List<Peca>) pecaRepository.findAll();		
		
	}
	
	public boolean removePeca(Long codBarras) throws ErroDeNegocioException {
		
		if(!pecaRepository.existsById(codBarras)) {
			throw new ErroDeNegocioException("A peça não existe!");
		}
		pecaRepository.deleteById(codBarras);
		return true;
	}
	
	public Peca alteraPeca(Long codBarras, Peca peca) throws ErroDeNegocioException {
		
		if(!codBarras.equals(peca.getCodBarras())) {
			throw new ErroDeNegocioException("Codigo de barra não existe");
		}
		
		if(!pecaRepository.existsById(codBarras)) {
			throw new ErroDeNegocioException("A peça não existe!");
		}
		return pecaRepository.save(peca);
	}
	
	public List<Peca> listaPecasComecadasCom(String texto) {
		return pecaRepository.findAllByNomeStartingWith(texto);
	}
	
	public List<Peca> listaPecasPorModeloCarro(String modeloCarro) throws ErroDeNegocioException {
		List<Peca> pecas = pecaRepository.findAllByModeloCarro(modeloCarro);
		if(pecas.size() == 0 ) {
			throw new ErroDeNegocioException("Modelo não existe");
		}
		return pecas;
	}
	
	public List<Peca> listaPecasPorCategoria(String categoria) throws ErroDeNegocioException{
		List<Peca> pecas = pecaRepository.findAllByCategoria(categoria);
		
		if(pecas.size() == 0) {
			throw new ErroDeNegocioException("Categoria não existe");
		}
		return pecas;
	}

	

}
