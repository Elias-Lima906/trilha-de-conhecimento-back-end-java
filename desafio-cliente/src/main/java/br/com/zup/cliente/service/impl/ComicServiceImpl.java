package br.com.zup.cliente.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
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
	
	private static final String REVISTA_APAGADA_COM_SUCESSO = "REVISTA APAGADA COM SUCESSO!";

	private static final String REVISTA_NÃO_ENCONTRADA = "REVISTA NÃO ENCONTRADA!";

	private static final String INICIANDO_PROCEDIMENTO_DE_VERIFICAÇÃO_DE_EXISTÊNCIA = "Iniciando procedimento de verificação de existência!";

	private static final String PROCESSO_FINALIZADO = "Processo finalizado com sucesso!";

	private static final String INICIANDO_SAVE_NO_BANCO_DE_DADOS = "Iniciando save no banco de dados!";

	private static final String CONEXÃO_FINALIZADA_COM_SUCESSO = "Conexão finalizada com sucesso!";

	private static final String INICIANDO_CONEXÃO_COM_API_MARVEL = "Iniciando conexão com API MARVEL!";

	private static final String INICIANDO_MÉTODO_DE_INTEGRAÇÃO_COM_API_EXTERNA = "Iniciando método de integração com API externa!";

	private final String PUBLIC_KEY = "76f2f2fec8a2c2c18a67aa059e5c0e5c";

	@Autowired
	private ComicMarvelRepository comicMarvelRepository;

	@Autowired
	private RestTemplate restTemplate;
	
	private Logger logger = Logger.getLogger(ClienteServiceImpl.class);

	@Override
	public List<ComicMarvel> listComicsFromMarvelAPI(String privateKey) {

		logger.info(INICIANDO_MÉTODO_DE_INTEGRAÇÃO_COM_API_EXTERNA);
		
		long ts = this.timeStampFactory();

		String hash = this.createHashMD5(ts, privateKey);

		UriComponents uri = this.UriBuilder(hash, ts);

		logger.info(INICIANDO_CONEXÃO_COM_API_MARVEL);
		
		List<ComicMarvel> comics = restTemplate.getForObject(uri.toUriString(), MarvelComicResponse.class).getData()
				.getResults();
		
		logger.info(CONEXÃO_FINALIZADA_COM_SUCESSO);

		return comics;
	}

	@Override
	public List<ComicMarvel> saveComic(String privateKey) {

		logger.info(INICIANDO_MÉTODO_DE_INTEGRAÇÃO_COM_API_EXTERNA);
		
		List<ComicMarvel> comics = this.listComicsFromMarvelAPI(privateKey);
		
		logger.info(CONEXÃO_FINALIZADA_COM_SUCESSO);
		
		logger.info(INICIANDO_SAVE_NO_BANCO_DE_DADOS);

		comics.stream().forEach(c -> comicMarvelRepository.save(c));
		
		logger.info(PROCESSO_FINALIZADO);

		return comics;
	}

	@Override
	public List<ComicMarvel> listAll() {
		return (List<ComicMarvel>) comicMarvelRepository.findAll();
	}

	@Override
	public Optional<ComicMarvel> getById(Long id) throws GlobalException {

		logger.info(INICIANDO_PROCEDIMENTO_DE_VERIFICAÇÃO_DE_EXISTÊNCIA);
		
		if (!comicMarvelRepository.existsById(id)) {
			throw new GlobalException(REVISTA_NÃO_ENCONTRADA);
		}

		
		logger.info(PROCESSO_FINALIZADO);
		
		return comicMarvelRepository.findById(id);
	}

	@Override
	public MensagemDTO deleteById(Long id) throws GlobalException {

		logger.info(INICIANDO_PROCEDIMENTO_DE_VERIFICAÇÃO_DE_EXISTÊNCIA);
		
		if (!comicMarvelRepository.existsById(id)) {
			throw new GlobalException(REVISTA_NÃO_ENCONTRADA);
		}

		
		comicMarvelRepository.deleteById(id);

		logger.info(PROCESSO_FINALIZADO);
		
		return new MensagemDTO(REVISTA_APAGADA_COM_SUCESSO);
	}

	private long timeStampFactory() {
		
		Long ts = new Date().getTime();

		logger.info("Criando Timestamp!");

		return ts;
	}

	private String createHashMD5(long ts, String privateKey) {

		logger.info("Criando hash!");
		
		return DigestUtils.md5Hex(ts + privateKey + PUBLIC_KEY);
	}

	private UriComponents UriBuilder(String hash, Long ts) {
		
		logger.info("Construindo URI!");

		UriComponents uri = UriComponentsBuilder.newInstance().scheme("http").host("gateway.marvel.com")
				.path("/v1/public/comics").queryParam("ts", ts).queryParam("apikey", PUBLIC_KEY)
				.queryParam("hash", hash).build();

		return uri;
	}

}
