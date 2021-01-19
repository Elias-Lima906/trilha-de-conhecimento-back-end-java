package br.com.zup.cliente.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import br.com.zup.cliente.dto.MensagemDTO;
import br.com.zup.cliente.entity.ComicMarvel;
import br.com.zup.cliente.exception.GlobalException;

public interface ComicService {

	public List<ComicMarvel> listComics(String privateKey) throws UnsupportedEncodingException, NoSuchAlgorithmException;

	public List<ComicMarvel> saveComic(String privateKey);
	
	public List<ComicMarvel> listAll();

	public Optional<ComicMarvel> getById(Long id) throws GlobalException;
	
	public MensagemDTO deleteById(Long id) throws GlobalException;
	
}
