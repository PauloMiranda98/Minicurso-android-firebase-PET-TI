package paulomiranda.minicursoandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CadastrarItemActivity extends AppCompatActivity {

    private EditText input_titulo;
    private EditText input_descricao;
    private EditText input_disciplica;
    private EditText input_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_item);

        input_titulo = (EditText) findViewById(R.id.input_titulo);
        input_descricao = (EditText) findViewById(R.id.input_descricao);
        input_disciplica = (EditText) findViewById(R.id.input_disciplina);
        input_data = (EditText) findViewById(R.id.input_data);

    }

    public void cadastrarNovoItem(View v){

        String disciplina = input_disciplica.getText().toString();
        String descricao = input_descricao.getText().toString();
        String titulo = input_titulo.getText().toString();
        String data = input_data.getText().toString();

        Tarefa minhaTarefa = new Tarefa(titulo, descricao, disciplina, data);

        PrincipalActivity.lista.add(minhaTarefa);

        finish();
    }
}