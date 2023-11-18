package com.fernando;

import com.fernando.model.Usuario;
import com.fernando.model.Participacao;
import com.fernando.repository.UsuarioRepository;
import com.fernando.repository.ParticipacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class ResTful01SbApplication implements CommandLineRunner {

	@Autowired
	private ParticipacaoRepository participacaoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	public static void main(String[] args) {
		SpringApplication.run(ResTful01SbApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		participacaoRepository.deleteAll();
		usuarioRepository.deleteAll();

		Usuario usuario1 = new Usuario("nandinhomsf", "nandinhomsf");
		usuarioRepository.save(usuario1);
		Usuario usuario2 = new Usuario("pedrosdos", "pedrosdos");
		usuarioRepository.save(usuario2);
		Usuario usuario3 = new Usuario("yurinmds", "yurinmds");
		usuarioRepository.save(usuario3);

		Usuario usuario4 = new Usuario("pruzny", "pruzny");
		usuarioRepository.save(usuario4);

		Participacao participacao = new Participacao(
				"yorick.jpg",
				usuario1,
				"Yorick",
				LocalDate.of(2023, 7, 22),
				100,
				BigDecimal.valueOf(6.2));
		participacaoRepository.save(participacao);

		participacao = new Participacao(
				"hecarim.jpg",
				usuario2,
				"Hecarim",
				LocalDate.of(2023, 5, 22),
				200,
				BigDecimal.valueOf(145));
		participacaoRepository.save(participacao);

		participacao = new Participacao(
				"amumu.jpg",
				usuario2,
				"Amumu",
				LocalDate.of(2023, 3, 24),
				400,
				BigDecimal.valueOf(99));
		participacaoRepository.save(participacao);

		participacao = new Participacao(
				"rengar.jpg",
				usuario3,
				"Rengar",
				LocalDate.of(2023, 3, 12),
				120,
				BigDecimal.valueOf(23));
		participacaoRepository.save(participacao);

		participacao = new Participacao(
				"fiora.jpg",
				usuario3,
				"Fiora",
				LocalDate.of(2023, 5, 17),
				340,
				BigDecimal.valueOf(43));
		participacaoRepository.save(participacao);

		participacao = new Participacao(
				"gwen.jpg",
				usuario3,
				"Gwen",
				LocalDate.of(2023, 5, 14),
				220,
				BigDecimal.valueOf(62));
		participacaoRepository.save(participacao);

		participacao = new Participacao(
				"lux.jpg",
				usuario1,
				"Lux",
				LocalDate.of(2023, 2, 22),
				350,
				BigDecimal.valueOf(56));
		participacaoRepository.save(participacao);

		participacao = new Participacao(
				"darius.jpg",
				usuario2,
				"Darius",
				LocalDate.of(2023, 2, 23),
				720,
				BigDecimal.valueOf(34));
		participacaoRepository.save(participacao);

		participacao = new Participacao(
				"garen.jpg",
				usuario3,
				"Garen",
				LocalDate.of(2023, 3, 28),
				600,
				BigDecimal.valueOf(5.39));
		participacaoRepository.save(participacao);

		participacao = new Participacao(
				"taric.jpg",
				usuario2,
				"Taric",
				LocalDate.of(2023, 4, 30),
				95,
				BigDecimal.valueOf(10));
		participacaoRepository.save(participacao);

		participacao = new Participacao(
				"gragas.jpg",
				usuario2,
				"Gragas",
				LocalDate.of(2023, 5, 29),
				350,
				BigDecimal.valueOf(7));
		participacaoRepository.save(participacao);

		participacao = new Participacao(
				"twitch.jpg",
				usuario1,
				"Twitch",
				LocalDate.of(2023, 5, 11),
				240,
				BigDecimal.valueOf(11.23));
		participacaoRepository.save(participacao);
	}
}
