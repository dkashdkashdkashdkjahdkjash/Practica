package com.example.practica3

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.Switch
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    lateinit var spinner : Spinner
    lateinit var spinnerSeleccionado : String
    lateinit var radioButtonElegido : String
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imagenFotoPerfilVacia = findViewById<ImageView>(R.id.imageViewFotoPerfil)

        imagenFotoPerfilVacia.setImageResource(R.mipmap.foto_de_perfil_vacia)

        val editTextNombre = findViewById<TextView>(R.id.textViewNombreEscribir)
        val editTextApellido = findViewById<TextView>(R.id.textViewApellido)
        val editTextCorreo = findViewById<TextView>(R.id.textViewCorreo)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroupGenero)

        radioGroup.setOnCheckedChangeListener{_, selectedRadioButtonId ->
            val radioButtonSeleccionado = findViewById<RadioButton>(selectedRadioButtonId)
            radioButtonElegido = radioButtonSeleccionado.text.toString()
        }

        spinner = findViewById<Spinner>(R.id.spinner)

        ArrayAdapter.createFromResource(
            this,
            R.array.paises_array,
            android.R.layout.simple_spinner_item
        ).also{ adaptador ->
            adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adaptador
        }
        spinner.onItemSelectedListener = this

        val interesLectura = findViewById<CheckBox>(R.id.checkBoxLectura)
        val interesDeporte = findViewById<CheckBox>(R.id.checkBoxDeporte)
        val interesMusica = findViewById<CheckBox>(R.id.checkBoxMusica)
        val interesArte = findViewById<CheckBox>(R.id.checkBoxArte)

        var lectura = ""
        interesLectura.setOnClickListener(){
            if(lectura == ""){
                lectura = getString(R.string.lectura)
            }else if (lectura != ""){
                lectura = ""
            }
        }
        var deporte = ""
        interesDeporte.setOnClickListener(){
            if(deporte == ""){
                deporte = getString(R.string.deporte)
            }else if (deporte != ""){
                deporte = ""
            }
        }
        var musica = ""
        interesMusica.setOnClickListener(){
            if(musica == ""){
                musica = getString(R.string.musica)
            }else if (musica != ""){
                musica = ""
            }
        }
        var arte = ""
        interesArte.setOnClickListener(){
            if(arte == ""){
                arte = getString(R.string.arte)
            }else if (arte != ""){
                arte = ""
            }
        }

        val seekBar = findViewById<SeekBar>(R.id.seekBarNivelDeSatisfaccion)
        val textViewSeekBarPuntuacion = findViewById<TextView>(R.id.textViewSeekBarPuntuacion)
        seekBar.min = 1
        seekBar.max = 10

        var seekBarPuntuacion = 0
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                seekBarPuntuacion = p1
                textViewSeekBarPuntuacion.text = "Valor seleccionado: $p1"
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })

        val switch1 = findViewById<Switch>(R.id.switch1)

        var subscipcion = "No"
        switch1.setOnClickListener(){
            if (subscipcion == "No"){
                subscipcion = "Si"
            }else if(subscipcion == "Si"){
                subscipcion = "No"
            }

        }

        val buttonGuardar = findViewById<Button>(R.id.buttonGuardar)
        val textViewResumen = findViewById<TextView>(R.id.textViewResumen)
        buttonGuardar.setOnClickListener{
            textViewResumen.text = "Nombre: ${editTextNombre.text.toString()} \n" +
                    "Apellido: ${editTextApellido.text.toString()} \n" +
                    "Correo: ${editTextCorreo.text.toString()} \n" +
                    "Género: $radioButtonElegido \n" +
                    "Pais de origen: ${spinnerSeleccionado.toString()} \n" +
                    "Intereses $lectura $deporte $musica $arte\n" +
                    "Nivel de satisfaccion: $seekBarPuntuacion \n" +
                    "Subscripcion al boletin: $subscipcion"
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        spinnerSeleccionado = spinner.selectedItem.toString()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        spinnerSeleccionado = "No has seleccionado ningun pais"
    }
}