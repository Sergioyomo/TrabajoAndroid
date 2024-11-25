package com.example.proyecto1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto1.ui.theme.Proyecto1Theme
import androidx.compose.material.icons.filled.PowerSettingsNew
import androidx.compose.ui.graphics.Color.Companion.Green

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GreetingPreview()
        }
    }
}

@Composable
fun App() {
    Proyecto1Theme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Titulo(
                name = "Android",
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun Titulo(name: String, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.background(color = Color.Blue)
    ){
        Text(
            text = "Tipo de $name",
            modifier = modifier,
            //textAlign = TextAlign.Center,
            fontSize = 32.sp,
            color = Color.White
        )
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val dispositivo11 = AccordionModel.Dispositivo(nombre = "AirClima 18000 Multi Connected (Aire acondicionado)")
    val dispositivo12 = AccordionModel.Dispositivo(nombre = "KETOTEK WiFi (Termostato)", conectado = true)
    val dispositivo13 = AccordionModel.Dispositivo(nombre = "Daitsu ECO DS-12KDR-2 (Aire Acondicionado)")

    val listaRow1 : List<AccordionModel.Dispositivo> = listOf(dispositivo11,dispositivo12,dispositivo13)
    val modelo = AccordionModel(header = "Temperatura y humedad",listaRow1)

    val dispositivo21 = AccordionModel.Dispositivo(nombre = "Xiaomi Smart Camera C301 (Cámara de vigilancia)", conectado = true)
    val dispositivo22 = AccordionModel.Dispositivo(nombre = "DINUY DM SUP 000 (Detector de movimiento)")
    val dispositivo23 = AccordionModel.Dispositivo(nombre = "Etrogo Bombillas LED (Sensor de Movimiento)")

    val listaRow2 : List<AccordionModel.Dispositivo> = listOf(dispositivo21,dispositivo22,dispositivo23)
    val modelo2 = AccordionModel(header = "Movimiento (PIR)",listaRow2)

    val dispositivo31 = AccordionModel.Dispositivo(nombre = "Nuki Smart Lock Pro (cerradura inteligente)")
    val dispositivo32 = AccordionModel.Dispositivo(nombre = "Nuki Smart Lock Pro (cerradura inteligente)(II)")
    val dispositivo33 = AccordionModel.Dispositivo(nombre = "Panamalar Wifi (Sensor de Ventana)", conectado = true)

    val listaRow3 : List<AccordionModel.Dispositivo> = listOf(dispositivo31,dispositivo32,dispositivo33)
    val modelo3 = AccordionModel(header = "Apertura",listaRow3)


    Proyecto1Theme {
        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            Column (
                modifier = Modifier.padding(innerPadding)
            ) {

                Titulo(name="Sensores",Modifier.fillMaxWidth().padding(5.dp))
                HorizontalDivider(thickness = 2.dp)
                Accordion(model=modelo)
                Accordion(model=modelo2)
                Accordion(model=modelo3)

            }

        }
    }
}



data class AccordionModel(
    val header: String,
    val rows: List<Dispositivo>
) {
    data class Dispositivo(
        val nombre: String,
        val id: Int = 0,
        val conectado: Boolean = false
    )
}

@Preview
@Composable
private fun AccordionRow(
    model: AccordionModel.Dispositivo = AccordionModel.Dispositivo("Dispositivo 1")
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { /*TODO: enviar a la siguiente pantalla con 'model'*/ }.padding(8.dp)
    ) {
        Text(model.nombre, Modifier.weight(1f), color = Color.Black, textAlign = TextAlign.Center)
        if (model.conectado){
            Icon(
                Icons.Filled.PowerSettingsNew,
                contentDescription = "power_settings_new",
                tint = Green
            )
        } else {
            Icon(
                Icons.Filled.PowerSettingsNew,
                contentDescription = "power_settings_new",
                tint = Red
            )
        }
    }
}

@Preview
@Composable
private fun AccordionHeader(
    title: String = "subtipo",
    isExpanded: Boolean = false,
    onTapped: () -> Unit = {}
) {
    val degrees = if (isExpanded) 180f else 0f

    Surface(
        color = White,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color.LightGray),
        //elevation = 8.dp,
    ) {
        Row(
            modifier = Modifier
                .clickable { onTapped() }
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(title, Modifier.weight(1f),  color = Color.Gray)
            Surface(shape = CircleShape, color = Color.Blue.copy(alpha = 0.6f)) {
                Icon(
                    Icons.Outlined.ArrowDropDown,
                    contentDescription = "arrow-down",
                    modifier = Modifier.rotate(degrees),
                    tint = White
                )
            }
        }
    }
}


@Composable
fun Accordion(modifier: Modifier = Modifier, model: AccordionModel) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        AccordionHeader(title = model.header, isExpanded = expanded) {
            expanded = !expanded
        }
        AnimatedVisibility(visible = expanded) {
            Surface(
                color = White,
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, Color.LightGray),
                //elevation = 1.dp,
                modifier = Modifier.padding(top = 8.dp)
            ) {
                LazyColumn {
                    items(model.rows) { row ->
                        AccordionRow(row)
                        HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
                    }
                }
            }
        }
    }
}