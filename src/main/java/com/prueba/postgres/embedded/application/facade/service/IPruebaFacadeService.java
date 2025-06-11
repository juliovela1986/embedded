package com.prueba.postgres.embedded.application.facade.service;

import com.prueba.postgres.embedded.infraestructure.entity.Prueba;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface IPruebaFacadeService {

    Page<Prueba> getAllPrueba(Pageable pageable);
    
    void updatePruebaById(@Param("code") String code, @Param("description") String description,
                                    @Param("isDefault") boolean isDefault, @Param("idPrueba") long idPrueba, @Param("idUser") Long idUser,
                                    @Param("active") boolean active);

    void insertNewPrueba(Prueba prueba);

    void deletePruebaById(Long idPrueba);

    long pruebaExist(@Param("code") String code);

    long findByIdPrueba(@Param("idPrueba") long idPrueba);

    List<Long> existingPruebas(List<Long> idPruebas);

    void updatePruebaDefault(@Param("idPrueba") long idPrueba,  @Param("idUser") Long idUser);

    public long obtainIdByCode(@Param("code") String code);

    public long validatePruebaDefault(@Param("idPrueba") long idPrueba);
}
