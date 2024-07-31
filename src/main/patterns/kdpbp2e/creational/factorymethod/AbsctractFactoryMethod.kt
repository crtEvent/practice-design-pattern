package kdpbp2e.creational.factorymethod

import java.lang.RuntimeException

fun main() {
    val properties = Parser.server(listOf("port: 8080", "environment: prod"))
    println(properties)
    // 출력:
    // ServerConfigurationImpl(
    //   properties=[
    //     IntProperty(name=port, value=8080),
    //     StringProperty(name=environment, value=prod)])
}

class Parser {

    companion object {
        fun server(propertyStrings: List<String>): ServerConfiguration {
            val parsedProperties = mutableListOf<Property>()
            for (p in propertyStrings) {
                parsedProperties += property(p)
            }

            return ServerConfigurationImpl(parsedProperties)
        }

        fun property(prop: String): Property {
            val (name, value) = prop.split(":")

            return when (name) {
                "port" -> IntProperty(name, value.trim().toInt())
                "environment" -> StringProperty(name, value.trim())
                else -> throw RuntimeException("Unknown property: $name")
            }
        }
    }
}

interface Property {
    val name: String
    val value: Any
}

interface ServerConfiguration {
    val properties: List<Property>
}

data class IntProperty(
    override val name: String,
    override val value: Int,
): Property

data class StringProperty(
    override val name: String,
    override val value: String,
): Property

data class ServerConfigurationImpl(
    override val properties: List<Property>
): ServerConfiguration