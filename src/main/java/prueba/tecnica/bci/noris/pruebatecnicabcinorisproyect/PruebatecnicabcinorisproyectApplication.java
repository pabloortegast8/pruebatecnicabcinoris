package prueba.tecnica.bci.noris.pruebatecnicabcinorisproyect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
		"prueba.tecnica.bci.noris.pruebatecnicabcinorisproyect",
		"com.noris.prueba.tec.controller",  // <-- Agrega el paquete del controlador
		"com.noris.prueba.tec.service", // agrega el paquete del service
		"com.noris.prueba.tec.repository" // agrega el paquete del repository
})
@EnableJpaRepositories(basePackages = "com.noris.prueba.tec.repository")  // sirve para scanear el repo
@EntityScan(basePackages = "com.noris.prueba.tec.model")
public class PruebatecnicabcinorisproyectApplication {

	public static void main(String[] args) {
		SpringApplication.run(PruebatecnicabcinorisproyectApplication.class, args);
	}
}
