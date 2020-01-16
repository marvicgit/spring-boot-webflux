package martin.site.springboot.webflux.app.models.services;

import martin.site.springboot.webflux.app.models.documents.Producto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductoService {
	public Flux<Producto> findAll();
	public Flux<Producto> findAllconNombreUpperCase();
	public Flux<Producto> findAllconNombreUpperCaseRepeat();
	public Mono<Producto> findById(String id);
	public Mono<Producto> save(Producto producto);
	public Mono<Void> delete(Producto producto);
}
