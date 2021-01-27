package br.com.zup.cliente.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.zup.cliente.entity.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, String> {

}
