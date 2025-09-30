package com.livros.biblioteca.services;

import com.livros.biblioteca.models.Emprestimo;
import com.livros.biblioteca.repositorys.EmprestimoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VerificacaoService {

    private final EmprestimoRepository emprestimoRepository;


    @Autowired
    public VerificacaoService(EmprestimoRepository emprestimoRepository) {
        this.emprestimoRepository = emprestimoRepository;
    }

    @Scheduled(cron = "0 0 1 * * *")
    //@Scheduled(fixedRate = 30000) //Executa a cada 30 segundos
    @Transactional
    public void verificarEmprestimosAtrasados() {
        System.out.println("--- ROBÔ: Iniciando verificação de empréstimos atrasados... ---");

        //Busca todos os empréstimos que não foram finalizados e estão em dia.
        List<Emprestimo> emprestimosAtivos = emprestimoRepository.findByFinalizadoFalseAndEmDiaTrue();

        for (Emprestimo emprestimo : emprestimosAtivos) {
            //Verifica se a data de término já passou.
            if (emprestimo.getDataEmprestimoTermino().isBefore(LocalDateTime.now())) {
                System.out.println("Empréstimo ID " + emprestimo.getId() + " está atrasado! Aplicando multa.");

                //Aplica a regra de negócio.
                emprestimo.setEmDia(false);
                emprestimo.setMulta(10); // Ou emprestimo.setMulta(emprestimo.getMulta() + 10);

                // Como o método é @Transactional, o JPA salvará as mudanças automaticamente.
            }
        }
        System.out.println("--- ROBÔ: Verificação concluída. ---");
    }

}
