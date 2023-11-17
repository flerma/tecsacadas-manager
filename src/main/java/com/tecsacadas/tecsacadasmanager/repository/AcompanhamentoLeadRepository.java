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
                   "               cast(to_char(dataContato,'MM') as integer), " +
                   "               count(id)) " +
                   " FROM AcompanhamentoLead" +
                   " GROUP by extract(year from dataContato)," +
                   "          cast(to_char(dataContato,'MM') as integer)" +
                   " ORDER BY extract(year from dataContato)," +
                   "          cast(to_char(dataContato,'MM') as integer)")
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
                   " WHERE to_char(dataContato,'MM') = :mes" +
                   " GROUP BY dataContato" +
                   " ORDER BY COUNT(id) desc")
    List<DiasSemanaComMaisConversoesMesDto> findDiasSemanaComMaisConversoesMes(@Param("mes") String mes);

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
            " WHERE to_char(data_contato,'YYYY') = :ano" +
            " GROUP BY dataContato" +
            " ORDER BY COUNT(id) desc")
    List<DiasSemanaComMaisConversoesMesDto> findDiasSemanaComMaisConversoesAno(@Param("ano") String ano);


    @Query(value = "SELECT new com.tecsacadas.tecsacadasmanager.dto.ConversoesPorMesAnoSemanaDto(" +
                   "       extract(year from dataContato)," +
                   "       to_char(dataContato,'MM')," +
                   "       to_char(dataContato,'week')," +
                   "       count(id))" +
                   " FROM AcompanhamentoLead" +
                   " WHERE to_char(dataContato,'YYYY') = :ano" +
                   " GROUP BY extract(year from dataContato)," +
                   "         to_char(dataContato,'MM')," +
                   "         to_char(dataContato,'week')" +
                   " ORDER BY extract(year from dataContato)," +
                   "          to_char(dataContato,'MM')," +
                   "          to_char(dataContato,'week')")
    List<ConversoesPorMesAnoSemanaDto> findConversoesPorMesAnoSemana(@Param("ano") String ano);
}
