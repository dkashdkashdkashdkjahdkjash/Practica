package com.example.practica3

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
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

        val textViewTitulo = findViewById<TextView>(R.id.TextViewTitulo)
        val textViewNombre = findViewById<TextView>(R.id.textViewNombre)
        val textViewApellido = findViewById<TextView>(R.id.textViewApellido)
        val textViewCorreo = findViewById<TextView>(R.id.textViewCorreo)
        var editTextNombre = findViewById<TextView>(R.id.textViewNombreEscribir)
        var editTextApellido = findViewById<TextView>(R.id.textViewApellido)
        var editTextCorreo = findViewById<TextView>(R.id.textViewCorreo)
        val textViewGenero = findViewById<TextView>(R.id.textViewGenero)
        var radioGroup = findViewById<RadioGroup>(R.id.radioGroupGenero)

        radioGroup.setOnCheckedChangeListener{_, selectedRadioButtonId ->
            val radioButtonSeleccionado = findViewById<RadioButton>(selectedRadioButtonId)
            radioButtonElegido = radioButtonSeleccionado.text.toString()
        }

        val textViewPais = findViewById<TextView>(R.id.textViewPaisDeOrigen)
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

        val textViewIntereses = findViewById<TextView>(R.id.textViewIntereses)


        val seekBar = findViewById<SeekBar>(R.id.seekBarNivelDeSatisfaccion)
        val textViewSeekBarMinimo = findViewById<TextView>(R.id.textViewSeekBarMinimo)
        val textViewSeekBarMaximo = findViewById<TextView>(R.id.textViewSeekBarMaximo)
        val textViewSeekBarPuntuacion = findViewById<TextView>(R.id.textViewSeekBarPuntuacion)
        seekBar.min = 1
        seekBar.max = 10

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                textViewSeekBarPuntuacion.text = "Valor seleccionado: $p1"
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                TODO("Not yet implemented")
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                TODO("Not yet implemented")
            }
        })

        val textViewSwitchTexto = findViewById<TextView>(R.id.textViewSwitchTexto)
        val switch1 = findViewById<Switch>(R.id.switch1)


        val buttonGuardar = findViewById<Button>(R.id.buttonGuardar)
        var textViewResumen = findViewById<TextView>(R.id.textViewResumen)
        buttonGuardar.setOnClickListener{
            textViewResumen.text = "Nombre: ${editTextNombre.text.toString()} \n" +
                    "Apellido: ${editTextApellido.text.toString()} \n" +
                    "Correo: ${editTextCorreo.text.toString()} \n" +
                    "Género: $radioButtonElegido \n" +
                    "Pais de origen: ${spinnerSeleccionado.toString()} \n" +
                    "Intereses \n" +
                    "Nivel de satisfaccion: ${textViewSeekBarPuntuacion.text.toString()} \n" +
                    "Subscripcion al boletin"
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        spinnerSeleccionado = spinner.selectedItem.toString()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        spinnerSeleccionado = "No has seleccionado ningun pais"
    }
}