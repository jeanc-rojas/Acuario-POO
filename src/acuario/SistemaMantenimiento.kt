package acuario

// 1. Interfaz para mantenimiento
interface Mantenible {
    fun realizarMantenimiento()
}

// 2. Clase abstracta para componentes del acuario
abstract class DispositivoAcuario(val nombre: String, val marca: String) {
    abstract val consumoWatts: Int
    var encendido: Boolean = false

    fun encender() {
        encendido = true
        println("[$nombre] Activado.")
    }

    fun apagar() {
        encendido = false
        println("[$nombre] Desactivado.")
    }

    abstract fun mostrarEstado()
}

// 3. Subclase FiltroAgua con herencia, override y constructor secundario
open class FiltroAgua(
    nombre: String,
    marca: String,
    var flujoLph: Int = 500
) : DispositivoAcuario(nombre, marca), Mantenible {

    override val consumoWatts: Int = 25

    init {
        println("Filtro '$nombre' registrado con flujo de $flujoLph L/h.")
    }

    // Constructor secundario
    constructor(nombre: String) : this(nombre, "Eheim", 300)

    override fun realizarMantenimiento() {
        println("Limpiando esponjas y cambiando material filtrante de: $nombre")
    }

    override fun mostrarEstado() {
        val estadoStr = if (encendido) "OPERATIVO" else "DETENIDO"
        println("Dispositivo: $nombre | Marca: $marca | Estado: $estadoStr | Consumo: ${consumoWatts}W | Flujo: $flujoLph L/h")
    }
}

// 4. Subclase SondaCalidadAgua con getters y setters de validación
class SondaCalidadAgua(
    nombre: String,
    marca: String
) : DispositivoAcuario(nombre, marca), Mantenible {

    override val consumoWatts: Int = 5

    var nivelPh: Double = 7.0
        set(valor) {
            field = valor
            if (valor < 6.5 || valor > 8.5) {
                println("¡ALERTA EN $nombre! pH fuera de rango seguro: $valor")
            }
        }

    override fun realizarMantenimiento() {
        println("Calibrando sensores de la sonda $nombre con solución buffer.")
    }

    override fun mostrarEstado() {
        val estadoStr = if (encendido) "MONITOREANDO" else "INACTIVO"
        println("Dispositivo: $nombre | Estado: $estadoStr | Lectura pH: $nivelPh")
    }
}

// Función ejecutora del sistema propio
fun ejecutarSistemaMantenimiento() {
    println("\n=== SISTEMA DE MANTENIMIENTO DEL ACUARIO ===")

    val filtroPrincipal = FiltroAgua("Filtro Canister UV", "Fluval", 1200)
    val sondaPh = SondaCalidadAgua("Sonda pH Digital", "AquaMedic")

    filtroPrincipal.encender()
    filtroPrincipal.mostrarEstado()
    filtroPrincipal.realizarMantenimiento()

    println("---")

    sondaPh.encender()
    sondaPh.nivelPh = 6.2 // Dispara la alerta por el setter
    sondaPh.mostrarEstado()
    sondaPh.realizarMantenimiento()
}