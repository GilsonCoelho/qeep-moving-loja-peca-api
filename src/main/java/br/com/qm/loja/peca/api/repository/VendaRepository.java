package br.com.qm.loja.peca.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.qm.loja.peca.api.entity.Venda;


@Repository
public interface VendaRepository extends CrudRepository<Venda, Long>{

	
	@Query("select sum(v.valorVenda) from Venda v")
	Float calculaFaturamento();
	
	List<Venda> findAllByNomeVendedor(String vendedor);
	
	List<Venda> findAllByFormaPagamento(String pagamento);
	
}
