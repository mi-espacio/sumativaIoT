package com.example.sum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {

    // Declaración de variables para componentes de interfaz de usuario
    private lateinit var editTextNombre: EditText
    private lateinit var spinnerComFav: Spinner
    private lateinit var radioGroupFrecuencia: RadioGroup
    private lateinit var buttonEnviar: Button
    private lateinit var textViewResumen: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Establecer el diseño de esta actividad
        setContentView(R.layout.activity_main)

        // Inicializar los componentes de la interfaz de usuario buscándolos por su ID
        editTextNombre = findViewById(R.id.editTextNombre)
        spinnerComFav = findViewById(R.id.spinnerComFav)
        radioGroupFrecuencia = findViewById(R.id.radioGroupFrecuencia)
        buttonEnviar = findViewById(R.id.buttonEnviar)
        textViewResumen = findViewById(R.id.textViewResumen)


        // Configuración del Spinner con las opciones de comida
        val comidas = arrayOf("Elija una opción", "Cazuela", "Mote con Huesillo", "Charquicán", "Pastel de Choclo", "Calapurca", "Empanada de Pino")
        val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item, comidas)
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerComFav.adapter = adaptador

        // Implementación del listener para el botón de envío
        buttonEnviar.setOnClickListener {
            enviarEncuesta() // Llama a la función para gestionar el envío
        }
    }

    // Función para gestionar el envío
    private fun enviarEncuesta() {
        // Recuperar entradas del usuario
        val nombre = editTextNombre.text.toString().trim()
        val comidaFavorita = spinnerComFav.selectedItem.toString()
        val idFrecuenciaSeleccionada = radioGroupFrecuencia.checkedRadioButtonId

        // Validar que todos los campos están completos
        if (nombre.isEmpty()) {
            mostrarMensaje("Ingrese su nombre")
            return
        }
        if (comidaFavorita == "Elija una opción") {
            mostrarMensaje("Seleccione su comida favorita")
            return
        }
        if (idFrecuenciaSeleccionada == -1) {
            mostrarMensaje("Seleccione la frecuencia de consumo de comida rápida")
            return
        }

        // Obtener el texto del RadioButton seleccionado
        val radioButtonSeleccionado = findViewById<RadioButton>(idFrecuenciaSeleccionada)
        val frecuenciaConsumo = radioButtonSeleccionado.text.toString()

        // Creación de un resumen de las respuestas
        val resumen = """
            Sus Respuestas:
            
            - Nombre -> $nombre
            - Comida Favorita -> $comidaFavorita
            - Consumo de Comida Rápida -> $frecuenciaConsumo
        """.trimIndent()

        // Mostrar el resumen en el TextView
        textViewResumen.text = resumen
        textViewResumen.visibility = View.VISIBLE
    }

    // Función para mostrar mensajes utilizando un Toast
    private fun mostrarMensaje(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }
}