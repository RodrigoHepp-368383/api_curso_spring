package med.voll.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import med.voll.api.DTO.medico.DadosAtualizarMedico;
import med.voll.api.DTO.medico.DadosDetalhamentoMedico;
import med.voll.api.DTO.medico.DadosListagemMedico;
import med.voll.api.model.Medico;
import med.voll.api.repository.MedicoRepository;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Transactional
    public Medico salvarMedico(Medico medico) {
        return medicoRepository.save(medico);
    }

    public Page<DadosListagemMedico> listarMedicos(Pageable paginacao) {
        return medicoRepository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
    }

    @Transactional
    public DadosDetalhamentoMedico atualizarMedico(DadosAtualizarMedico medicoAtualizar) {
        Medico medico = medicoRepository.findById(medicoAtualizar.id())
                .orElseThrow(() -> new IllegalArgumentException("Médico não encontrado"));
        medico.atualizar(medicoAtualizar);
        return new DadosDetalhamentoMedico(medico);
    }

    @Transactional
    public void deletarMedico(Long id) {
        Medico medico = medicoRepository.getReferenceById(id);
        medico.setAtivo(false);
    }

    public DadosDetalhamentoMedico consultarMedico(Long id) {
        return new DadosDetalhamentoMedico(medicoRepository.getReferenceById(id));
    }
}
