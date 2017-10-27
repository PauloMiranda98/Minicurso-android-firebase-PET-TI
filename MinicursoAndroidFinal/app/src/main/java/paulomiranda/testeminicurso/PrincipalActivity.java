package paulomiranda.testeminicurso;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PrincipalActivity extends AppCompatActivity {

    List<Tarefa> lista;
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent mIntent = new Intent(PrincipalActivity.this, CadastroTarefaActivity.class);
                startActivity(mIntent);

            }
        });

        mListView = (ListView) findViewById(R.id.lv_tarefa);
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference(user.getUid()+"/tarefa");

                myRef.child(lista.get(i).getId()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            atualizarLista();
                            Toast.makeText(PrincipalActivity.this, "Tarefa deletada com sucesso!", Toast.LENGTH_LONG).show();
                        } else{
                            Toast.makeText(PrincipalActivity.this, "Erro ao deletar!", Toast.LENGTH_LONG).show();
                        }
                    }
                });


                return false;
            }
        });

        lista = new ArrayList<>();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.sair){
            FirebaseAuth.getInstance().signOut();

            Intent mIntent = new Intent(this, LoginActivity.class);
            startActivity(mIntent);
            finish();
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();

        atualizarLista();
    }

    private void atualizarLista(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(user.getUid()+"/tarefa");

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                lista.clear();

                for (DataSnapshot data :dataSnapshot.getChildren()) {
                    Tarefa t = data.getValue(Tarefa.class);

                    if(t != null){
                        t.setId(data.getKey());
                        lista.add(t);
                    }
                }

                MyAdapterItem mAdapter = new MyAdapterItem(PrincipalActivity.this,lista);

                mListView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

}
