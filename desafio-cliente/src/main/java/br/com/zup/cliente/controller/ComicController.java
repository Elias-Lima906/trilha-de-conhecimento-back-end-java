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
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/comics")
public class ComicController {

	@Autowired
	ComicService comicService;

	@ApiOperation(value = "Busca quadrinhos da API pública da MARVEL.")
	@GetMapping(path = "/APIMarvel", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<ComicMarvel> listComicsFromMarvelAPI(@RequestParam String privateKey)
			throws UnsupportedEncodingException, NoSuchAlgorithmException {
		return comicService.listComicsFromMarvelAPI(privateKey);
	}

	@ApiOperation(value = "Salva quandrinhos da MARVEL no bamco de dados.")
	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<ComicMarvel> saveComic(@RequestParam String privateKey) {
		return comicService.saveComic(privateKey);
	}

	@ApiOperation(value = "Lista quadrinhos do banco de dados.")
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<ComicMarvel> listAll() {
		return comicService.listAll();
	}

	@ApiOperation(value = "Busca um quadrinho pro id.")
	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Optional<ComicMarvel> getById(@PathVariable Long id) throws GlobalException {
		return comicService.getById(id);
	}

	@ApiOperation(value = "Remove um quadrinho do banco de dados.")
	@DeleteMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public MensagemDTO deleteById(@PathVariable Long id) throws GlobalException {
		return comicService.deleteById(id);
	}
}
