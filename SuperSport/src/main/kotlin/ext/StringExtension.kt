package ext

fun String.extractNumbersToInt(): Int {
    return this.filter { it.isDigit() }.toInt()
}

fun String.extractAlphabets(): String {
    return this.filter { it.isLetter() }
}

fun String.extractAlphabetsWithoutQuotations(): String {
    return this.filter { it != '\'' && it != '\"' }
}