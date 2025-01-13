package med.voll.api.DTO.paciente;

import jakarta.validation.constraints.NotNull;
import med.voll.api.DTO.endereco.DadosEndereco;

public record DadosAtualizacaoPaciente(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}
