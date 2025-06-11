package com.prueba.postgres.embedded.infraestructure.facade.service.impl;

import java.util.List;

import com.prueba.postgres.embedded.application.facade.service.IPruebaFacadeService;
import com.prueba.postgres.embedded.infraestructure.dao.IPruebaDao;
import com.prueba.postgres.embedded.infraestructure.entity.Prueba;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class PruebaFacadeServiceImpl implements IPruebaFacadeService {

  private final IPruebaDao pruebaDao;

  public PruebaFacadeServiceImpl(IPruebaDao pruebaDao) {
    this.pruebaDao = pruebaDao;
  }

  @Override
  @Transactional(readOnly = true)
  public Page<Prueba> getAllPrueba(Pageable pageable) {
    return pruebaDao.findAll(pageable);
  }

  @Override
  @Transactional
  public void updatePruebaById(String code, String description, boolean isDefault, long idPrueba, Long idUser,
                                  boolean active) {
    pruebaDao.updatePruebaById(code, description, isDefault, idPrueba,idUser, active);
  }

  @Override
  @Transactional
  public void insertNewPrueba(Prueba prueba) {
    pruebaDao.save(prueba);
  }

  @Override
  public void deletePruebaById(Long idPrueba) {
    pruebaDao.deleteById(idPrueba);
  }

//  @Override
//  @Transactional(readOnly = true)
//  public long countByCode(long code) {
//    return pruebaDao.countByCode(code);
//  }

  @Override
  @Transactional(readOnly = true)
  public long pruebaExist(String code) {
    return pruebaDao.pruebaExist(code);
  }

  @Override
  @Transactional(readOnly = true)
  public long findByIdPrueba(long idPrueba) {
    return pruebaDao.findByIdPrueba(idPrueba);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Long> existingPruebas(List<Long> idPruebas) {
    return pruebaDao.existingPruebas(idPruebas);
  }

  @Override
  @Transactional
  public void updatePruebaDefault(long idPrueba, Long idUser) {
    pruebaDao.updatePruebaDefault(idPrueba, idUser);
  }

  @Override
  @Transactional(readOnly = true)
  public long obtainIdByCode(String code) {
    return pruebaDao.obtainIdByCode(code);
  }

  @Override
  @Transactional(readOnly = true)
  public long validatePruebaDefault(long idPrueba) {
    return pruebaDao.validatePruebaDefault(idPrueba);
  }


}
