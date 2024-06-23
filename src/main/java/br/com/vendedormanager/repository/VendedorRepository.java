package br.com.vendedormanager.repository;


import br.com.vendedormanager.model.Vendedor;
import org.springframework.data.repository.CrudRepository;

public interface VendedorRepository extends CrudRepository<Vendedor, Long> {
    Vendedor findByMatricula(String matricula);
}
