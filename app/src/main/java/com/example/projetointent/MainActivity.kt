package com.example.projetointent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.projetointent.TelaCompartilhamento
import com.example.projetointent.TelaDados
import com.example.projetointent.TelaMenu

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjetoIntentApp()
        }
    }
}

@Composable
fun ProjetoIntentApp() {
    val navController = rememberNavController()

    var nome by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }

    NavHost(navController = navController, startDestination = "menu") {
        composable("menu") {
            TelaMenu(navController)
        }
        composable("dados") {
            TelaDados(navController, onSave = { newNome, newTelefone, newDescricao ->
                nome = newNome
                telefone = newTelefone
                descricao = newDescricao
                navController.popBackStack()
            })
        }
        composable("compartilhamento") {
            TelaCompartilhamento(navController, nome, telefone, descricao)
        }
    }
}
