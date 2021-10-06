package br.com.alura.helper;

import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import br.com.alura.FormularioActivity;
import br.com.alura.R;
import br.com.alura.model.Pessoa;

/**
 * @author devrsvieira
 */
public class FormularioActivityHelper {

    private final EditText campoNome;
    private final EditText campoEndereco;
    private final EditText campoTelefone;
    private final EditText campoSite;
    private final RatingBar campoNota;


    public FormularioActivityHelper(FormularioActivity formularioActivity) {
        campoNome = formularioActivity.findViewById(R.id.formulario_nome);
        campoEndereco = formularioActivity.findViewById(R.id.formulario_endereco);
        campoTelefone = formularioActivity.findViewById(R.id.formulario_telefone);
        campoSite = formularioActivity.findViewById(R.id.formulario_site);
        campoNota = formularioActivity.findViewById(R.id.formulario_nota);
    }

    public Pessoa getPessoa(){
        Pessoa pessoa = new Pessoa();
            pessoa.setNome(campoNome.getText().toString());
            pessoa.setEndereco(campoEndereco.getText().toString());
            pessoa.setTelefone(campoTelefone.getText().toString());
            pessoa.setSite(campoSite.getText().toString());
            pessoa.setNota((double) campoNota.getProgress());
        return pessoa;
    }

}
