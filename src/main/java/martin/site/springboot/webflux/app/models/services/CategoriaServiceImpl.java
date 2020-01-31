package martin.site.springboot.webflux.app.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import martin.site.springboot.webflux.app.models.dao.CategoriaDao;
import martin.site.springboot.webflux.app.models.documents.Categoria;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	private CategoriaDao dao;

	@Override
	public Flux<Categoria> findAll() {
		return dao.findAll();
	}

	@Override
	public Mono<Categoria> findById(String id) {
		return dao.findById(id);
	}

	@Override
	public Mono<Categoria> save(Categoria categoria) {
		return dao.save(categoria);
	}


}
