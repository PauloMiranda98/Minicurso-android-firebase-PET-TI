package paulomiranda.testeminicurso;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.security.Principal;

import static android.R.attr.password;

public class CadastroActivity extends AppCompatActivity {

    //Criando variaveis para serem referenciadas aos editores de texto no layout
    private EditText txt_nome;
    private EditText txt_email;
    private EditText txt_senha;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //informando a classe qual é o layout que estamos trabalhando
        setContentView(R.layout.activity_cadastro);

        //associando cada variavel ao seu respectivo ID
        //(EditText) <- isso serve para converter um tipo View(Generico) para um EditText
        txt_nome = (EditText) findViewById(R.id.edt_nome);
        txt_email = (EditText) findViewById(R.id.edt_email);
        txt_senha = (EditText) findViewById(R.id.edt_senha);

        mAuth = FirebaseAuth.getInstance();
    }

    //uma forma que capturar o clique do botao, nesse caso, o botao de cadastrar
    public void clique_do_botao_cadastrar(View v){
        if(txt_email.getText().length()>0 && txt_senha.getText().length() > 5){
        mAuth.createUserWithEmailAndPassword(txt_email.getText().toString(), txt_senha.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Intent mIntent = new Intent(CadastroActivity.this, PrincipalActivity.class);
                            startActivity(mIntent);

                            finish();
                        }

                    }
                });
        }else{
            Toast.makeText(CadastroActivity.this, "Email ou senha inválido!",Toast.LENGTH_SHORT).show();
        }
    }

}
