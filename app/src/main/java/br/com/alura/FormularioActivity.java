package br.com.alura;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import br.com.alura.dao.PessoaDAO;
import br.com.alura.helper.FormularioActivityHelper;
import br.com.alura.model.Pessoa;
import br.com.alura.validador.PessoaValidador;

public class FormularioActivity extends AppCompatActivity {

    private FormularioActivityHelper formularioActivityHelper;
    private final PessoaDAO pessoaDAO;

    public FormularioActivity (){
    pessoaDAO = new PessoaDAO(FormularioActivity.this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        formularioActivityHelper = new FormularioActivityHelper(this);

        Intent intent = getIntent();
        Pessoa pessoa = (Pessoa) intent.getSerializableExtra("pessoa");
        if(pessoa != null){
            formularioActivityHelper.preencheFormulario(pessoa);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_formulario_xml,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_formulario_ok:
                Pessoa pessoa = formularioActivityHelper.getPessoa();

            if(!PessoaValidador.validador(pessoa).isStatus()){
                Toast.makeText(FormularioActivity.this, "Campo " +PessoaValidador.validador(pessoa).getCampoInvalido()+ " precisa ser preenchido.", Toast.LENGTH_SHORT).show();
                break;
            }

            if(pessoa.getId() != null){
                pessoaDAO.alteraPessoa(pessoa);
                Toast.makeText(FormularioActivity.this, pessoa.getNome() +" foi alterado(a)!", Toast.LENGTH_SHORT).show();
            } else {
                pessoaDAO.insere(pessoa);
                Toast.makeText(FormularioActivity.this, pessoa.getNome() +" foi criado(a)!", Toast.LENGTH_SHORT).show();
            }
            finish();
            break;
        }
        return super.onOptionsItemSelected(item);
    }

}