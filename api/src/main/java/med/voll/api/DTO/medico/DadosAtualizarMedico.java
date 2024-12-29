package med.voll.api.DTO.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.model.Endereco;

public record DadosAtualizarMedico(@NotNull Long id, String nome, String telefone, Endereco endereco) {
    
}
