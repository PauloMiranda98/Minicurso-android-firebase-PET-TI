package paulomiranda.testeminicurso;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CadastroTarefaActivity extends AppCompatActivity {
    private EditText txt_titulo;
    private EditText txt_descricao;
    private EditText txt_materia;
    private EditText txt_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_tarefa);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txt_titulo = (EditText) findViewById(R.id.edt_titulo);
        txt_descricao = (EditText) findViewById(R.id.edt_descricao);
        txt_materia = (EditText) findViewById(R.id.edt_materia);
        txt_data = (EditText) findViewById(R.id.edt_data);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }

        return true;
    }

    public void clique_do_botao_cadastrar(View v){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(user.getUid()+"/tarefa");

        Tarefa mTarefa = new Tarefa();
        mTarefa.setTitulo(txt_titulo.getText().toString());
        mTarefa.setDescricao(txt_descricao.getText().toString());
        mTarefa.setMateria(txt_materia.getText().toString());
        mTarefa.setData(txt_data.getText().toString());

        myRef.push().setValue(mTarefa).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    finish();
                }else{
                    Toast.makeText(CadastroTarefaActivity.this, "Erro ao inserir!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}
