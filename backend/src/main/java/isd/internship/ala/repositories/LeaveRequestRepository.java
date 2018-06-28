package isd.internship.ala.repositories;

import isd.internship.ala.models.LeaveRequest;
import isd.internship.ala.models.Status;
import isd.internship.ala.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Integer> {
    List<LeaveRequest> findAllByUserIdEqualsOrderByRequestDate (User id);
    List<LeaveRequest> findAllByUserIdEqualsAndStatusIdEquals (User userId, Status statusId);
    List<LeaveRequest> findAllByStatusIdEqualsOrderByRequestDate (Status statusId);

}
