package com.architecturehexagonale

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

// Avec cette annotation, Spring Boot scanne toutes les classes qui se
// trouvent dans le même package et dans tous ses sous-packages. Cela
// inclut les autres modules. Pour cette raison, il ne faut pas mettre
// cette classe dans un package "bootstrap" par exemple.
// => https://stackoverflow.com/a/33080711/2430043
@SpringBootApplication
class ArchitectureHexagonale

fun main(args: Array<String>) {
    runApplication<ArchitectureHexagonale>(*args)
}
