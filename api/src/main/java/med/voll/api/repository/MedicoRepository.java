package med.voll.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import med.voll.api.model.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {

}
