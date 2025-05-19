package com.voting.cooperative.client;

import org.springframework.stereotype.Component;

import java.util.Random;


@Component
public class CpfValidatorClient {

    public enum StatusVotacao {
        ABLE_TO_VOTE,
        UNABLE_TO_VOTE
    }

    private final Random random = new Random();

    public boolean podeVotar(String cpf) {
        if (cpf == null || cpf.length() != 11) {
            return false;
        }
        

        StatusVotacao status = random.nextDouble() < 0.8 
                ? StatusVotacao.ABLE_TO_VOTE 
                : StatusVotacao.UNABLE_TO_VOTE;
                
        return status == StatusVotacao.ABLE_TO_VOTE;
    }
}