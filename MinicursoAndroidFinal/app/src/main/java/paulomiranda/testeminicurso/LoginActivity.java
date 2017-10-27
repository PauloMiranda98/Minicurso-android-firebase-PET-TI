package paulomiranda.testeminicurso;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.security.Principal;

public class LoginActivity extends AppCompatActivity {

    //Criando variaveis para serem referenciadas aos editores de texto no layout
    private EditText txt_email;
    private EditText txt_senha;

    //Criando variavel para ser referenciada no botao login do layout
    private Button btn_login;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //informando a classe qual é o layout que estamos trabalhando
        setContentView(R.layout.activity_login);

        //associando cada variavel ao seu respectivo ID
        //(EditText) <- isso serve para converter um tipo View(Generico) para um EditText
        txt_email = (EditText) findViewById(R.id.edt_email);
        txt_senha = (EditText) findViewById(R.id.edt_senha);

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            Intent mIntent = new Intent(LoginActivity.this, PrincipalActivity.class);
            startActivity(mIntent);

            //fechar a tela atual
            finish();
        }

        btn_login = (Button) findViewById(R.id.btn_login);

        //Primeira forma de capturar o clique do usuario no botao, nesse caso, o botao de login
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //forma de chamar uma nova tela no android
                //new Intent(origem, destino)
                if(txt_email.getText().length()>0 && txt_senha.getText().length() > 5){
                    mAuth.signInWithEmailAndPassword(txt_email.getText().toString(), txt_senha.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent mIntent = new Intent(LoginActivity.this, PrincipalActivity.class);
                                startActivity(mIntent);

                                //fechar a tela atual
                                finish();
                            } else {
                                Toast.makeText(LoginActivity.this, "Email ou senha incorreto!", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }else{
                    Toast.makeText(LoginActivity.this, "Email ou senha inválido!",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    //segunda forma que capturar o clique do botao, nesse caso, o botao de cadastrar
    public void clique_do_botao_cadastrar(View v){

        //forma de chamar uma nova tela no android
        //new Intent(origem, destino)
        Intent mIntent = new Intent(this, CadastroActivity.class);
        startActivity(mIntent);

        //fechar a tela atual
        finish();
    }

}
