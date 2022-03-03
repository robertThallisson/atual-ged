package com.atualged.util;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public abstract class Formatacao {
//	private For
	private static Formatacao uniqueInstance;
	Locale ptBr = new Locale("pt", "BR");
	NumeroExtenso numeroExtenso = new NumeroExtenso();
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	DateTimeFormatter formatterLiteral = DateTimeFormatter.ofLocalizedDate( FormatStyle.FULL ).withLocale( ptBr );
	
	private Formatacao() {

	}

	public static synchronized Formatacao getInstance() {

		if (uniqueInstance == null)
			uniqueInstance = new Formatacao() {};
			
		return uniqueInstance;
	}
	
	public String formatarMonetario(Float value) {
		return value != null ? NumberFormat.getCurrencyInstance(ptBr).format(value)	: "R$ 0";
	}
	
	public String formatarMonetarioLiteral(Float value) {
		if (value == null) {
			return "zero reais";
		}
		numeroExtenso.setNumber(value);
		return numeroExtenso.toMonetario();
	}
	
	public String formatarDate(LocalDate value) {
		if (value == null) {
			value = LocalDate.now();
		}

        return value.format(formatter);
	}
	
	public String formatarDateTime(LocalDateTime value) {
		if (value == null) {
			value = LocalDateTime.now();
		}

        return value.format(formatterTime);
	}
	
	public String formatarDateLiteral(LocalDate value) {
		if (value == null) {
			value = LocalDate.now();
		}
        return value.format(formatterLiteral);
	}
	
}
