package com.workoutnow.general.service;

import com.workoutnow.general.controllers.ExecutionController;
import com.workoutnow.general.dtos.ExecutionDto;
import com.workoutnow.general.enums.StatusExecution;
import com.workoutnow.general.models.Execution;
import com.workoutnow.general.models.Training;
import com.workoutnow.general.repositories.ExecutionRepository;
import com.workoutnow.general.repositories.TrainingRepository;
import com.workoutnow.general.service.util.MockUtil;
import com.workoutnow.general.service.util.UserUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ExecutionServiceTest {
    private ExecutionService executionService ;
    @Mock
    private ExecutionRepository executionRepository;
    @Mock
    private TrainingRepository trainingRepository;
    @Mock
    private KeycloakService keycloakService;

    @BeforeEach
    private void initialize(){
        MockitoAnnotations.initMocks(this);
        this.executionService = new ExecutionService(executionRepository, trainingRepository, keycloakService);
    }

    @Test
    void deveriaExecutarOTreinamento(){
        Date date = new Date();
        Execution e = new Execution(
                1L,
                new Training(),
                StatusExecution.IN_PROGRESS,
                date,
                null,
                "idteste"
        );
        Mockito.when(this.executionRepository.save(Mockito.any())).thenReturn(e);
        Mockito.when(this.trainingRepository.findById(1L)).thenReturn(Optional.of(new Training()));

        try (MockedStatic<UserUtil> utilities = Mockito.mockStatic(UserUtil.class)) {
            ExecutionDto executionDto = executionService.create(1L);
            utilities.when(UserUtil::getCurrentUserId).thenReturn("idteste");

            assertEquals(e.getStatus(),  executionDto.getStatus());
            assertEquals(e.getStartDate(),  executionDto.getStart());
            assertNull(executionDto.getEnd());
            assertEquals(e.getUserId(), executionDto.getUserId());

            Mockito.verify(executionRepository).save(Mockito.any());
        }
    }

//    @Test
//    void deveriaEnviarEmailParaVencedorDoLeilao(){
//        List<Leilao> leiloes = leiloes();
//        Mockito.when(this.dao.buscarLeiloesExpirados()).thenReturn(leiloes);
//
//        service.finalizarLeiloesExpirados();
//
//        Leilao leilao = leiloes.get(0);
//        Lance lanceVencedor = leilao.getLanceVencedor();
//
//        Mockito.verify(enviadorDeEmails).enviarEmailVencedorLeilao(lanceVencedor);
//    }
//
//    @Test
//    void naoDeveriaEnviarEmailParaVencedorDoLeilaoEmCasoDeExceptionAoSalvarOLeilao(){
//        List<Leilao> leiloes = leiloes();
//        Mockito.when(this.dao.buscarLeiloesExpirados()).thenReturn(leiloes);
//        Mockito.when(this.dao.salvar(Mockito.any())).thenThrow(RuntimeException.class);
//
//        try{
//            service.finalizarLeiloesExpirados();
//            Mockito.verifyNoInteractions(enviadorDeEmails);
//        }catch (Exception e){
//
//        }
//    }
//
//    private List<Leilao> leiloes() {
//        List<Leilao> lista = new ArrayList<>();
//
//        Leilao leilao = new Leilao("Celular",
//                new BigDecimal("500"),
//                new Usuario("Fulano"));
//
//        Lance primeiro = new Lance(new Usuario("Beltrano"),
//                new BigDecimal("600"));
//        Lance segundo = new Lance(new Usuario("Ciclano"),
//                new BigDecimal("900"));
//
//        leilao.propoe(primeiro);
//        leilao.propoe(segundo);
//
//        lista.add(leilao);
//
//        return lista;
//    }
//
//    @Test
//    void deveriaCriarUmNovoJogo(){
//        Jogo jogo = this.executionController.iniciarNovoJogo(jogador).getBody();
//        assertNotEquals(null, jogo);
//        assertEquals(jogo.getJogador1(), jogador);
//        assertTrue(JogoStorage.getInstancia().getJogos().containsKey(jogo.getId()));
//    }
//
//    @Test
//    void deveriaConectarAJogoAleatorio(){
//        Jogo jogoCriado = this.executionController.iniciarNovoJogo(jogador).getBody();
//        Jogo jogoConectado = this.executionController.conectarAJogoAleatorio(jogador2).getBody();
//
//        assertNotEquals(null, jogoConectado);
//        assertEquals(jogoConectado.getJogador2(), jogador2);
//        assertEquals(jogoConectado, jogoCriado);
//        assertTrue(JogoStorage.getInstancia().getJogos().containsKey(jogoConectado.getId()));
//    }
//
//    @Test
//    void deveriaConectarAJogo(){
//        Jogo jogoCriado = this.executionController.iniciarNovoJogo(jogador).getBody();
//        assertNotEquals(null, jogoCriado);
//
//        Jogo jogoConectado = this.executionController
//                .conectarAoJogo(new ConectarAoJogoRequest(jogador2, jogoCriado.getId())).getBody();
//
//        assertNotEquals(null, jogoConectado);
//        assertEquals(jogoConectado.getJogador2(), jogador2);
//        assertEquals(jogoConectado, jogoCriado);
//        assertTrue(JogoStorage.getInstancia().getJogos().containsKey(jogoConectado.getId()));
//    }
//
//    @Test
//    void deveriaExecutarPrimeiraJogadaEBloquearASegundaJogada(){
//        Mockito.doNothing().when(simpMessagingTemplate).convertAndSend(ArgumentMatchers.anyString(), ArgumentMatchers.any(Object.class));
//
//        Jogo jogoCriado = this.executionController.iniciarNovoJogo(jogador).getBody();
//        assertNotEquals(null, jogoCriado);
//
//        Jogada jogada = new Jogada(jogoCriado.getId(), 0,0, Marcador.X);
//        Jogada jogada2 = new Jogada(jogoCriado.getId(), 1,1, Marcador.X);
//        Jogo jogoResponseJogada = this.executionController.executarJogada(jogada).getBody();
//        Jogo jogoResponseJogada2 = this.executionController.executarJogada(jogada2).getBody();
//
//        assertNotNull(jogoCriado);
//        assertNotNull(jogoResponseJogada);
//        assertNull(jogoResponseJogada2);
//        assertEquals(Turno.JOGADOR2, jogoResponseJogada.getTurno());
//        assertEquals(1, jogoResponseJogada.getQuadro()[0][0]);
//        assertEquals(0, jogoResponseJogada.getQuadro()[1][1]);
//    }
}
