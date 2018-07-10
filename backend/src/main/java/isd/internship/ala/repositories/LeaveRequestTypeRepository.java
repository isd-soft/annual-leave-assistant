package isd.internship.ala.repositories;

import isd.internship.ala.models.LeaveRequestType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRequestTypeRepository extends JpaRepository<LeaveRequestType, Integer> {
}
