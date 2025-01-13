package med.voll.api.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import med.voll.api.enums.Especialidade;
import med.voll.api.model.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Page<Medico> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
            SELECT m FROM Medico m
            WHERE 
                m.ativo = true
                and m.especialidade = :especialidade
                and NOT EXISTS (
                    select c from Consulta c
                    where c.medico = m
                    and c.data = :data
                )
            order by function('RAND')
            """)
    Medico escolherMedicoAleatorioLivreNaData(Especialidade especialidade, LocalDateTime data);

    boolean existsByIdAndAtivoTrue(Long id);

}
