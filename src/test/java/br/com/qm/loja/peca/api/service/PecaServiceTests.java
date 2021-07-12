package br.com.qm.loja.peca.api.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.qm.loja.peca.api.entity.Peca;
import br.com.qm.loja.peca.api.exception.ErroDeNegocioException;
import br.com.qm.loja.peca.api.repository.PecaRepository;


@RunWith(MockitoJUnitRunner.class)
public class PecaServiceTests {
	
	
	@Mock
	PecaRepository pecaRepository;
	
	@InjectMocks
	PecaService pecaService;
	
	@Test
	public void deveCadastrarUmaPecaComSucesso() throws ErroDeNegocioException {
		
		Peca peca = new Peca();
		peca.setCodBarras(55L);
		
		Mockito.when(pecaRepository.existsById(55L)).thenReturn(false);
		Mockito.when(pecaRepository.save(peca)).thenReturn(peca);
		
		Peca pecaRetornada = pecaService.cadastraPeca(peca);
		
		Assert.assertEquals(peca, pecaRetornada);		
		
	}
	
	@Test
	public void naoDeveCadastrarUmaPecaExistente()throws ErroDeNegocioException {
		Peca peca = new Peca();
		peca.setCodBarras(55L);
		
		Mockito.when(pecaRepository.existsById(55L)).thenReturn(true);
		
		String mensagemDeErroEsperada = "A peça já existe!";
		String mensagemRetornada = null;
		
		try {
			pecaService.cadastraPeca(peca);
		}catch (ErroDeNegocioException e) {
			mensagemRetornada = e.getMessage();
		}
		
		Assert.assertEquals(mensagemDeErroEsperada, mensagemRetornada);
	}
	
	@Test
	public void deveAlterarUmaPecaComSucesso()throws ErroDeNegocioException {
		Peca peca = new Peca();
		peca.setCodBarras(55L);
		
		Mockito.when(pecaRepository.existsById(55L)).thenReturn(true);
		Mockito.when(pecaRepository.save(Mockito.any(Peca.class))).thenReturn(peca);
		
		Peca pecaRetornada = pecaService.alteraPeca(55L, peca);
		Assert.assertEquals(peca, pecaRetornada);
	}
	
	@Test
	public void naoDeveAlterarUmaPecaCasoIdSejaDiferenteNoRecurso() {
		
		Peca peca = new Peca();
		peca.setCodBarras(55L);
		
		String mensagemDeErroEsperada = "O id da peça do recurso é diferente do corpo!";
		String mensagemRetornada = null;
		
		try {
			pecaService.alteraPeca(44L, peca);
		}catch(ErroDeNegocioException e) {
			mensagemRetornada = e.getMessage();
		}
		
		Assert.assertEquals(mensagemDeErroEsperada, mensagemRetornada);
	}
	
	@Test
	public void naoDeveAlterarUmaPecaInexistente(){
		
		Peca peca = new Peca();
		peca.setCodBarras(55L);
		
		String mensagemDeErroEsperada = "A peça não existe";
		String mensagemRetornada = null;
		
		Mockito.when(pecaRepository.existsById(55L)).thenReturn(false);
		
		try {
			pecaService.alteraPeca(55L, peca);
		}catch(ErroDeNegocioException e) {
			mensagemRetornada = e.getMessage();
		}
		
		Assert.assertEquals(mensagemDeErroEsperada, mensagemRetornada);
	}
	
	@Test
	public void deveRemoverUmaPecaComSucesso() {
		
		String mensagemDeErroEsperada = "A peça não existe!";
		String mensagemRetornada = null;
		Mockito.when(pecaRepository.existsById(55L)).thenReturn(true);
		
		try {
			pecaService.removePeca(55L);
		} catch (ErroDeNegocioException e) {
			mensagemRetornada = e.getMessage();
		}
		
		Assert.assertEquals(mensagemDeErroEsperada, mensagemRetornada);
		
	}
	
	@Test
	public void naoDeveRemoverUmaPecaInexistente() {
		
		String mensagemDeErroEsperada = "A peça não existe!";
		String mensagemRetornada = null;
		Mockito.when(pecaRepository.existsById(55L)).thenReturn(false);
		
		try {
			pecaService.removePeca(55L);
		} catch (ErroDeNegocioException e) {
			mensagemRetornada = e.getMessage();
		}
		
		Assert.assertEquals(mensagemDeErroEsperada, mensagemRetornada);
		
	}
	

}
