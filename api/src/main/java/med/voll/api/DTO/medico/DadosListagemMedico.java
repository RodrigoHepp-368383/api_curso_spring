package med.voll.api.DTO.medico;

import med.voll.api.enums.Especialidade;
import med.voll.api.model.Medico;

public record DadosListagemMedico(String nome, String email, String crm, Especialidade especialidade) {
    public DadosListagemMedico(Medico medico) {
        this(medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
