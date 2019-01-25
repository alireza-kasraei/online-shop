package net.devk.marketing.service.targets;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.devk.marketing.service.model.TargetMember;
import net.devk.marketing.service.targets.dto.PersonnelTargetsListResponseDTO;
import net.devk.marketing.service.targets.dto.PersonnelTargetsResponseDTO;

interface TargetMemberRepository extends JpaRepository<TargetMember, Long> {

	@Query("select new net.devk.marketing.service.targets.dto.PersonnelTargetsResponseDTO(t.id,t.value,t.daysCount,tm.value) from TargetMember tm inner join tm.target t inner join tm.teamMember tmm inner join tmm.personnel p inner join t.service s where p.username=?1 and s.id=?2")
	public List<PersonnelTargetsResponseDTO> findPersonnelTargets(String username, Long serviceId);
	
	@Query("select new net.devk.marketing.service.targets.dto.PersonnelTargetsListResponseDTO(t.id,t.value,tm.value,s.name,s.id) from TargetMember tm inner join tm.target t inner join tm.teamMember tmm inner join tmm.personnel p inner join t.service s where p.username=?1")
	public List<PersonnelTargetsListResponseDTO> findTargets(String username);

	@Query(value = "SELECT sum(r.real_value) FROM targets t INNER JOIN targets_members tm on tm.target_id=t.id INNER JOIN requirements      r on r.target_member_id=tm.id INNER JOIN customers c on r.customer_id=c.id INNER JOIN requirement_statuses rs on rs.requirement_id=r.id INNER JOIN requirement_status_types rst on rs.requirement_status_type_id=rst.id  INNER JOIN assigned_requirements ar on ar.customer_requirement_id=r.id INNER JOIN assigned_requirements_statuses ars on ars.assigned_requirement_id=ar.id  INNER JOIN assigned_status_types ast on ast.id=ars.assigned_status_type_id  where rst.type_code=?3 and ast.type_code=?4 and t.register_date<=?2 and t.id=?1", nativeQuery = true)
	public Long sumTargetStatistics(Long targetId, Date currentDate, String requirementStatusTypeCode,
			String assignedStatusTypeCode);

}
