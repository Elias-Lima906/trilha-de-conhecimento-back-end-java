package br.com.zup.cm.entity;
public class MarvelComicResponse {

	private MarvelComicDataResponse data;

	public MarvelComicResponse() {
		this.data = new MarvelComicDataResponse();
	}

	public MarvelComicDataResponse getData() {
		return data;
	}

	public void setData(MarvelComicDataResponse data) {
		this.data = data;
	}

}
