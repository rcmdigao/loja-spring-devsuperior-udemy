package com.lojanelioalves.api.repositories;

import com.lojanelioalves.api.entities.Categoria;
import com.lojanelioalves.api.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
