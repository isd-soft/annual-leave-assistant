package isd.internship.ala;

import isd.internship.ala.models.LeaveRequest;
import isd.internship.ala.models.LeaveRequestType;
import isd.internship.ala.models.Role;
import isd.internship.ala.models.User;
import isd.internship.ala.repositories.LeaveRequestRepository;
import isd.internship.ala.repositories.LeaveRequestTypeRepository;
import isd.internship.ala.repositories.RoleRepository;
import isd.internship.ala.repositories.UserRepository;
import isd.internship.ala.services.LeaveRequestTypeService;
import isd.internship.ala.services.RoleService;
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

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private LeaveRequestTypeService leaveRequestTypeService;

	@Override
	public void run(String... args) throws Exception {
		Role adminRole = roleRepository.findByRole("ADMIN");
		Role userRole = roleRepository.findByRole("USER");

		LeaveRequestType annual = leaveRequestTypeService.findByName("Annual");
		LeaveRequestType studies = leaveRequestTypeService.findByName("Studies");
		LeaveRequestType personal = leaveRequestTypeService.findByName("Personal");
		LeaveRequestType maternity = leaveRequestTypeService.findByName("Maternity");
		LeaveRequestType paternity = leaveRequestTypeService.findByName("Paternity");
		LeaveRequestType marriage = leaveRequestTypeService.findByName("Marriage");


		if(annual == null)
			annual = new LeaveRequestType("Annual");

		if(studies == null)
			studies = new LeaveRequestType("Studies");

		if(personal == null)
			personal = new LeaveRequestType("Personal");

		if(maternity == null)
			maternity = new LeaveRequestType("Maternity");

		if(paternity == null)
			paternity = new LeaveRequestType("Paternity");

		if(marriage == null)
			marriage = new LeaveRequestType("Marriage");



		if(adminRole == null)
			adminRole = roleRepository.save(new Role("ADMIN"));

		if(userRole == null)
			userRole = roleRepository.save(new Role("USER"));


		LocalDate date = LocalDate.of(2018,12,12);

		// Root user
		User root = new User("root@isd", "root","root", "root", date, adminRole);
		userRepository.save(root);

		// Leave request types
		leaveRequestTypeService.create(annual);
		leaveRequestTypeService.create(studies);
		leaveRequestTypeService.create(personal);
		leaveRequestTypeService.create(maternity);
		leaveRequestTypeService.create(paternity);
		leaveRequestTypeService.create(marriage);

		// Populating database to test stuff
		userRepository.save(new User("fiona@mail.md","aa", "Fiona", "Hij", date, userRole));
		userRepository.save(new User("nu_fiona@mail.md","aa", "NuFiona", "Helvetica", date, userRole));
		userRepository.save(new User("george@mail.md","aa", "George", "Porumbescu", date, userRole));
		userRepository.save(new User("arial@mail.md","aa", "Arial", "Balak", date, userRole));
	}
}