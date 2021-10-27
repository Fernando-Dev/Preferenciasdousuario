package br.fernando.preferenciasdousuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private Button buttonSalvar;
    private TextInputEditText editNome;
    private TextView textViewResultado;
    private static final String ARQUIVO_PREFERENCIA = "ArquivoPreferencia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSalvar = findViewById(R.id.buttonSalvar);
        editNome = findViewById(R.id.edtNome);
        textViewResultado = findViewById(R.id.textViewResultado);

        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                cria um xml onde consegue salvar dados internos no aparelho
                SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA,0);
//                com este objeto consigo fazer alteracao no arquivo
                SharedPreferences.Editor editor = preferences.edit();

                if (editNome.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Preencha o nome", Toast.LENGTH_LONG).show();
                }else{
                    String nome = editNome.getText().toString();
//                    salvando o nome
                    editor.putString("nome",nome);
                    editor.commit();
                    textViewResultado.setText("Olá, " + nome);
                }

            }
        });

        //recuperar dados do sharedpreferences
        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA,0);
        //valida se temos o nome em preferences
        if (preferences.contains("nome")){
            String nome = preferences.getString("nome","usuário não definido");
            textViewResultado.setText("Olá, "+nome);
        }else{
            textViewResultado.setText("Olá, usuário não definido");
        }

    }
}