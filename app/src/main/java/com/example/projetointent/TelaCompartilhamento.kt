import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun <NavController> TelaCompartilhamento(navController: NavController) {
    var intencao by rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current

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
                intencao = intencao,
                context = context
            )
        }) {
            Text("Compartilhar")
        }
    }
}

fun compartilharDados(nome: String, telefone: String, biotipo: String, intencao: String, context: Context) {
    val shareIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, "Nome: $nome\nTelefone: $telefone\nBiotipo: $biotipo\nIntenção: $intencao")
        type = "text/plain"
    }
    context.startActivity(Intent.createChooser(shareIntent, "Compartilhar via"))
}
