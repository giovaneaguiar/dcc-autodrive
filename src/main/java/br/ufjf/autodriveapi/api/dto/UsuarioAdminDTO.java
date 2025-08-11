package br.ufjf.autodriveapi.api.dto;

import br.ufjf.autodriveapi.model.entity.Usuario;
import br.ufjf.autodriveapi.model.entity.UsuarioAdmin;
import org.modelmapper.ModelMapper;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class UsuarioAdminDTO {

    private Long id;
    private String login;
    private String senha;
    private String senhaRepeticao;
    private String cpf;
    private boolean admin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenhaRepeticao() {
        return senhaRepeticao;
    }

    public void setSenhaRepeticao(String senhaRepeticao) {
        this.senhaRepeticao = senhaRepeticao;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public boolean getAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public static UsuarioAdminDTO create(UsuarioAdmin usuarioAdmin) {
        ModelMapper modelMapper = new ModelMapper();
        UsuarioAdminDTO dto = modelMapper.map(usuarioAdmin, UsuarioAdminDTO.class);
        return dto;
    }
}