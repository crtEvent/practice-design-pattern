package kdpbp2e.creational.factorymethod

import java.lang.RuntimeException

fun main() {
    val port = property("port: 8080")
    val environment = property("environment: prod")

    // 문제: port를 Int 타입으로 저장했으나 Property.value는 Any 타입이다.
    val newPort: Any = port.value

    // as Int로 캐스팅 할 수 있지만. 캐스팅 되지 않는 경우 프로그램이 종료된다
    val newPort2: Int = port.value as Int

    // 안전한 캐스팅을 할 수 있지만. 캐스팅 되지 않는 경우 null이 반환된다
    val newPort3: Int? = port.value as? Int
}

fun property(prop: String): Property {
    val (name, value) = prop.split(":")
    return when (name) {
        "port" -> PropertyImpl(name, value.trim().toInt())
        "environment" -> PropertyImpl(name, value.trim())
        else -> throw RuntimeException("Unknown property: $name")
    }
}

interface Property {
    val name: String
    val value: Any
}

interface ServerConfiguration {
    val properties: List<Property>
}

data class PropertyImpl(
    override val name: String,
    override val value: Any,
): Property

data class ServerConfigurationImpl(
    override val properties: List<Property>
): ServerConfiguration