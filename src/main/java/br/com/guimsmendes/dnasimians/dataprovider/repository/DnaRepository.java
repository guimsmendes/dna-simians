package br.com.guimsmendes.dnasimians.dataprovider.repository;

import br.com.guimsmendes.dnasimians.dataprovider.exception.DataProviderException;
import org.springframework.stereotype.Repository;

import br.com.guimsmendes.dnasimians.dataprovider.model.DnaEntity;

import javax.persistence.*;
import javax.transaction.Transactional;

@Repository
public class DnaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Object postDnaSequence(DnaEntity dna) {
        try {
            return entityManager.createNativeQuery("SELECT public.postDnaSequence(?,?,?,?)")
                    .setParameter(1, dna.getDnaId())
                    .setParameter(2, dna.getDnaSequence())
                    .setParameter(3, dna.getDnaType())
                    .setParameter(4, dna.getTransactionDate())
                    .getSingleResult();
        } catch (Exception e) {
            throw new DataProviderException.RepositoryException("Unable to connect to Database through DnaRepository. Message: {}", e.getMessage());
        }
    }

    @Transactional
    public Object count(String dnaType) {
        try {
            return entityManager.createNativeQuery("SELECT count(u) FROM dna u WHERE LOWER(dnatype) = LOWER(?)")
                    .setParameter(1, dnaType)
                    .getSingleResult();
        } catch (Exception e) {
            throw new DataProviderException.RepositoryException("Unable to connect to Database through DnaRepository. Message: {}", e.getMessage());
        }
    }

    @Transactional
    public void delete(){
        entityManager.createNativeQuery("DELETE FROM dna u").executeUpdate();
    }

}
