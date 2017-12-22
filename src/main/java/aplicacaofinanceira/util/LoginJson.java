package aplicacaofinanceira.util;

public class LoginJson {
    
    private String nomeDeUsuario;
    private String senha;

    public LoginJson() {}

    public String getNomeDeUsuario() {
        return nomeDeUsuario;
    }

    public void setNomeDeUsuario(String nomeDeUsuario) {
        this.nomeDeUsuario = nomeDeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }    
}
