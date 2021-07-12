package br.com.qm.loja.peca.api.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.qm.loja.peca.api.entity.Peca;


@Repository
public interface PecaRepository extends CrudRepository<Peca, Long> {
	
	List<Peca> findAllByNomeStartingWith(String texto);
	List<Peca> findAllByModeloCarro(String modeloCarro);
	List<Peca> findAllByCategoria(String categoria);	

}
