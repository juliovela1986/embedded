package com.prueba.postgres.embedded.infraestructure.dao;

import com.prueba.postgres.embedded.infraestructure.entity.Prueba;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPruebaDao extends JpaRepository<Prueba, Long> {

    @Modifying
    @Query("update Prueba set  code = :code, description = :description, isDefault = :isDefault, lastModifiedBy = :idUser,"
            + " active = :active, lastModifiedDate = CURRENT_TIMESTAMP where idPrueba = :idPrueba")
    public void updatePruebaById(   @Param ("code") String code,
                                    @Param("description") String description,
                                    @Param("isDefault") boolean isDefault, @Param("idPrueba") long idPrueba, @Param("idUser") Long idUser,
                                    @Param("active") boolean active);

    @Query("select count(1) from Prueba where code = :code")
    public long pruebaExist(@Param("code") String code);

    @Query("select count(1) from Prueba where idPrueba = :idPrueba")
    public long findByIdPrueba(@Param("idPrueba") long idPrueba);

    @Query("SELECT idPrueba FROM Prueba WHERE idPrueba IN (:idPruebas)")
    public List<Long> existingPruebas(@Param("idPruebas") List<Long> idPruebas);

    @Modifying()
    @Query("update Prueba set isDefault = false, lastModifiedBy = :idUser, lastModifiedDate = CURRENT_TIMESTAMP"
            + " where idPrueba != :idPrueba and isDefault = true")
    public void updatePruebaDefault(@Param("idPrueba") long idPrueba, @Param("idUser") Long idUser);

    @Query("select idLanguage from Language where code = :code")
    public long obtainIdByCode(@Param("code") String code);

    @Query("select count(1) from Prueba where isDefault = true and idPrueba <> :idPrueba")
    public long validatePruebaDefault(@Param("idPrueba") long idPrueba);
}
