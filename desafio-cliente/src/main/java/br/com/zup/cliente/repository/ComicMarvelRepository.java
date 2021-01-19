package br.com.zup.cliente.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.zup.cliente.entity.ComicMarvel;

@Repository
public interface ComicMarvelRepository extends CrudRepository<ComicMarvel, Long> {

}
