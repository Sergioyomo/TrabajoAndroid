package com.example.proyecto1

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PowerSettingsNew
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CombinedApp()
        }
    }
}

@Composable
fun CombinedApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "menu") {
        composable("menu") { MainMenu(navController) }
        composable("exercise1") { Exercise1Screen() }
        composable("exercise2") { Exercise2Screen() }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainMenu(navController: NavController) {
    Scaffold {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Button(onClick = { navController.navigate("exercise1") }) {
                    Text("Tipos de sensores (Sergio Gómez Sánchez)")
                }
                Button(
                    onClick = { navController.navigate("exercise2") },
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text("Inicio (Jose Francisco Mora Garcia)")
                }
            }
        }
    }
}

// ---------------------------- Ejercicio 1 ----------------------------
@Composable
fun Exercise1Screen() {
    val titulo="Sensores"
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

    val listaModelos by rememberSaveable{mutableStateOf(listOf(modelo,modelo2,modelo3))}

    Exercise1Theme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column (
                modifier = Modifier.padding(innerPadding)
            ) {

                Titulo(name=titulo,Modifier.fillMaxWidth().padding(5.dp))
                HorizontalDivider(thickness = 2.dp)
                Body(listaModelos)

            }
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
@Composable
fun Body(listaModelos: List<AccordionModel>) {
    for (i in 0..(listaModelos.size - 1)){
        Accordion(model=listaModelos.get(i))
    }
}
@Composable
fun Exercise1Theme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = Color.Blue,
            onPrimary = Color.White,
            background = Color.LightGray,
            onBackground = Color.Black
        ),
        typography = Typography(),
        content = content
    )
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
// ---------------------------- Ejercicio 2 ----------------------------
@Composable
fun Exercise2Screen() {
    DeviceSelectorApp()
}

@Composable
fun DeviceSelectorApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "device_list") {
        composable("device_list") {
            DeviceListScreen(navController)
        }
        composable("selected_device/{deviceType}/{deviceName}") { backStackEntry ->
            val deviceType = backStackEntry.arguments?.getString("deviceType") ?: ""
            val deviceName = backStackEntry.arguments?.getString("deviceName") ?: ""
            SelectedDeviceScreen(deviceType, deviceName)
        }
    }
}

@Composable
fun DeviceListScreen(navController: NavController) {
    val deviceCategories = mapOf(
        "Sensores" to listOf(
            "Sensor de Temperatura y Humedad",
            "Sensor de Movimiento",
            "Sensor de Apertura",
            "Sensor de Presión"
        ),
        "Actuadores" to listOf(
            "Relé Inteligente",
            "Actuador de Válvula",
            "Servomotor",
            "Controlador de Iluminación"
        ),
        "Dispositivos de Monitoreo y Control Complejo" to listOf(
            "Cámara IP",
            "Controlador de Clima",
            "Estación Meteorológica",
            "Contador de Energía"
        )
    )

    var selectedDeviceType by remember { mutableStateOf("") }
    var selectedDeviceName by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Lista de Dispositivos", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        LazyColumn(modifier = Modifier.weight(1f).padding(top = 16.dp)) {
            deviceCategories.forEach { (category, devices) ->
                item {
                    Text(
                        text = category,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
                devices.forEach { device ->
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    selectedDeviceType = category
                                    selectedDeviceName = device
                                }
                                .padding(vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(device)
                        }
                    }
                }
            }
        }

        Button(
            onClick = {
                if (selectedDeviceType.isNotEmpty() && selectedDeviceName.isNotEmpty()) {
                    navController.navigate(
                        "selected_device/$selectedDeviceType/$selectedDeviceName"
                    )
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Añadir")
        }
    }
}

@Composable
fun SelectedDeviceScreen(deviceType: String, deviceName: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text(
            text = "Has seleccionado un \"$deviceType\": \"$deviceName\"",
            fontSize = 18.sp
        )
    }
}
