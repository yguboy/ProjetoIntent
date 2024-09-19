package com.example.projetointent.view

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.projetointent.viewModel.MainViewModel

@Composable
fun TelaCompartilha(navController: NavHostController, viewModel: MainViewModel) {
    val pessoa = viewModel.pessoa
    val context = LocalContext.current
    var intention by remember { mutableStateOf("") }
    val internetAvailable = isInternetAvailable(context)

    val personalData = pessoa?.let {
        "Nome: ${it.nome} \nTelefone: ${it.numeroTelefone} \nDescrição: ${it.descricao}"
    } ?: "Nenhum dado pessoal disponível."

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Dados Pessoais Preenchidos:")
        Text(personalData)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Intenção:")
        TextField(value = intention, onValueChange = { intention = it })
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (internetAvailable) {
                    val intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, "$personalData\nIntenção: $intention")
                        type = "text/plain"
                    }
                    context.startActivity(Intent.createChooser(intent, "Compartilhe"))
                } else {
                    Toast.makeText(context, "Sem conexão com a internet", Toast.LENGTH_LONG).show()
                }
            },
            enabled = internetAvailable
        ) {
            Text("Compartilhar")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text("Voltar")
        }
    }
}

fun isInternetAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
    return networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
}