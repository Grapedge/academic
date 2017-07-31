package me.maxct.academic

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class AcademicApplication

fun main(args: Array<String>) {
    SpringApplication.run(AcademicApplication::class.java, *args)
}
