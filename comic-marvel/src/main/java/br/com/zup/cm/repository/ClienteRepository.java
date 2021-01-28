package br.com.zup.cm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.zup.cm.entity.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, String> {

}
