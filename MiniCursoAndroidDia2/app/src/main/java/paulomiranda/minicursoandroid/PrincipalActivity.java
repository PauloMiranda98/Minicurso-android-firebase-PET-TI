package paulomiranda.minicursoandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class PrincipalActivity extends AppCompatActivity {

    public static List<Tarefa> lista;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        lista = new ArrayList<Tarefa>();
        listView = (ListView) findViewById(R.id.lista);

    }

    @Override
    protected void onResume() {
        super.onResume();

        TarefaAdapter adapter = new TarefaAdapter(this, lista);
        listView.setAdapter(adapter);
    }

    public void cadastrarItem(View v){
        Intent i = new Intent(this, CadastrarItemActivity.class);
        startActivity(i);
    }
}



