package br.com.alura.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import br.com.alura.model.Pessoa;

import java.util.ArrayList;
import java.util.List;

/**
 * @author devrsvieira
 */
public class PessoaDAO extends SQLiteOpenHelper {

    public PessoaDAO(@Nullable Context context) {
        super(context, "Agenda", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder sql = new StringBuilder();
            sql.append("CREATE TABLE pessoa (id INTEGER PRIMARY KEY, nome TEXT NOT NULL, endereco TEXT, telefone TEXT, site TEXT, nota REAL);");
        db.execSQL(sql.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        StringBuilder sql = new StringBuilder();
            sql.append("DROP TABLE IF EXISTS Alunos;");
        db.execSQL(sql.toString());
        onCreate(db);
    }

    public void insere(Pessoa pessoa) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();
            dados.put("nome", pessoa.getNome());
            dados.put("endereco", pessoa.getEndereco());
            dados.put("telefone", pessoa.getTelefone());
            dados.put("site", pessoa.getSite());
            dados.put("nota", pessoa.getNota());

        db.insert("pessoa", null, dados);
        close();
    }

    @SuppressLint("Range")
    public List<Pessoa> buscarTodos() {
        List<Pessoa> listaPessoas = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        SQLiteDatabase db = getWritableDatabase();

        sql.append("SELECT * FROM pessoa ORDER BY nome asc");
        Cursor c = db.rawQuery(sql.toString(), null);

        while(c.moveToNext()){
            listaPessoas.add(populaPessoa(c));
        }
        c.close();
        close();
        return listaPessoas;
    }

    public void deleta(Pessoa pessoa){
        SQLiteDatabase db = getWritableDatabase();
        String[] parametros = {pessoa.getId().toString()};
        db.delete("pessoa", "id = ?", parametros);
        close();
    }

    public Pessoa buscaPorId(Long id){
        Pessoa pessoa = new Pessoa();
        SQLiteDatabase db = getWritableDatabase();
        String[] parametros = {id.toString()};
        StringBuilder sql = new StringBuilder("SELECT * FROM pessoa WHERE id = ?");
        Cursor c = db.rawQuery(sql.toString(), parametros);

        while(c.moveToNext()){
            pessoa = populaPessoa(c);
        }
        c.close();
        close();
        return pessoa;
    }

    /*
    *  metodo de popular pessoa como um rowmapper
    * */

    @SuppressLint("Range")
    private Pessoa populaPessoa(Cursor c) {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(c.getLong(c.getColumnIndex("id")));
        pessoa.setNome(c.getString(c.getColumnIndex("nome")));
        pessoa.setEndereco(c.getString(c.getColumnIndex("endereco")));
        pessoa.setTelefone(c.getString(c.getColumnIndex("telefone")));
        pessoa.setSite(c.getString(c.getColumnIndex("site")));
        pessoa.setNota(c.getDouble(c.getColumnIndex("nota")));
        return pessoa;
    }

    public void alteraPessoa(Pessoa pessoa) {
        SQLiteDatabase db = getWritableDatabase();
        String[] parametros = {pessoa.getId().toString()};
        db.update("pessoa", preencheContentValues(pessoa),"id = ?", parametros);
        db.close();
        close();
    }

    private ContentValues preencheContentValues(Pessoa pessoa) {
        ContentValues dados = new ContentValues();
        dados.put("nome", pessoa.getNome());
        dados.put("endereco", pessoa.getEndereco());
        dados.put("telefone", pessoa.getTelefone());
        dados.put("site", pessoa.getSite());
        dados.put("nota", pessoa.getNota());
        return dados;
    }
}
