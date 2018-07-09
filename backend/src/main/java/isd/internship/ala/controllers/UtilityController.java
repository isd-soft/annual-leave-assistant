package isd.internship.ala.controllers;


import isd.internship.ala.models.LeaveRequest;
import isd.internship.ala.models.LeaveRequestType;
import isd.internship.ala.models.Role;
import isd.internship.ala.models.User;
import isd.internship.ala.repositories.LeaveRequestRepository;
import isd.internship.ala.repositories.LeaveRequestTypeRepository;
import isd.internship.ala.repositories.RoleRepository;
import isd.internship.ala.repositories.UserRepository;
import isd.internship.ala.utility.EmailService;
import isd.internship.ala.utility.WordGenerator;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.time.temporal.ChronoUnit.DAYS;

@RestController
@CrossOrigin("*")
@RequestMapping("/ala/utility")
public class UtilityController {

    @Autowired
    LeaveRequestRepository leaveRequestRepository;

    @Autowired
    LeaveRequestTypeRepository leaveRequestTypeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/word/{id}")
    public ResponseEntity<byte[]> generateWord(@PathVariable(name = "id") Integer id ) throws IOException {

        LeaveRequest leaveRequest = leaveRequestRepository.findById(id).get();
        List<LeaveRequestType> leaveRequestTypes = leaveRequestTypeRepository.findAll();
        WordGenerator wordGenerator = new WordGenerator(leaveRequestTypes);
//        String savePath = "C:\\Users\\Miron\\Desktop\\" + leaveRequest.getUser().getName() + "_" + leaveRequest.getUser().getSurname() + "_" + leaveRequest.getRequestType().getName() + ".docx";

        long days = DAYS.between(leaveRequest.getStartDate(), leaveRequest.getEndDate()) + 1;
        Map<String, Object> replacementMap = new HashMap<String, Object>();
        replacementMap.put("name", leaveRequest.getUser().getName());
        replacementMap.put("surname", leaveRequest.getUser().getSurname());
        replacementMap.put("function", leaveRequest.getUser().getFunction());
        replacementMap.put("start_day", leaveRequest.getStartDate());
        replacementMap.put("end_day", leaveRequest.getEndDate());
        replacementMap.put("days", days);


        ByteArrayInputStream bos = wordGenerator.generateNewWord(replacementMap, leaveRequest.getLeaveRequestType().getName());

        byte[] array = IOUtils.toByteArray(bos);
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Disposition", "filename =");

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(array);
    }

    @GetMapping("/email/{id}")
    public void sendNotificationToUser(@PathVariable(name = "id") Long id) {

        try {
            User user = userRepository.findById(id).get();
            emailService.sendUserNotification(user);
        } catch (MailException e) {
            e.printStackTrace();
        }
    }


}
