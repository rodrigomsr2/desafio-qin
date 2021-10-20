package com.tenniscourts.schedules;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.OffsetDateTime;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

	Boolean existsByTennisCourtIdAndStartDateTime(Long tennisCourtId, OffsetDateTime startDateTime);
	
    List<Schedule> findByTennisCourt_IdOrderByStartDateTime(Long id);
    
    List<Schedule> findAllByStartDateTimeLessThanEqualAndEndDateTimeGreaterThanEqual(OffsetDateTime endDateTime, OffsetDateTime startDateTime);

    @Query(nativeQuery = true, 
    		value =    "select * "
		    		+ " from schedule "
		    		+ " where id not in ( "
			    		+ "	select schedule_id "
			    		+ "	from reservation "
			    		+ "	where reservation_status not in ('CANCELLED', 'RESCHEDULED') "
		    		+ " )")
    List<Schedule> findFreeSchedules();
    
    @Query("from Schedule s where startDateTime = :iniDate and s.tennisCourt.id in :ids")
    List<Schedule> findSchedulesBy(@Param("iniDate") OffsetDateTime iniDate, @Param("ids") List<Long> ids);
    
    
    
}