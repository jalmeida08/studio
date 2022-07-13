package br.com.jsa;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {

		List<String> lst = new ArrayList();
		lst.add("Ana Clara");
		lst.add("Jarilene");
		lst.add("Marilene");
		lst.add("Rita");
		lst.add("Abadiana");
		lst.add("Zonia");
		lst.add("Bastiana");

		System.out.println( new BCryptPasswordEncoder().encode("123123"));
		System.out.println(new BCryptPasswordEncoder());
//		lst.sort(Comparator.comparing(t -> t.));
		lst.sort((s1, s2) -> s1.compareTo(s2));
//		lst.forEach(System.out::println);
	}

}
