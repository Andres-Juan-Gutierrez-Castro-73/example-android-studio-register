package com.example.trabajo_clase_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //CREAMOS LAS PROPIEDADES QUE SE VAN A CONECTAR CON LOS ELEMENTOS DE LA INTERFAZ GRAFICA
    EditText txtNombre, txtDni;
    TextView txtMensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //CREAMOS EL METODO PARA INCIAR AL UI
        inciarInterfaz();
    }

    private void inciarInterfaz() {
        //VAMOS A ASIGANAR EL ID DE LOS ELEMNTOS DE LA INTERFAZ CON LAS PROPIEDADES DEFINIDAS
        txtNombre =findViewById(R.id.txtNombre);
        txtDni =findViewById(R.id.txtDni);
        txtMensaje = findViewById(R.id.txtMensaje);
    }

    //CREAMOS EL METODO PARA VALIDAR LA INFORMACION
    private boolean validarCampos() {
        if (txtNombre.getText().toString().equals("")){
            txtNombre.setError("Ingrese un nombre");
            txtNombre.requestFocus();
            return false;
        } else if (txtDni.getText().toString().equals("")) {
            txtDni.setError("Ingrese un DNI");
            txtDni.requestFocus();
            return false;
        }
        return true;
    }

    //METODO PARA GUARDAR DATOS
    public void guardar(View v){
        ConexionDb conexion = new ConexionDb(this);
        SQLiteDatabase db = conexion.getWritableDatabase();
        ContentValues valores = new ContentValues();

        //VALIDAMOS LOS DATOS
        if (validarCampos()){
            valores.put(ConexionDb.COLUMNA_NOMBRE, txtNombre.getText().toString());
            valores.put(ConexionDb.COLUMNA_DNI, Integer.parseInt(txtDni.getText().toString()));
            long resultado = db.insert(ConexionDb.NOMBRE_TABLA, null, valores);
            if (resultado > 0){
                txtNombre.setText("");
                txtDni.setText("");
                txtMensaje.setText("Guardado con exito");
                return;
            } else {
                txtMensaje.setText("Hubo un error");
                return;
            }
        }
    }


}