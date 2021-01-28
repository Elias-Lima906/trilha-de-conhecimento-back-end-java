package br.com.zup.cliente;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.HttpClientErrorException;

import br.com.zup.cm.entity.ComicMarvel;
import br.com.zup.cm.service.impl.ComicServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class IntegrationMarvelAPIMethodTest {

	@Mock
	ComicServiceImpl comicServiceImpl;

	@Test
	public void shouldListComicsFromMarvelAPI() {

		List<ComicMarvel> results = new ArrayList<ComicMarvel>();

		results.add(this.comicFactory());

		Mockito.when(comicServiceImpl.listComicsFromMarvelAPI("")).thenReturn(results);

		List<ComicMarvel> returnedComics = comicServiceImpl.listComicsFromMarvelAPI("");

		List<ComicMarvel> expectedComics = new ArrayList<ComicMarvel>();

		expectedComics.add(this.comicFactory());

		Assert.assertEquals(expectedComics.get(0), returnedComics.get(0));
	}

	@Test(expected = HttpClientErrorException.class)
	public void shouldNotListMarvelAPIComicsIfHashHasNotBeenBuiltCorrectly() {

		Mockito.when(comicServiceImpl.listComicsFromMarvelAPI(""))
				.thenThrow(HttpClientErrorException.Unauthorized.class);

		comicServiceImpl.listComicsFromMarvelAPI("");
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
