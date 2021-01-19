package br.com.zup.cliente.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import br.com.zup.cliente.entity.MarvelResponse;

public interface ComicService {

	public MarvelResponse listComics() throws UnsupportedEncodingException, NoSuchAlgorithmException;

}
