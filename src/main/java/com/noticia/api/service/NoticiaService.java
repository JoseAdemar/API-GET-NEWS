package com.noticia.api.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.noticia.api.exception.NoticiaException;
import com.noticia.api.model.Noticia;
import com.noticia.api.repository.NoticiaRepository;

@Service
public class NoticiaService {

	@Autowired
	private NoticiaRepository noticiaRepository;

	public List<Noticia> noticiaCapturada(Noticia noticia) throws IOException{
		
		try {
			
		String url = "https://www.infomoney.com.br/mercados/";
		Document doc = Jsoup.connect(url).get();
		System.out.println("###############################################################");
		// Pega o elemento infiniteScroll: The root to parse the News entities
		Element infiniteScroll = doc.getElementById("infiniteScroll");
		Elements rows = infiniteScroll.getElementsByClass("row py-3 item");
		List<Noticia> noticiaList = new ArrayList<>();
		int maxNews = 3; //Quantidade de noticia que vai ser exibida
		int curNews = 0; //Primeira noticia a ser exibida
		for (Element row : rows) {
			noticia = new Noticia();
			Element link = row.getElementsByClass("hl-title hl-title-2").select("a[href]").get(0);
			String url1 = link.attr("href");
			Document detail = Jsoup.connect(link.attr("href")).get();
			noticia.setSubTitulo(detail.getElementsByClass("article-lead").text());
			noticia.setUrl(url1);
			noticia.setTitulo(link.text());
			noticia.setSubTitulo(detail.getElementsByClass("article-lead").text());
			noticia.setAutor(detail.getElementsByClass("author-name").text());
			noticia.setDataPublicacao(detail.getElementsByClass("posted-on").text());
			noticia.setConteudo(
					detail.getElementsByClass("col-md-9 col-lg-8 col-xl-6  m-sm-auto m-lg-0 article-content").text());

			noticiaList.add(noticia);
			
			resultadoDaPesquisa(url1, link, detail);//Metodo apenas para imprimir no console o resultado

			curNews++;
			if (curNews >= maxNews) {
				break;
			}

		}

		noticiaRepository.saveAll(noticiaList);
		return noticiaList;
		
		
		}catch (EmptyResultDataAccessException e) {
			
			throw new NoticiaException(String.format("Erro ao salvar as informações"));
		}
	}
	
	 //Lista todas as noticias
	 public List<Noticia> listarTodasAsNoticias(){
		 return noticiaRepository.findAll();
		 
	 }
	 
	 //Lista noticia por ID
     public Noticia listarNoticiaPorId(Long id) {
    		return noticiaRepository.findById(id)
    				.orElseThrow(() -> new NoticiaException(id));
	 }
     
		// Deleta noticia por ID
		public void deletarNoticiaPorId(Long id) {
			try {
				noticiaRepository.deleteById(id);

			} catch (EmptyResultDataAccessException e) {
				throw new NoticiaException
				(String.format("Não foi encontrado dados para o ID: " + id));
			}
		}
		
		
		//Atualizar uma noticia por Id
		public Noticia atualizarNoticia(Noticia noticia, Long id) {
			
			try {
			Noticia noticias = noticiaRepository.findById(id).get();
			if(noticias != null) {
				
			     BeanUtils.copyProperties(noticia, noticias, "id");
			     
			     return noticias = noticiaRepository.save(noticias);
			}
			}catch (RuntimeException e ) {
				
			   throw new  NoticiaException
			   (String.format("Não foi encontrado dados para o ID: " + id));
			}
			return noticia;
			
			
		}
	
	// Metodo para imprimir o resultado da pesquisa
	private void resultadoDaPesquisa(String url1, Element link, Document detail) {
		System.out.println("URL: " + url1);
		System.out.println("Título: " + link.text());
		System.out.println("Sub-Titulo: " + detail.getElementsByClass("article-lead").text());
		System.out.println("Autor: " + detail.getElementsByClass("author-name").text());
		System.out.println("Data: " + detail.getElementsByClass("posted-on").text());
		System.out.println("Conteúdo: "
				+ detail.getElementsByClass("col-md-9 col-lg-8 col-xl-6  m-sm-auto m-lg-0 article-content").text());
		System.out.println("###################################################################");
	}

}
