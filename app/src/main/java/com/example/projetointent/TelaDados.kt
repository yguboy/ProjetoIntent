package com.example.projetointent

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun TelaDados(navController: NavHostController, viewModel: MainViewModel) {
    var nome by rememberSaveable { mutableStateOf(viewModel.pessoa?.nome ?: "") }
    var numeroTelefone by rememberSaveable { mutableStateOf(viewModel.pessoa?.numeroTelefone ?: "") }
    var descricao by rememberSaveable { mutableStateOf(viewModel.pessoa?.descricao ?: "") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Nome:")
        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text("Número de telefone:")
        OutlinedTextField(
            value = numeroTelefone,
            onValueChange = { numeroTelefone = it },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Next
            )
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text("Descrição do biotipo:")
        OutlinedTextField(
            value = descricao,
            onValueChange = { descricao = it },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            )
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val pessoa = Pessoa(nome, numeroTelefone, descricao)
            viewModel.setPessoa(pessoa)
            navController.popBackStack()
        }) {
            Text("Voltar")
        }
    }
}
