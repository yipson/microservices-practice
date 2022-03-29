package com.formacionbdi.springboot.app.usuarios.models.dao;

import com.formacionbdi.springboot.app.usuarios.models.entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UsuarioDao extends PagingAndSortingRepository<Usuario, Long> {

    public Usuario findByUsername(String username);

    // Personalizar consultas con JPQL
    @Query("select u from Usuario u where u.username=?1")
    public Usuario obtenerPorUsername(String username);

    // Hacer una consulata nativa a la base de datos
    @Query(value="SELECT * FROM usuarios WHERE username=?1", nativeQuery = true)
    public Usuario encontrarUsuarioPorUsername(String username);
}
