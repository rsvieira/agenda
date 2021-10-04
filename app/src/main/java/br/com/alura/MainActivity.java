package br.com.alura;

import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<String> pessoas = new ArrayList<>();
            pessoas.add("Ramon");
            pessoas.add("Luana");
            pessoas.add("Barney");
            pessoas.add("Agnes");

        ListView listaPessoas = findViewById(R.id.pessoas);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pessoas);
        listaPessoas.setAdapter(adapter);

        Button addPessoa = findViewById(R.id.formulario_main_add);
        addPessoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentVaiProFormulario = new Intent(MainActivity.this, FormularioActivity.class);
                startActivity(intentVaiProFormulario);
            }
        });

    }
}