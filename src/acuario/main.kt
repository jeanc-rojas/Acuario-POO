package acuario

fun construirAcuario() {
    val miAcuario = Acuario(ancho = 25, largo = 25, alto = 40)
    miAcuario.imprimirTamano()

    val miTorre = TanqueTorre(diametro = 25, alto = 40)
    miTorre.imprimirTamano()
}

fun crearPeces() {
    val tiburon = Tiburon()
    val pezPayaso = PezPayaso()

    println("El tiburón es de color: ${tiburon.color}")
    tiburon.comer()

    println("El pez payaso es de color: ${pezPayaso.color}")
    pezPayaso.comer()
}

fun main() {
    println("=== PARTE 1: CONSTRUCCIÓN DE ACUARIOS ===")
    construirAcuario()

    println("\n=== PARTE 2: PRUEBAS DE PECES ===")
    crearPeces()

    println("\n=== PARTE 3: EJEMPLO PROPIO - SISTEMA DE MANTENIMIENTO ===")
    ejecutarSistemaMantenimiento()
}


