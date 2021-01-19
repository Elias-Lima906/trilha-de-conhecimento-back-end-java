package br.com.zup.cliente.service.impl;

import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.cliente.entity.MarvelResponse;
import br.com.zup.cliente.service.ComicService;

@Service
public class ComicServiceImpl implements ComicService {

	private final String PUBLIC_KEY = "76f2f2fec8a2c2c18a67aa059e5c0e5c";

	private final String PRIVATE_KEY = "f2d052d47217ee846e559ec321760f162b18631b";

	@Override
	public MarvelResponse listComics() {

		RestTemplate restTemplate = new RestTemplate();
		long ts = this.timeStampFactory();

		String hash = this.createHashMD5(ts);

		UriComponents uri = this.UriBuilder(hash, ts);

		return restTemplate.getForObject(uri.toUriString(), MarvelResponse.class);
	}

	private long timeStampFactory() {

		Long ts = new Date().getTime();

		return ts;
	}

	private String createHashMD5(long ts) {

		return DigestUtils.md5Hex(ts + PRIVATE_KEY + PUBLIC_KEY);
	}

	private UriComponents UriBuilder(String hash, Long ts) {

		UriComponents uri = UriComponentsBuilder.newInstance()
				.scheme("http")
				.host("gateway.marvel.com")
				.path("/v1/public/comics")
				.queryParam("ts", ts)
				.queryParam("apikey", PUBLIC_KEY)
				.queryParam("hash", hash)
				.build();

		return uri;
	}
}
