package br.com.jsa.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

public class DateUtil {

    public static Integer retornarMesDeMesAno(String mesAno){
        if(mesAno != null)
            return Integer.valueOf(mesAno.substring(4));
        else
            return LocalDate.now().getMonth().getValue();
    }

    public static Integer recuperarAnoDeMesAno(String mesAno){
        if(mesAno != null)
            return Integer.valueOf(mesAno.substring(0,4));
        else
            return LocalDate.now().getYear();
    }

    public static LocalDateTime retornaDataInicio(String anoMes){
        if(anoMes.isEmpty())
        	anoMes = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMM"));

        final Integer ano = recuperarAnoDeMesAno(anoMes);
        final Integer mes = retornarMesDeMesAno(anoMes);
        
        return recuperaPrimeiroDiaMesInformado(ano, mes);
    }

    public static LocalDateTime retornaDataFim(String anoMes){
        if(anoMes.isEmpty())
        	anoMes = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMM"));
        final Integer ano = recuperarAnoDeMesAno(anoMes);
        final Integer mes = retornarMesDeMesAno(anoMes);
        return recuperaUltimoDiaMesInformado(ano, mes);
    }
    
    @SuppressWarnings("static-access")
	public static LocalDateTime recuperaUltimoDiaMesInformado(Integer ano, Integer mes){
        return LocalDateTime.now().MAX.withMonth(mes).withYear(ano).with(TemporalAdjusters.lastDayOfMonth());
    }

    @SuppressWarnings("static-access")
	public static LocalDateTime recuperaPrimeiroDiaMesInformado(Integer ano, Integer mes){
        return LocalDateTime.now().MIN.withMonth(mes).withYear(ano).with(TemporalAdjusters.firstDayOfMonth());
    }
}
