package br.com.alura.mvc.mudi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching// necessário colcar essa bean para o cache funcionar
@SpringBootApplication
public class MudiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MudiApplication.class, args);
	}

}
//EnableCaching- O cache realmente é inicializado na hora em que a nossa aplicação sobe.

//E o que acontece? Vamos acompanhar o log de novo, porque eu vou abrir a página home mais uma vez.
// E aconteceu o que é esperado, ele vai no banco de dados e faz o select ? NÃO
//uando eu volto para a página e faço uma atualização da página home, várias vezes, era para termos
// várias consultas ao banco, mas não. Ele cacheou aquele conteúdo.

/**
 * Qual é a vantagem e quando aplicar o cache na nossa aplicação?
 * Utilizar cache neste cenário pode não apenas reduzir o custo financeiro
 * da aplicação, como liberar capacidade de processamento para outras funcionalidades
 * possivelmente mais importantes.
 *
 * Separar a página pública (sem login) das páginas restritas
 * Redirecionar após logout para a página home
 * Trabalhar com paginação e ordenação, usando as classes Pageable e Sort
 * Usar o cache na aplicação para melhorar o desempenho usando a anotação @Cacheable
 */