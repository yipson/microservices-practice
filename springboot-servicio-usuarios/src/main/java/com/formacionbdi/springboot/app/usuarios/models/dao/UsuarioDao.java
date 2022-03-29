package com.formacionbdi.springboot.app.usuarios.models.dao;

import com.formacionbdi.springboot.app.commons.usuarios.models.entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path="usuarios")
public interface UsuarioDao extends PagingAndSortingRepository<Usuario, Long> {

    // Personalizar nombre del endpoint
    @RestResource(path="buscar-username")
    public Usuario findByUsername(@Param("nombre") String username);

    // Personalizar consultas con JPQL
    @Query("select u from Usuario u where u.username=?1")
    public Usuario obtenerPorUsername(String username);

    // Hacer una consulata nativa a la base de datos
    @Query(value="SELECT * FROM usuarios WHERE username=?1", nativeQuery = true)
    public Usuario encontrarUsuarioPorUsername(String username);
}
