package med.voll.api.DTO.medico;

import med.voll.api.enums.Especialidade;
import med.voll.api.model.Medico;

public record DadosDetalhamentoMedico(Long id, String nome, String crm, Especialidade especialidade, String telefone,
        String email) {

    public DadosDetalhamentoMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getCrm(), medico.getEspecialidade(), medico.getTelefone(),
                medico.getEmail());
    }

}
