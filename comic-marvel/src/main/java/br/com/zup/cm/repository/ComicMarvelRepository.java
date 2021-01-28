package br.com.zup.cm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.zup.cm.entity.ComicMarvel;

@Repository
public interface ComicMarvelRepository extends CrudRepository<ComicMarvel, Long> {

}
