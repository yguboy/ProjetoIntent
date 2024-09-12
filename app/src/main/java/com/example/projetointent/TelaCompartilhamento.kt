import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import java.lang.reflect.Modifier

@Composable
fun <NavController> TelaCompartilhamento(navController: NavController) {
    var intencao by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Nome: Nome de Exemplo")
        Spacer(modifier = Modifier.height(8.dp))
        Text("Número de telefone: Número de Exemplo")
        Spacer(modifier = Modifier.height(8.dp))
        Text("Descrição do biotipo: Biotipo de Exemplo")
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = intencao,
            onValueChange = { intencao = it },
            label = { Text("Intenção") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            compartilharDados(
                nome = "Nome de Exemplo",
                telefone = "Número de Exemplo",
                biotipo = "Biotipo de Exemplo",
                intencao = intencao
            )
        }) {
            Text("Compartilhar")
        }
    }
}

@Composable
fun compartilharDados(nome: String, telefone: String, biotipo: String, intencao: String) {
    val shareIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, "Nome: $nome\nTelefone: $telefone\nBiotipo: $biotipo\nIntenção: $intencao")
        type = "text/plain"
    }
    val context = LocalContext.current
    context.startActivity(Intent.createChooser(shareIntent, "Compartilhar via"))
}