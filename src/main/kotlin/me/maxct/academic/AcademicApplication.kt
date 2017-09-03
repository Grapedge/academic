package me.maxct.academic

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.cache.annotation.EnableCaching
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters

@EntityScan(
    basePackageClasses = arrayOf(AcademicApplication::class, Jsr310JpaConverters::class)
)
@SpringBootApplication
@EnableCaching
class AcademicApplication

fun main(args: Array<String>) {
    SpringApplication.run(AcademicApplication::class.java, *args)
}
