package med.voll.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import med.voll.api.DTO.medico.DadosAtualizarMedico;
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
        return medicoRepository.findAllByAtivoTrue(paginacao).stream().map(DadosListagemMedico::new).toList();
    }

    @Transactional
    public void atualizarMedico(DadosAtualizarMedico medicoAtualizar) {
        Medico medico = medicoRepository.findById(medicoAtualizar.id())
                .orElseThrow(() -> new IllegalArgumentException("Médico não encontrado"));
        medico.atualizar(medicoAtualizar);
    }

    @Transactional
    public void deletarMedico(Long id) {
        Medico medico = medicoRepository.getReferenceById(id);
        medico.setAtivo(false);
    }
}
