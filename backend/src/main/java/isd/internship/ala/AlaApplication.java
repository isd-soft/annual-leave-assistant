package isd.internship.ala;

import isd.internship.ala.models.User;
import isd.internship.ala.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class AlaApplication implements CommandLineRunner{
	public static void main(String[] args) {
		SpringApplication.run(AlaApplication.class, args);
	}

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		// Populating database to test stuff
		LocalDate date = LocalDate.of(2018,12,12);
		userRepository.save(new User("root","root", "root", "root", date, "admin"));
		userRepository.save(new User("fiona@mail.md","aa", "Fiona", "Hij", date, "user"));
		userRepository.save(new User("nu_fiona@mail.md","aa", "NuFiona", "Helvetica", date, "user"));
		userRepository.save(new User("george@mail.md","aa", "George", "Porumbescu", date, "user"));
		userRepository.save(new User("arial@mail.md","aa", "Arial", "Balak", date, "user"));
	}
}