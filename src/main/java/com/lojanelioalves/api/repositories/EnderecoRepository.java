package com.lojanelioalves.api.repositories;

import com.lojanelioalves.api.entities.Cidade;
import com.lojanelioalves.api.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
