package br.com.qm.loja.peca.api.service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.qm.loja.peca.api.dto.ResponseDTO;
import br.com.qm.loja.peca.api.dto.VendaDTO;
import br.com.qm.loja.peca.api.entity.Peca;
import br.com.qm.loja.peca.api.entity.Venda;
import br.com.qm.loja.peca.api.exception.ErroDeNegocioException;
import br.com.qm.loja.peca.api.repository.VendaRepository;

@RunWith(MockitoJUnitRunner.class)
//Criei uma classe para testar a classe de serviço de vendas.
public class VendaServiceTests {
	// A primeira anotação diferente que vemos aqui é essa @Mock
	// que serve para identificar que a classe abaixo será mockada,
	// ou seja, nós iremos simular o retorno de seus métodos.
	@Mock
	PecaService pecaService;
	
	@Mock
	VendaRepository vendaRepository;
	// Já a anotação injectMocks serve para dizermos que a classe abaixo,
	// quando for instanciada terá os mocks injetados nela, fazemos isso
	// por que pecaService e vendaRepository são
	// objetos usados dentro da classe vendaService que nós queremos simular
	// os comportamentos pois o intuito desta classe de testes é
	// testar somente o comportamente dos métodos da classe
	// VendaService. Estamos, no fim de tudo, à isolando para
	// atingir o nosso objetivo de testar a mínima parte do código.
	@InjectMocks
	VendaService vendaService;

	@Test
	public void deveRealizarUmaVenda() throws ErroDeNegocioException {
		Peca peca = new Peca();
		peca.setCodBarras(111L);
		peca.setFabricante("VW");
		peca.setModeloCarro("Polo");
		peca.setNome("Volante");
		peca.setQtdEstoque(10);
		peca.setPrecoCusto(100.5F);
		peca.setPrecoVenda(500.3F);
		peca.setCategoria("Acessorios");
		
		VendaDTO vendaDto = new VendaDTO();
		vendaDto.setIdVenda(1L);
		vendaDto.setDataVenda(LocalDate.now());
		vendaDto.setNomeVendedor("João");
		vendaDto.setFormaPagamento("Cartão");
		vendaDto.setCodBarras(111L);
		vendaDto.setQuantidade(10);
		
		// Aqui está o pulo do gato. Estamos dizendo, através desse método
		// que quando fizermos uma chamada ao método consultaPeca nós deveremos
		// retornar a peça que foi criada à cima.
		Mockito.when(pecaService.consultaPeca(111L)).thenReturn(Optional.of(peca));
		
		// Há também uma chamada ao alteraPeca dentro do método realizaVenda,
		// portanto, nós também mockamos um retorno para essa chamada.
		Mockito.when(pecaService.alteraPeca(Mockito.anyLong(), Mockito.any(Peca.class)))
				.thenReturn(peca);
		
		ResponseDTO mensagemRetornada = this.vendaService.realizaVenda(vendaDto);
		ResponseDTO mensagemEsperada = new ResponseDTO("Venda realizada com sucesso!");
		Assert.assertEquals("A mensagem retornada deve ser indicativo de sucesso", mensagemEsperada, mensagemRetornada);
	}
	
	@Test(expected = ErroDeNegocioException.class)
	public void naoDeveRealizarUmaVendaDeUmaPecaInexistente() throws ErroDeNegocioException {
		
		VendaDTO vendaDto = new VendaDTO();
		vendaDto.setIdVenda(1L);
		vendaDto.setDataVenda(LocalDate.now());
		vendaDto.setNomeVendedor("João");
		vendaDto.setFormaPagamento("Cartão");
		vendaDto.setCodBarras(111L);
		vendaDto.setQuantidade(10);
	
		
		Optional<Peca> peca = Optional.empty();
		Mockito.when(pecaService.consultaPeca(111L)).thenReturn(peca);
		
		vendaService.realizaVenda(vendaDto);
	}
	
	@Test(expected = ErroDeNegocioException.class)
	public void naoDeveRealizarUmaVendaQuandoAQuantidadeForMaiorQueOEstoque() throws ErroDeNegocioException {
		
		Peca peca = new Peca();
		peca.setCodBarras(111L);
		peca.setFabricante("VW");
		peca.setModeloCarro("Polo");
		peca.setNome("Volante");
		peca.setQtdEstoque(10);
		peca.setPrecoCusto(100.5F);
		peca.setPrecoVenda(500.3F);
		peca.setCategoria("Acessorios");
		
		VendaDTO vendaDto = new VendaDTO();
		vendaDto.setIdVenda(1L);
		vendaDto.setDataVenda(LocalDate.now());
		vendaDto.setNomeVendedor("João");
		vendaDto.setFormaPagamento("Cartão");
		vendaDto.setCodBarras(111L);
		vendaDto.setQuantidade(11);
	
		
		Mockito.when(pecaService.consultaPeca(111L)).thenReturn(Optional.of(peca));
		
		vendaService.realizaVenda(vendaDto);
	}
	
	@Test
	public void deveListarVendas() {
		
		VendaDTO vendaDto = new VendaDTO();
		vendaDto.setIdVenda(1L);
		vendaDto.setDataVenda(LocalDate.now());
		vendaDto.setNomeVendedor("João");
		vendaDto.setFormaPagamento("Cartão");
		vendaDto.setCodBarras(111L);
		vendaDto.setQuantidade(11);
		
		
		List<Venda> vendas = Collections.singletonList(vendaDto.toObject());
		Mockito.when(vendaRepository.findAll()).thenReturn(vendas);
		List<Venda> vendasRetornadas = vendaService.listarVendas();
		
		Assert.assertEquals(vendas, vendasRetornadas);
	}
	
	@Test
	public void deveRemoverUmaVendaComSucesso() {
		
		Mockito.when(vendaRepository.existsById(14L)).thenReturn(true);
		boolean retorno = vendaService.removeVenda(14L);
		
		Assert.assertTrue(retorno);
	}
	
	@Test
	public void naoDeveRemoverUmaVendaNaoExistente() {
		
		Mockito.when(vendaRepository.existsById(14L)).thenReturn(false);
		boolean retorno = vendaService.removeVenda(14L);
		
		Assert.assertFalse(retorno);
	}
	
}
