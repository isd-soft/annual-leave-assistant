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
import java.util.Date;

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


		if(annual == null) {
			annual = new LeaveRequestType("Annual");
			annual.setDescription("Annual leave is paid time off work granted by employers to employees to be used for whatever the employee wishes.");
		}

		if(studies == null) {
			studies = new LeaveRequestType("Studies");
			studies.setDescription("In connection with exams it is possible to request leave for up to 21 work days.\n" + "This type of absence can only be used for entire days.");
		}

		if(personal == null) {
			personal = new LeaveRequestType("Personal");
			personal.setDescription("Personal leave refers to short-term absences for reasons other than illness, such as taking a child to a physician, school appointments, time to run errands, etc.");
		}

		if(maternity == null) {
			maternity = new LeaveRequestType("Maternity");
			maternity.setDescription("To be used if self-certified sick leave is due to pregnancy-related problems.");
		}

		if(paternity == null) {
			paternity = new LeaveRequestType("Paternity");
			paternity.setDescription("Paternity leave is a period of either one or two consecutive weeks that fathers or partners can take off from work to care for their baby or child.");
		}

		if(marriage == null) {
			marriage = new LeaveRequestType("Marriage");
			marriage.setDescription("Marriage Leave is usually fully paid leave; but you may decide for it to be part-paid or unpaid if the leave you granted is for an extended period of time.");
		}



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