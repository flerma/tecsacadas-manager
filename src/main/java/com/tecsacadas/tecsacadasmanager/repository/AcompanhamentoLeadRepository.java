package com.tecsacadas.tecsacadasmanager.repository;

import com.tecsacadas.tecsacadasmanager.domain.model.AcompanhamentoLead;
import com.tecsacadas.tecsacadasmanager.dto.ConversoesPorAnoMesDto;
import com.tecsacadas.tecsacadasmanager.dto.ConversoesPorMesAnoSemanaDto;
import com.tecsacadas.tecsacadasmanager.dto.DiasSemanaComMaisConversoesMesDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AcompanhamentoLeadRepository extends JpaRepository<AcompanhamentoLead, Long> {


    @Query(value = "SELECT new com.tecsacadas.tecsacadasmanager.dto.ConversoesPorAnoMesDto(" +
                   "               year(dataContato), " +
                   "               month(dataContato), " +
                   "               count(id)) " +
                   " FROM AcompanhamentoLead" +
                   " WHERE year(dataContato) = :ano" +
                   " GROUP by year(dataContato)," +
                   "          month(dataContato)" +
                   " ORDER BY year(dataContato)," +
                   "          month(dataContato)")
    List<ConversoesPorAnoMesDto> findConversoesPorMesAno(@Param("ano") Integer ano);

    @Query(value = "SELECT new com.tecsacadas.tecsacadasmanager.dto.DiasSemanaComMaisConversoesMesDto(" +
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
                   " FROM AcompanhamentoLead" +
                   " WHERE extract(year from dataContato) = :ano" +
                   "   AND extract(month from dataContato) = :mes" +
                   " GROUP BY dataContato" +
                   " ORDER BY COUNT(id) desc")
    List<DiasSemanaComMaisConversoesMesDto> findDiasSemanaComMaisConversoesMes(@Param("ano") Integer ano,
                                                                               @Param("mes") Integer mes);

    @Query(value = "SELECT new com.tecsacadas.tecsacadasmanager.dto.DiasSemanaComMaisConversoesMesDto(" +
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
            " FROM AcompanhamentoLead" +
            " WHERE extract(year from dataContato) = :ano" +
            " GROUP BY dataContato" +
            " ORDER BY COUNT(id) desc")
    List<DiasSemanaComMaisConversoesMesDto> findDiasSemanaComMaisConversoesAno(@Param("ano") Integer ano);


    @Query(value = "SELECT new com.tecsacadas.tecsacadasmanager.dto.ConversoesPorMesAnoSemanaDto(" +
                   "       year(dataContato)," +
                   "       month(dataContato)," +
                   "       floor((DayOfMonth(dataContato)-1)/7)+1," +
                   "       count(id))" +
                   " FROM AcompanhamentoLead" +
                   " WHERE year(dataContato) = :ano" +
                   " GROUP BY year(dataContato)," +
                   "          month(dataContato)," +
                   "          floor((DayOfMonth(dataContato)-1)/7)+1" +
                   " ORDER BY year(dataContato)," +
                   "          month(dataContato)," +
                   "          floor((DayOfMonth(dataContato)-1)/7)+1")
    List<ConversoesPorMesAnoSemanaDto> findConversoesPorMesAnoSemana(@Param("ano") Integer ano);

    @Query(value = "SELECT count(id)" +
                   " FROM AcompanhamentoLead " +
                   " WHERE month(dataContato) = :mes" +
                   "   AND year(dataContato) = :ano" +
                   "   AND coalesce(motivoNaoSerPotencial, '') = ''" +
                   " GROUP BY year(dataContato)," +
                   "          month(dataContato)" +
                   " ORDER BY year(dataContato)," +
                   "          month(dataContato)")
    Long findLeadsValidos(@Param("ano") Integer ano, @Param("mes") Integer mes);

    @Query(value = "SELECT count(id)" +
            " FROM AcompanhamentoLead " +
            " WHERE month(dataContato) = :mes" +
            "   AND year(dataContato) = :ano" +
            "   AND coalesce(motivoNaoSerPotencial, '') <> ''" +
            " GROUP BY year(dataContato)," +
            "          month(dataContato)" +
            " ORDER BY year(dataContato)," +
            "          month(dataContato)")
    Long findLeadsInvalidos(@Param("ano") Integer ano, @Param("mes") Integer mes);
}
