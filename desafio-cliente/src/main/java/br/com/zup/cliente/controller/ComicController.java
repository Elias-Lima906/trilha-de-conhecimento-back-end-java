package br.com.zup.cliente.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.cliente.dto.MensagemDTO;
import br.com.zup.cliente.entity.ComicMarvel;
import br.com.zup.cliente.exception.GlobalException;
import br.com.zup.cliente.service.ComicService;

@RestController
@RequestMapping("/comics")
public class ComicController {

	@Autowired
	ComicService comicService;

	@GetMapping(path = "/APIMarvel", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<ComicMarvel> listComics(@RequestParam String privateKey) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		return comicService.listComics(privateKey);
	}
	
	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<ComicMarvel> saveComic(@RequestParam String privateKey) {
		return comicService.saveComic(privateKey);
	}
	
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<ComicMarvel> listAll(){
		return comicService.listAll();
	}
	
	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Optional<ComicMarvel> getById(@PathVariable Long id) throws GlobalException {
		return comicService.getById(id);
	}
	
	@DeleteMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public MensagemDTO deleteById(@PathVariable Long id) throws GlobalException {
		return comicService.deleteById(id);
	}
}
