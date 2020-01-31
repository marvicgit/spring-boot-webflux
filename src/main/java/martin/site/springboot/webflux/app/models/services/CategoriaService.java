package martin.site.springboot.webflux.app.models.services;

import martin.site.springboot.webflux.app.models.documents.Categoria;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CategoriaService {
	public Flux<Categoria> findAll();
	public Mono<Categoria> findById(String id);
	public Mono<Categoria> save(Categoria categoria);
}
