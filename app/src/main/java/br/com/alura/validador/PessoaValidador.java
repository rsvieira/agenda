package br.com.alura.validador;

import br.com.alura.model.Pessoa;

/**
 * @author devrsvieira
 */
public class PessoaValidador {

    private String campoInvalido;
    private boolean status;

    public static PessoaValidador validador(Pessoa pessoa){
        PessoaValidador pessoaValidador = new PessoaValidador();
        if(pessoa.getNome().isEmpty() || pessoa.getNome() == null){
            pessoaValidador.setCampoInvalido("Nome");
        } else if(pessoa.getEndereco().isEmpty() || pessoa.getEndereco() == null){
            pessoaValidador.setCampoInvalido("Endereço");
        } else if(pessoa.getTelefone().isEmpty() || pessoa.getTelefone() == null){
            pessoaValidador.setCampoInvalido("Telefone");
        } else if(pessoa.getSite().isEmpty() || pessoa.getSite() == null){
            pessoaValidador.setCampoInvalido("Site");
        } else {
            pessoaValidador.setStatus(true);
        }
        return pessoaValidador;
    }

    public String getCampoInvalido() {
        return campoInvalido;
    }

    public void setCampoInvalido(String campoInvalido) {
        this.campoInvalido = campoInvalido;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
