package com.kroton.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

//	@Query(value = "select u from Usuario u where u.conta <= :cconta ")
//	Usuario findByConta(@Param("cconta") String conta);
	@Transactional(readOnly = true)
	Usuario findByConta(String conta);

}
