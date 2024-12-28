package med.voll.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import med.voll.api.DTO.medico.DadosListagemMedico;
import med.voll.api.model.Medico;
import med.voll.api.repository.MedicoRepository;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Transactional
    public void salvarMedico(Medico medico) {
        medicoRepository.save(medico);
    }

    public List<DadosListagemMedico> listarMedicos(Pageable paginacao) {
        return medicoRepository.findAll(paginacao).stream().map(DadosListagemMedico::new).toList();
    }
}
