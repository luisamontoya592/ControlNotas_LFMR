package com.example.controlnotas_lfmr;

import androidx.appcompat.app.AppCompatActivity;

//Librerías que voy a usar
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

/*REALIZADO POR:
Luisa Fernanda Montoya Restrepo*/

public class MainActivity extends AppCompatActivity {

    //Declarar las variables que usaré
    private EditText TxtNota1, TxtNota2, TxtNota3, TxtNota4, TxtNombreEstudiante;
    private RadioButton RbNota2, RbNota4;
    private CheckBox CbNota1, CbNota3;
    private TextView TvNotaDefinitiva, TvPerdieron;
    private Button BtnIngresar, BtnPerdieron;
    //Inicializar las variables que me permitirán hacer cálculos mateméticos
    private double Nota1=0, Nota2=0, Nota3=0, Nota4=0, NotaDefinitiva=0;
    private int CantPerdieron=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Asigno el objeto a cada variable
        //Edit text
        TxtNota1 = findViewById(R.id.TxtNota1);
        TxtNota2 = findViewById(R.id.TxtNota2);
        TxtNota3 = findViewById(R.id.TxtNota3);
        TxtNota4 = findViewById(R.id.TxtNota4);
        TxtNombreEstudiante = findViewById(R.id.TxtNombreEstudiante);
        //Radiobutton
        RbNota2 = findViewById(R.id.RbNota2);
        RbNota4 = findViewById(R.id.RBNota4);
        //Checkbox
        CbNota1 = findViewById(R.id.CbNota1);
        CbNota3 = findViewById(R.id.CbNota3);
        //Textview
        TvNotaDefinitiva = findViewById(R.id.TvNotaDefinitiva);
        TvPerdieron = findViewById(R.id.TvPerdieron);
        //Button
        BtnIngresar = findViewById(R.id.BtnIngresar);
        BtnPerdieron = findViewById(R.id.BtnPerdieron);

        //Agrego funcionalidad al botón Ingresar
        BtnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Convierto lo que se recopila en las 4 notas a string
                String TxtNotas1 = String.valueOf(TxtNota1);
                String TxtNotas2 = String.valueOf(TxtNota2);
                String TxtNotas3 = String.valueOf(TxtNota3);
                String TxtNotas4 = String.valueOf(TxtNota4);
                String TxtNombreEstudiant = String.valueOf(TxtNombreEstudiante);

                //Teniendo las notas en string valido que no queden campos vacíos y que se seleccionen los porcentajes
                if (CbNota1.isChecked() && TxtNotas1!= "" && RbNota2.isChecked() && TxtNotas2!= "" && CbNota3.isChecked() && TxtNotas3!="" && RbNota4.isChecked() && TxtNotas4!="" && TxtNombreEstudiant!="") {
                    //Convierto de string a decimal las notas para poder hacer cálculos matemáticos
                    double TxtNotass1 = Double.parseDouble(TxtNota1.getText().toString());
                    double TxtNotass2 = Double.parseDouble(TxtNota2.getText().toString());
                    double TxtNotass3 = Double.parseDouble(TxtNota3.getText().toString());
                    double TxtNotass4 = Double.parseDouble(TxtNota4.getText().toString());

                    //Valido cada nota con un condicional, si se sale del rango 0 a 5 que muestre un mensaje
                    // Sino que haga el cálculo de la nota, multiplicando el valor de la nota por el porcentaje
                    if (TxtNotass1<0 || TxtNotass1>5 || TxtNotass2<0 || TxtNotass2>5 || TxtNotass3<0 || TxtNotass3>5 || TxtNotass4<0 || TxtNotass4>5){
                        Toast.makeText(MainActivity.this, "Las notas deben estar entre 0 y 5", Toast.LENGTH_LONG).show();
                    }else{
                        Nota1 = TxtNotass1 * 0.15;
                        Nota2 = TxtNotass2 * 0.2;
                        Nota3 = TxtNotass3 * 0.3;
                        Nota4 = TxtNotass4 * 0.35;
                        //Calculo la nota definitiva sumando los resultados de las notas anteriores
                        //Puse una función para que el valor de la nota salga solo con dos décimas después de la coma
                        NotaDefinitiva = Math.round((Nota1 + Nota2 + Nota3 + Nota4) * 100.0) / 100.0;
                        //Muestro la nota definitiva en pantalla
                        TvNotaDefinitiva.setText("La nota definitiva de " + TxtNombreEstudiante.getText() + " es " + String.valueOf(NotaDefinitiva));

                        //Sumo en un contador los estudiantes que sacan menos de 3 de definitiva
                        if (NotaDefinitiva < 3) {
                            CantPerdieron++;
                        }
                        TvPerdieron.setText("");
                    }



                } else {
                    //En caso de que no se ingrese completo notas y seleccionen porcentajes, se muestra mensaje
                    Toast.makeText(MainActivity.this, "Nombre, Notas o porcentajes incompletas, por favor revisar", Toast.LENGTH_LONG).show();
                }
            }


        });

        //Se agrega funcionalidad a botón calcular
        BtnPerdieron.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Se mostrará cantidad de estudiantes que perdieron la materia
                TvPerdieron.setText("Perdieron la materia "+String.valueOf(CantPerdieron)+" estudiantes");
                //Se deja de nuevo en cero el contador, para ingresar un nuevo grupo de estudiantes
                CantPerdieron=0;
                TxtNombreEstudiante.setText("");
                TxtNota1.setText("");
                TxtNota2.setText("");
                TxtNota3.setText("");
                TxtNota4.setText("");
                TvNotaDefinitiva.setText("");
            }
        });

        /*REALIZADO POR:
        Luisa Fernanda Montoya Restrepo*/

    }
}