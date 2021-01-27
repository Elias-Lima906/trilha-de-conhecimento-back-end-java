package br.com.zup.cliente.test;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;

import br.com.zup.cliente.dto.ClienteDTO;
import br.com.zup.cliente.dto.MensagemDTO;
import br.com.zup.cliente.dto.alteraClienteDTO;
import br.com.zup.cliente.entity.Cliente;
import br.com.zup.cliente.exception.GlobalException;
import br.com.zup.cliente.repository.ClienteRepository;
import br.com.zup.cliente.service.impl.ClienteServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class CostumerMethodTest {

	private static final String CLIENTE_JÁ_EXISTE = "CLIENTE JÁ EXISTE NO BANCO DE DADOS!";

	private static final String CLIENTE_REMOVIDO_COM_SUCESSO = "CLIENTE REMOVIDO COM SUCESSO";

	private static final String CLIENTE_NÃO_ENCONTRADO = "CLIENTE NÃO ENCONTRADO!";

	@Mock
	ClienteRepository clienteRepository;

	@InjectMocks
	ClienteServiceImpl costumerServiceImpl;

	@Test
	public void shouldFindCostumerById() throws GlobalException {

		Optional<Cliente> costumer = Optional.of(this.costumerFactory());
		
		Mockito.when(clienteRepository.existsById("46063610855")).thenReturn(true);
		Mockito.when(clienteRepository.findById("46063610855")).thenReturn(costumer);

		Optional<Cliente> returnedCostumer = costumerServiceImpl.buscaCliente("46063610855");

		Assert.assertEquals(costumer.get(), returnedCostumer.get());
	}

	@Test
	public void shouldNotFindCostumerById() {

		Mockito.when(clienteRepository.existsById("46063610855")).thenReturn(false);

		String expectedMessage = CLIENTE_NÃO_ENCONTRADO;
		String returnedMessage = null;

		try {
			costumerServiceImpl.buscaCliente("46063610855");
		} catch (GlobalException e) {
			returnedMessage = e.getMensagemErro();
		}

		Assert.assertEquals(returnedMessage, expectedMessage);
	}

	@Test
	public void shouldSaveACostumer() throws GlobalException {

		ClienteDTO costumerDto = this.costumerDTOFactory();
		Cliente expectedCostumer = new Cliente();

		Mockito.when(clienteRepository.existsById("46063610855")).thenReturn(false);

		Cliente returnedCostumer = costumerServiceImpl.adicionaCliente(costumerDto);

		BeanUtils.copyProperties(costumerDto, expectedCostumer);

		Assert.assertEquals(expectedCostumer, returnedCostumer);
	}

	@Test
	public void shouldNotSaveACostumer() {

		ClienteDTO costumerDto = this.costumerDTOFactory();

		Mockito.when(clienteRepository.existsById("46063610855")).thenReturn(true);

		String expectedMessage = CLIENTE_JÁ_EXISTE;
		String returnedMessage = null;

		try {
			costumerServiceImpl.adicionaCliente(costumerDto);
		} catch (GlobalException e) {
			returnedMessage = e.getMensagemErro();
		}

		Assert.assertEquals(expectedMessage, returnedMessage);
	}

	@Test
	public void shouldUpdateACostumer() throws GlobalException {

		alteraClienteDTO costumerDto = this.buildDTOCostumer();
		Optional<Cliente> costumer = Optional.of(this.costumerFactory());

		Mockito.when(clienteRepository.existsById("46063610855")).thenReturn(true);
		Mockito.when(clienteRepository.findById("46063610855")).thenReturn(costumer);

		Optional<Cliente> returnedCostumer = costumerServiceImpl.alteraCliente("46063610855", costumerDto);
		Cliente expectedCostumer = new Cliente();

		BeanUtils.copyProperties(costumerDto, expectedCostumer);
		expectedCostumer.setCpf("46063610855");

		Assert.assertEquals(expectedCostumer, returnedCostumer.get());
	}

	@Test
	public void shouldNotUpdateACostumer() {

		alteraClienteDTO costumerDto = this.buildDTOCostumer();

		Mockito.when(clienteRepository.existsById("46063610855")).thenReturn(false);

		String expectedMessage = CLIENTE_NÃO_ENCONTRADO;
		String returnedMessage = null;

		try {
			costumerServiceImpl.alteraCliente("46063610855", costumerDto);
		} catch (GlobalException e) {
			returnedMessage = e.getMensagemErro();
		}

		Assert.assertEquals(expectedMessage, returnedMessage);
	}

	@Test
	public void shouldRomoveCostumerById() throws GlobalException {

		Mockito.when(clienteRepository.existsById("46063610855")).thenReturn(true);

		MensagemDTO returnedMessage = costumerServiceImpl.removeCliente("46063610855");
		MensagemDTO expectedMessage = new MensagemDTO(CLIENTE_REMOVIDO_COM_SUCESSO);

		Assert.assertEquals(expectedMessage, returnedMessage);
	}

	@Test
	public void shouldNotRomoveCostumerById() {

		Mockito.when(clienteRepository.existsById("46063610855")).thenReturn(false);

		String expectedMessage = CLIENTE_NÃO_ENCONTRADO;
		String returnedMessage = null;

		try {
			costumerServiceImpl.removeCliente("46063610855");
		} catch (GlobalException e) {
			returnedMessage = e.getMensagemErro();
		}

		Assert.assertEquals(expectedMessage, returnedMessage);
	}

	private Cliente costumerFactory() {

		Cliente costumer = new Cliente();

		costumer.setCpf("46063610855");
		costumer.setNome("Elias Lima");
		costumer.setIdade(21);
		costumer.setEmail("eliaslima@mail.com");
		costumer.setTelefone("19983792017");
		costumer.setEndereco("Rua 8, 40");

		return costumer;
	}

	private ClienteDTO costumerDTOFactory() {

		ClienteDTO costumerDto = new ClienteDTO();

		costumerDto.setCpf("46063610855");
		costumerDto.setNome("Elias Lima");
		costumerDto.setIdade(21);
		costumerDto.setEmail("eliaslima@mail.com");
		costumerDto.setTelefone("19983792017");
		costumerDto.setEndereco("Rua 8, 40");

		return costumerDto;
	}

	private alteraClienteDTO buildDTOCostumer() {

		alteraClienteDTO updateCostumerDTO = new alteraClienteDTO();

		updateCostumerDTO.setNome("Elia Pereira De Lima");
		updateCostumerDTO.setIdade(22);
		updateCostumerDTO.setEmail("elias.lima@mail.com");
		updateCostumerDTO.setTelefone("019983792017");
		updateCostumerDTO.setEndereco("Rua 8, N° 40");

		return updateCostumerDTO;
	}

}
