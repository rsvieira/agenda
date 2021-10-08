package br.com.alura;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import br.com.alura.dao.PessoaDAO;
import br.com.alura.model.Pessoa;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listaPessoas;
    private final PessoaDAO pessoaDAO;

    public MainActivity(){
        pessoaDAO = new PessoaDAO(MainActivity.this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaPessoas = findViewById(R.id.lista_pessoas);

        /*
        * metodo que captura a acao de um click em uma lista
        * */
        clickItemLista();

        /*
        *  Botão de add uma pessoa na agenda
        * */
        botaoAdd();

        /*
        *  cria um menu de contexto
        * */
        registerForContextMenu(listaPessoas);
    }

    private void clickItemLista() {
        listaPessoas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Pessoa pessoa = pessoaDAO.buscaPorId(((Pessoa) listaPessoas.getItemAtPosition(i)).getId());
                vaiParaOformulario(pessoa);

            }
        });
    }

    private void botaoAdd() {
        Button addPessoa = findViewById(R.id.formulario_main_add);
        addPessoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vaiParaOformulario(null);
            }
        });
    }

    /**
     *  metodo que chama uma activity a ser exibida
     */
    private void vaiParaOformulario(Pessoa pessoa) {
        Intent intentVaiProFormulario = new Intent(MainActivity.this, FormularioActivity.class);
        intentVaiProFormulario.putExtra("pessoa", pessoa);
        startActivity(intentVaiProFormulario);
    }

    private void carregaLista(){
    List<Pessoa> pessoas = pessoaDAO.buscarTodos();
    listaPessoas = findViewById(R.id.lista_pessoas);
    ArrayAdapter<Pessoa> adapter = new ArrayAdapter<Pessoa>(this, android.R.layout.simple_list_item_1, pessoas);
        listaPessoas.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
       MenuItem deletar = menu.add("Deletar");
       deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
           @Override
           public boolean onMenuItemClick(MenuItem menuItem) {
               AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuItem.getMenuInfo();
                Pessoa pessoa = (Pessoa) listaPessoas.getItemAtPosition(info.position);

                pessoaDAO.deleta(pessoa);
                Toast.makeText(MainActivity.this, "Deletando " + pessoa.getNome(), Toast.LENGTH_SHORT).show();

               carregaLista();
               return false;
           }
       });
    }
}