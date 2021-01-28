package br.com.zup.cliente.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.cliente.dto.ClienteDTO;
import br.com.zup.cliente.dto.MensagemDTO;
import br.com.zup.cliente.dto.alteraClienteDTO;
import br.com.zup.cliente.entity.Cliente;
import br.com.zup.cliente.exception.GlobalException;
import br.com.zup.cliente.service.ClienteService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	ClienteService clienteService;

	@ApiOperation(value = "Lista todos os clientes.")
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<Cliente> listaCliente() {
		return clienteService.listaCientes();
	}

	@ApiOperation(value = "Busca um cliente por id.")
	@GetMapping(path = "/{cpf}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Optional<Cliente> buscaCliente(@PathVariable String cpf) throws GlobalException {
		return clienteService.buscaCliente(cpf);
	}

	@ApiOperation(value = "Adiciona um cliente.")
	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public Cliente adicionaCliente(@Valid @RequestBody ClienteDTO clienteDto) throws GlobalException {
		return clienteService.adicionaCliente(clienteDto);
	}

	@ApiOperation(value = "Altera um cliente.")
	@PutMapping(path = "/{cpf}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Optional<Cliente> alteraCliente(@PathVariable String cpf, @Valid @RequestBody alteraClienteDTO clienteDTO)
			throws GlobalException {
		return clienteService.alteraCliente(cpf, clienteDTO);
	}

	@ApiOperation(value = "Remove um cliente")
	@DeleteMapping(path = "/{cpf}")
	public MensagemDTO removeCliente(@PathVariable String cpf) throws GlobalException {
		return clienteService.removeCliente(cpf);
	}
}
