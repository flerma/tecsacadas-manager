package com.tecsacadas.tecsacadasmanager.data.db.lead;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

interface LeadFollowUpDao extends JpaRepository<LeadFollowUpEntity, Long> {


    @Query(value = "SELECT new com.tecsacadas.tecsacadasmanager.data.db.lead.ConversionsPerYearMonthResponse(" +
                   "               year(dataContato), " +
                   "               month(dataContato), " +
                   "               count(id)) " +
                   " FROM LeadFollowUp" +
                   "Entity WHERE year(dataContato) = :year" +
                   " GROUP by year(dataContato)," +
                   "          month(dataContato)" +
                   " ORDER BY year(dataContato)," +
                   "          month(dataContato)")
    List<ConversionsPerYearMonthResponse> findConversionsPerYearMonth(@Param("year") Integer year);

    @Query(value = "SELECT new com.tecsacadas.tecsacadasmanager.data.db.lead.DaysOfWeekWithMoreConversionsMonthResponse(" +
                   "  dataContato, " +
                   "  CASE weekday(dataContato)" +
                   "         WHEN 6 THEN 'Domingo'" +
                   "         WHEN 0 THEN 'Segunda-feira'" +
                   "         WHEN 1 THEN 'Terça-feira'" +
                   "         WHEN 2 THEN 'Quarta-feira'" +
                   "         WHEN 3 THEN 'Quinta-feira'" +
                   "         WHEN 4 THEN 'Sexta-feira'" +
                   "         WHEN 5 THEN 'Sabado'" +
                   "         ELSE 'other'" +
                   "  END," +
                   "  COUNT(id))" +
                   " FROM LeadFollowUp" +
                   "Entity WHERE extract(year from dataContato) = :year" +
                   "   AND extract(month from dataContato) = :month" +
                   " GROUP BY dataContato" +
                   " ORDER BY COUNT(id) desc")
    List<DaysOfWeekWithMoreConversionsMonthResponse> findDaysOfWeekWithMoreConversionsMonth(@Param("year") Integer year,
                                                                                            @Param("month") Integer month);

    @Query(value = "SELECT new com.tecsacadas.tecsacadasmanager.data.db.lead.DaysOfWeekWithMoreConversionsYearResponse(" +
            "  dataContato, " +
            "  CASE weekday(dataContato)" +
            "         WHEN 6 THEN 'Domingo'" +
            "         WHEN 0 THEN 'Segunda-feira'" +
            "         WHEN 1 THEN 'Terça-feira'" +
            "         WHEN 2 THEN 'Quarta-feira'" +
            "         WHEN 3 THEN 'Quinta-feira'" +
            "         WHEN 4 THEN 'Sexta-feira'" +
            "         WHEN 5 THEN 'Sabado'" +
            "         ELSE 'other'" +
            "  END," +
            "  COUNT(id))" +
            " FROM LeadFollowUp" +
            "Entity WHERE extract(year from dataContato) = :year" +
            " GROUP BY dataContato" +
            " ORDER BY COUNT(id) desc")
    List<DaysOfWeekWithMoreConversionsYearResponse> findDaysOfWeekWithMoreConversionsYear(@Param("year") Integer year);


    @Query(value = "SELECT new com.tecsacadas.tecsacadasmanager.data.db.lead.ConversionsPerYearMonthWeekResponse(" +
                   "       year(dataContato)," +
                   "       month(dataContato)," +
                   "       floor((DayOfMonth(dataContato)-1)/7)+1," +
                   "       count(id))" +
                   " FROM LeadFollowUp" +
                   "Entity WHERE year(dataContato) = :year" +
                   " GROUP BY year(dataContato)," +
                   "          month(dataContato)," +
                   "          floor((DayOfMonth(dataContato)-1)/7)+1" +
                   " ORDER BY year(dataContato)," +
                   "          month(dataContato)," +
                   "          floor((DayOfMonth(dataContato)-1)/7)+1")
    List<ConversionsPerYearMonthWeekResponse> findConversionsPerYearMonthWeek(@Param("year") Integer year);

    @Query(value = "SELECT count(id)" +
                   " FROM LeadFollowUpEntity " +
                   " WHERE month(dataContato) = :month" +
                   "   AND year(dataContato) = :year" +
                   "   AND coalesce(motivoNaoSerPotencial, '') = ''" +
                   " GROUP BY year(dataContato)," +
                   "          month(dataContato)" +
                   " ORDER BY year(dataContato)," +
                   "          month(dataContato)")
    Long findValidLeads(@Param("year") Integer year, @Param("month") Integer month);

    @Query(value = "SELECT count(id)" +
            " FROM LeadFollowUpEntity " +
            " WHERE month(dataContato) = :month" +
            "   AND year(dataContato) = :year" +
            "   AND coalesce(motivoNaoSerPotencial, '') <> ''" +
            " GROUP BY year(dataContato)," +
            "          month(dataContato)" +
            " ORDER BY year(dataContato)," +
            "          month(dataContato)")
    Long findInvalidLeads(@Param("year") Integer year, @Param("month") Integer month);
}
