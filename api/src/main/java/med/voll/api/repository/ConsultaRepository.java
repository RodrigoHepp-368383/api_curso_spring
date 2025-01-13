package med.voll.api.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.model.Consulta;
import med.voll.api.model.Medico;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    boolean existsByMedicoIdAndData(Medico referenceById, LocalDateTime data);

}
