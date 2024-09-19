package com.example.projetointent.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.projetointent.model.Pessoa

class MainViewModel : ViewModel() {
    private val _pessoa = mutableStateOf<Pessoa?>(null)
    val pessoa: Pessoa?
        get() = _pessoa.value

    fun setPessoa(pessoa: Pessoa) {
        _pessoa.value = pessoa
    }
}