package br.com.zup.cliente.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.cliente.dto.MensagemDTO;
import br.com.zup.cliente.entity.ComicMarvel;
import br.com.zup.cliente.entity.MarvelComicResponse;
import br.com.zup.cliente.exception.GlobalException;
import br.com.zup.cliente.repository.ComicMarvelRepository;
import br.com.zup.cliente.service.ComicService;

@Service
public class ComicServiceImpl implements ComicService {

	private final String PUBLIC_KEY = "76f2f2fec8a2c2c18a67aa059e5c0e5c";

	@Autowired
	ComicMarvelRepository comicMarvelRepository;

	@Autowired
	RestTemplate restTemplate;

	@Override
	public List<ComicMarvel> listComicsFromMarvelAPI(String privateKey) {

		long ts = this.timeStampFactory();

		String hash = this.createHashMD5(ts, privateKey);

		UriComponents uri = this.UriBuilder(hash, ts);

		List<ComicMarvel> comics = restTemplate.getForObject(uri.toUriString(), MarvelComicResponse.class).getData()
				.getResults();

		return comics;
	}

	@Override
	public List<ComicMarvel> saveComic(String privateKey) {

		List<ComicMarvel> comics = this.listComicsFromMarvelAPI(privateKey);

		comics.stream().forEach(c -> comicMarvelRepository.save(c));

		return comics;
	}

	@Override
	public List<ComicMarvel> listAll() {
		return (List<ComicMarvel>) comicMarvelRepository.findAll();
	}

	@Override
	public Optional<ComicMarvel> getById(Long id) throws GlobalException {

		if (!comicMarvelRepository.existsById(id)) {
			throw new GlobalException("REVISTA NÃO ENCONTRADA!");
		}

		return comicMarvelRepository.findById(id);
	}

	@Override
	public MensagemDTO deleteById(Long id) throws GlobalException {

		if (!comicMarvelRepository.existsById(id)) {
			throw new GlobalException("REVISTA NÃO ENCONTRADA!");
		}

		comicMarvelRepository.deleteById(id);
		return new MensagemDTO("REVISTA APAGADA COM SUCESSO!");
	}

	private long timeStampFactory() {

		Long ts = new Date().getTime();

		return ts;
	}

	private String createHashMD5(long ts, String privateKey) {

		return DigestUtils.md5Hex(ts + privateKey + PUBLIC_KEY);
	}

	private UriComponents UriBuilder(String hash, Long ts) {

		UriComponents uri = UriComponentsBuilder.newInstance().scheme("http").host("gateway.marvel.com")
				.path("/v1/public/comics").queryParam("ts", ts).queryParam("apikey", PUBLIC_KEY)
				.queryParam("hash", hash).build();

		return uri;
	}

}
