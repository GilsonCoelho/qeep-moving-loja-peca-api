package br.com.qm.loja.peca.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.qm.loja.peca.api.dto.ResponseDTO;
import br.com.qm.loja.peca.api.dto.VendaDTO;
import br.com.qm.loja.peca.api.entity.Peca;
import br.com.qm.loja.peca.api.entity.Venda;
import br.com.qm.loja.peca.api.exception.ErroDeNegocioException;
import br.com.qm.loja.peca.api.repository.VendaRepository;


@Service
public class VendaService {

	
	@Autowired
	VendaRepository vendaRepository;
	
	@Autowired
	PecaService pecaService;
	
	
	public ResponseDTO realizaVenda(VendaDTO vendaDto) throws ErroDeNegocioException {
		
		Venda venda = vendaDto.toObject();
		
		Optional<Peca> pecaOpt = pecaService.consultaPeca(venda.getCodBarras());
		
		if (pecaOpt.isEmpty()) {
			throw new ErroDeNegocioException("A venda não pode ser realizada pois a peça não existe!");
		}
		
		Peca peca = pecaOpt.get();
		
		if (peca.getQtdEstoque() < venda.getQuantidade()) {
			throw new ErroDeNegocioException(
					"A venda não pode ser realizada pois a quantidade em estoque é menor do que a requisita! Hoje há em estoque "
							+ peca.getQtdEstoque() + " peças");
		}
		
		peca.setQtdEstoque(peca.getQtdEstoque() - venda.getQuantidade());
		venda.setValorVenda(venda.getQuantidade() * peca.getPrecoVenda());
		
		pecaService.alteraPeca(peca.getCodBarras(), peca);
		vendaRepository.save(venda);
		
		return new ResponseDTO("Venda realizada com sucesso!");
	}

	public List<Venda> listarVendas() {
		return (List<Venda>) vendaRepository.findAll();
	}

	public Float calculaFaturamento() {
		return vendaRepository.calculaFaturamento();
	}

	public List<Venda> listaVendasPorVendedor(String vendedor) {
		return vendaRepository.findAllByNomeVendedor(vendedor);
	}

	public List<Venda> listaVendasPorPagamento(String formaPagamento) {
		return vendaRepository.findAllByFormaPagamento(formaPagamento);
	}

	public boolean removeVenda(Long idVenda) {
		
		if (!vendaRepository.existsById(idVenda)) {
			return false;
		}
		
		vendaRepository.deleteById(idVenda);
		return true;
	}

}