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
                   "               extract(year from dataContato), " +
                   "               extract(month from dataContato), " +
                   "               count(id)) " +
                   " FROM AcompanhamentoLead" +
                   " GROUP by extract(year from dataContato)," +
                   "          extract(month from dataContato)" +
                   " ORDER BY extract(year from dataContato)," +
                   "          extract(month from dataContato)")
    List<ConversoesPorAnoMesDto> findConversoesPorMesAno();

    @Query(value = "SELECT new com.tecsacadas.tecsacadasmanager.dto.DiasSemanaComMaisConversoesMesDto(" +
                   "  dataContato, " +
                   "  CASE " +
                   "         WHEN extract(dow from dataContato) = 0 THEN 'Domingo'" +
                   "         WHEN extract(dow from dataContato) = 1 THEN 'Segunda-feira'" +
                   "         WHEN extract(dow from dataContato) = 2 THEN 'Terça-feira'" +
                   "         WHEN extract(dow from dataContato) = 3 THEN 'Quarta-feira'" +
                   "         WHEN extract(dow from dataContato) = 4 THEN 'Quinta-feira'" +
                   "         WHEN extract(dow from dataContato) = 5 THEN 'Sexta-feira'" +
                   "         WHEN extract(dow from dataContato) = 6 THEN 'Sabado'" +
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
            "  CASE " +
            "         WHEN extract(dow from dataContato) = 0 THEN 'Domingo'" +
            "         WHEN extract(dow from dataContato) = 1 THEN 'Segunda-feira'" +
            "         WHEN extract(dow from dataContato) = 2 THEN 'Terça-feira'" +
            "         WHEN extract(dow from dataContato) = 3 THEN 'Quarta-feira'" +
            "         WHEN extract(dow from dataContato) = 4 THEN 'Quinta-feira'" +
            "         WHEN extract(dow from dataContato) = 5 THEN 'Sexta-feira'" +
            "         WHEN extract(dow from dataContato) = 6 THEN 'Sabado'" +
            "         ELSE 'other'" +
            "  END," +
            "  COUNT(id))" +
            " FROM AcompanhamentoLead" +
            " WHERE extract(year from dataContato) = :ano" +
            " GROUP BY dataContato" +
            " ORDER BY COUNT(id) desc")
    List<DiasSemanaComMaisConversoesMesDto> findDiasSemanaComMaisConversoesAno(@Param("ano") Integer ano);


    @Query(value = "SELECT new com.tecsacadas.tecsacadasmanager.dto.ConversoesPorMesAnoSemanaDto(" +
                   "       extract(year from dataContato)," +
                   "       extract(month from dataContato)," +
                   "       to_char(dataContato,'week')," +
                   "       count(id))" +
                   " FROM AcompanhamentoLead" +
                   " WHERE extract(year from dataContato) = :ano" +
                   " GROUP BY extract(year from dataContato)," +
                   "          extract(month from dataContato)," +
                   "          to_char(dataContato,'week')" +
                   " ORDER BY extract(year from dataContato)," +
                   "          extract(month from dataContato)," +
                   "          to_char(dataContato,'week')")
    List<ConversoesPorMesAnoSemanaDto> findConversoesPorMesAnoSemana(@Param("ano") Integer ano);

    @Query(value = "SELECT count(id)" +
                   " FROM AcompanhamentoLead " +
                   " WHERE extract(month from dataContato) = :mes" +
                   "   AND extract(year from dataContato) = :ano" +
                   "   AND coalesce(motivoNaoSerPotencial, '') = ''" +
                   " GROUP BY extract(year from dataContato)," +
                   "          extract(year from dataContato)" +
                   " ORDER BY extract(year from dataContato)," +
                   "          extract(year from dataContato)")
    Long findLeadsValidos(@Param("ano") Integer ano, @Param("mes") Integer mes);

    @Query(value = "SELECT count(id)" +
            " FROM AcompanhamentoLead " +
            " WHERE extract(month from dataContato) = :mes" +
            "   AND extract(year from dataContato) = :ano" +
            "   AND coalesce(motivoNaoSerPotencial, '') <> ''" +
            " GROUP BY extract(year from dataContato)," +
            "          extract(month from dataContato)" +
            " ORDER BY extract(year from dataContato)," +
            "          extract(month from dataContato)")
    Long findLeadsInvalidos(@Param("ano") Integer ano, @Param("mes") Integer mes);
}
