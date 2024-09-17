package com.example.projetointent

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun TelaCompartilhamento(
    navController: NavHostController,
    nome: String,
    telefone: String,
    descricao: String
) {
    val context = LocalContext.current
    var intencao by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Nome: $nome")
        Spacer(modifier = Modifier.height(8.dp))
        Text("Telefone: $telefone")
        Spacer(modifier = Modifier.height(8.dp))
        Text("Biotipo: $descricao")
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = intencao,
            onValueChange = { intencao = it },
            label = { Text("Intenção") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_SUBJECT, "Dados Pessoais")
                    putExtra(
                        Intent.EXTRA_TEXT,
                        "Nome: $nome\nTelefone: $telefone\nBiotipo: $descricao\nIntenção: $intencao"
                    )
                }
                context.startActivity(Intent.createChooser(intent, "Compartilhe via"))
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Compartilhar")
        }
    }
}
