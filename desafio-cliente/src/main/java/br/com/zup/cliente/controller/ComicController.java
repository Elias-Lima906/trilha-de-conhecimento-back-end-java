package br.com.zup.cliente.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.cliente.entity.MarvelResponse;
import br.com.zup.cliente.service.ComicService;

@RestController
@RequestMapping("/comics")
public class ComicController {

	@Autowired
	ComicService comicService;

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public MarvelResponse listComics() throws UnsupportedEncodingException, NoSuchAlgorithmException {
		return comicService.listComics();
	}
}
