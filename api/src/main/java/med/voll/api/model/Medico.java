package med.voll.api.model;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.DTO.medico.DadosAtualizarMedico;
import med.voll.api.DTO.medico.DadosCadastroMedico;
import med.voll.api.enums.Especialidade;

@Table(name = "medicos")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    private boolean ativo = true;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    public Medico(DadosCadastroMedico dadosCadastroMedico) {
        this.nome = dadosCadastroMedico.nome();
        this.email = dadosCadastroMedico.email();
        this.telefone = dadosCadastroMedico.telefone();
        this.crm = dadosCadastroMedico.crm();
        this.especialidade = dadosCadastroMedico.especialidade();
        this.endereco = new Endereco(dadosCadastroMedico.endereco());

    }

    public void atualizar(DadosAtualizarMedico medicoAtualizar) {
        this.nome = StringUtils.isNotEmpty(medicoAtualizar.nome()) ? medicoAtualizar.nome() : this.nome;
        this.telefone = StringUtils.isEmpty(medicoAtualizar.telefone()) ? medicoAtualizar.telefone() : this.telefone;
        this.endereco = medicoAtualizar.endereco() != null ? medicoAtualizar.endereco() : this.endereco;
    }
}
