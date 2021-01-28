package br.com.zup.cliente.test;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.zup.cm.dto.MensagemDTO;
import br.com.zup.cm.entity.ComicMarvel;
import br.com.zup.cm.exception.GlobalException;
import br.com.zup.cm.repository.ComicMarvelRepository;
import br.com.zup.cm.service.impl.ComicServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class MarvelComicMethodTest {

	private static final String REVISTA_APAGADA_COM_SUCESSO = "REVISTA APAGADA COM SUCESSO!";

	private static final String REVISTA_NÃO_ENCONTRADA = "REVISTA NÃO ENCONTRADA!";

	@Mock
	ComicMarvelRepository comicMarvelRepository;

	@InjectMocks
	ComicServiceImpl comicServiceImpl;

	@Test
	public void shouldFindComicsById() throws GlobalException {

		Optional<ComicMarvel> comic = Optional.of(this.comicFactory());

		Mockito.when(comicMarvelRepository.existsById(1308L)).thenReturn(true);
		Mockito.when(comicMarvelRepository.findById(1308L)).thenReturn(comic);

		Optional<ComicMarvel> returnedcomic = comicServiceImpl.getById(1308L);

		Assert.assertEquals(comic.get(), returnedcomic.get());
	}

	@Test
	public void shouldNotFindComicsById() {

		Mockito.when(comicMarvelRepository.existsById(1308L)).thenReturn(false);

		String expectedMessage = REVISTA_NÃO_ENCONTRADA;
		String returnedMessage = null;

		try {
			comicServiceImpl.getById(1308L);
		} catch (GlobalException e) {
			returnedMessage = e.getMensagemErro();
		}

		Assert.assertEquals(returnedMessage, expectedMessage);
	}

	@Test
	public void shouldRemoveAComicById() throws GlobalException {

		Mockito.when(comicMarvelRepository.existsById(1308L)).thenReturn(true);

		MensagemDTO returnedMessage = comicServiceImpl.deleteById(1308L);
		MensagemDTO expectedMessage = new MensagemDTO(REVISTA_APAGADA_COM_SUCESSO);

		Assert.assertEquals(expectedMessage, returnedMessage);
	}

	@Test
	public void shouldNotRemoveAComicById() {

		Mockito.when(comicMarvelRepository.existsById(1308L)).thenReturn(false);

		String expectedMessage = REVISTA_NÃO_ENCONTRADA;
		String returnedMessage = null;

		try {
			comicServiceImpl.deleteById(1308L);
		} catch (GlobalException e) {
			returnedMessage = e.getMensagemErro();
		}

		Assert.assertEquals(expectedMessage, returnedMessage);
	}

	private ComicMarvel comicFactory() {

		ComicMarvel comic = new ComicMarvel();

		comic.setId(1308L);
		comic.setTitle("Doctor Strange and the multiverse of madness");
		comic.setDescription("A crazy multi-verse full of Madness");
		comic.setModified("");
		comic.setDiamondCode("");
		comic.setEan("");
		comic.setFormat("comic");
		comic.setPageCount(97);

		return comic;
	}

}
