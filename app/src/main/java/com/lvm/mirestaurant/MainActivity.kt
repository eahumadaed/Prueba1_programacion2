package com.lvm.mirestaurant

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.NumberFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private val precioPastel = 36000
    private val precioCazuela = 10000
    private var totalComida = 0
    private var propina = 0

    @SuppressLint("SetTextI18n", "UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputCantidadPastelChoclo = findViewById<EditText>(R.id.inputCantidadPastelChoclo)
        val inputCantidadCazuela = findViewById<EditText>(R.id.inputCantidadCazuela)
        val subtotalPastelChoclo = findViewById<TextView>(R.id.subtotalPastelChoclo)
        val subtotalCazuela = findViewById<TextView>(R.id.subtotalCazuela)
        val totalComidaView = findViewById<TextView>(R.id.totalComida)
        val switchPropina = findViewById<Switch>(R.id.switchPropina)
        val textViewPropina = findViewById<TextView>(R.id.textViewPropina)
        val totalConPropinaView = findViewById<TextView>(R.id.total)

        subtotalPastelChoclo.text = formatCurrency(0)
        subtotalCazuela.text = formatCurrency(0)
        totalComidaView.text = formatCurrency(0)
        textViewPropina.text = formatCurrency(0)
        totalConPropinaView.text = formatCurrency(0)

        inputCantidadPastelChoclo.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val cantidad = s.toString().toIntOrNull() ?: 0
                val subtotal = if (cantidad > 0) cantidad * precioPastel else 0
                subtotalPastelChoclo.text = formatCurrency(subtotal)
                actualizarTotalComida()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        inputCantidadCazuela.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val cantidad = s.toString().toIntOrNull() ?: 0
                val subtotal = if (cantidad > 0) cantidad * precioCazuela else 0
                subtotalCazuela.text = formatCurrency(subtotal)
                actualizarTotalComida()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        switchPropina.setOnCheckedChangeListener { _, _ ->
            actualizarTotalComida()
        }
    }

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private fun actualizarTotalComida() {
        val subtotalPastel = findViewById<TextView>(R.id.subtotalPastelChoclo).text.toString().replace("[^\\d]".toRegex(), "").toIntOrNull() ?: 0
        val subtotalCazuela = findViewById<TextView>(R.id.subtotalCazuela).text.toString().replace("[^\\d]".toRegex(), "").toIntOrNull() ?: 0
        totalComida = subtotalPastel + subtotalCazuela

        val totalComidaView = findViewById<TextView>(R.id.totalComida)
        totalComidaView.text = formatCurrency(totalComida)

        val switchPropina = findViewById<Switch>(R.id.switchPropina)
        propina = if (switchPropina.isChecked && totalComida > 0) {
            (totalComida * 0.1).toInt()
        } else {
            0
        }
        findViewById<TextView>(R.id.textViewPropina).text = formatCurrency(propina)

        actualizarTotalConPropina()
    }

    private fun actualizarTotalConPropina() {
        val totalConPropina = totalComida + propina
        findViewById<TextView>(R.id.total).text = formatCurrency(totalConPropina)
    }

    private fun formatCurrency(amount: Int): String {
        val format = NumberFormat.getCurrencyInstance(Locale("es", "CL"))
        return format.format(amount)
    }
}
