package com.example.projetointent

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun TelaCompartilha(navController: NavHostController, viewModel : MainViewModel) {
    var pessoa = viewModel.pessoa
    val context = LocalContext.current
    var intention by remember { mutableStateOf("") }

    val personalData = pessoa?.let {
        "Nome: ${it.nome} \nTelefone: ${it.numeroTelefone} \nDescrição: ${it.descricao}"
    } ?: "Nenhum dado pessoal disponível."

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)
        ,horizontalAlignment = Alignment.CenterHorizontally
        ,verticalArrangement = Arrangement.Center) {
        Text("Dados Pessoais Preenchidos:")
        Text(personalData)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Intenção:")
        TextField(value = intention, onValueChange = { intention = it })
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "$personalData\nIntenção: $intention")
                type = "text/plain"
            }
            context.startActivity(Intent.createChooser(intent, "Compartilhe"))
        }) {
            Text("Compartilhar")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text("Voltar")
        }
    }
}
