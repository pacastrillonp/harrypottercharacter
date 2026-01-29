package co.pacastrillon.harrypottercharacter.domain.model

sealed interface Filter {
    data object All : Filter
    data object Students : Filter
    data object Staff : Filter
    data class House(val name: String) : Filter
}
