package br.com.qintess.apinetshoes.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import br.com.qintess.apinetshoes.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

	Page<Produto> findByNome(String nomeProduto, Pageable paginacao);
	
//	@Query("SELECT p FROM Produto p WHERE p.nome = :nomeProduto")
//	List<Produto> carregarPorNomeDoProduto(@Param("nomeProduto") String nomeProduto);
	
}
