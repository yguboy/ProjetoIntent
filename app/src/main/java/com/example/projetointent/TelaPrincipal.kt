import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.projetointent.MainViewModel
import com.example.projetointent.TelaCompartilha
import com.example.projetointent.TelaDados

@Composable
fun AppNavHost(navController: NavHostController, viewModel: MainViewModel) {
    NavHost(navController = navController, startDestination = "menu") {
        composable("menu") {
            MenuPrincipal(navController, viewModel)
        }
        composable("dados") {
            TelaDados(navController, viewModel)
        }
        composable("compartilha") {
            TelaCompartilha(navController, viewModel)
        }
    }
}

@Composable
fun MenuPrincipal(navController: NavHostController ,viewModel : MainViewModel) {
    val pessoa = viewModel.pessoa

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        pessoa?.let {
            Text(text = it.nome)
            Text(text = it.numeroTelefone)
            Text(text = it.descricao)
        } ?: Text(text = "Não há dados pessoais")

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("dados") }) {
            Text("Dados")
        }
        Button(
            onClick = {
                if (pessoa != null) {
                    navController.navigate("compartilha")
                }
            }
        ) {
            Text("Compartilhamento")
        }
    }
}
