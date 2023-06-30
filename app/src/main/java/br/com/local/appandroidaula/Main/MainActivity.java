package br.com.local.appandroidaula.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.internal.TextWatcherAdapter;

import java.util.concurrent.ExecutionException;

import br.com.local.appandroidaula.Model.CEP;
import br.com.local.appandroidaula.R;
import br.com.local.appandroidaula.Service.HttpService;

public class MainActivity extends AppCompatActivity {
    Button btnBuscarCEP;
    EditText txtCEP;
    TextView tvwDadosCEP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnBuscarCEP = findViewById(R.id.btnBuscar);
        txtCEP = findViewById(R.id.txtCEP);
        tvwDadosCEP = findViewById(R.id.tvwDadosCEP);



        TextWatcher textWatcher = new TextWatcher(){

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                btnBuscarCEP.setEnabled(txtCEP.length() == 8);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };

        txtCEP.addTextChangedListener(textWatcher);

        btnBuscarCEP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    CEP retorno = new HttpService(txtCEP.getText().toString().trim()).execute().get();
                    tvwDadosCEP.setText(retorno.toString());
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}