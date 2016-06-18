package controls;

import controls.Biblioteca.SonsCurtos;
import controls.Biblioteca.SonsFundo;

public class Radio {

    PlayerMp3Wav playerMusicaFundo;
    Thread threadMusicaFundo;

    public Radio() {
    }

    public void tocar(SonsCurtos arquivoSom, int volume) {
        PlayerMp3Wav executorSom = new PlayerMp3Wav(arquivoSom.getArquivo(), volume, false);
        new Thread(executorSom).start(); // Inica thread para tocar o som, que é curto e acaba logo
    }

    public void tocar(SonsFundo somFundo, int volume, boolean repetir) {
        pararMusicaFundo(); // se vai tocar outra, parar a atual

        if (somFundo != SonsFundo.NENHUMA_MUSICA) {
            playerMusicaFundo = new PlayerMp3Wav(somFundo.getArquivo(), volume, repetir);
            playerMusicaFundo.setVolume(volume);
            threadMusicaFundo = new Thread(playerMusicaFundo);
            threadMusicaFundo.start();
            
        }
    }

    public void volumeMusicaFundo(int volume) {
        playerMusicaFundo.setVolume(volume);
    }

    public void setMudoMusicaFundo(boolean isMudo) {
        playerMusicaFundo.setMudo(isMudo);
    }

    public void setRepetirMusicaFundo(boolean repetir) {
        playerMusicaFundo.setRepetir(repetir);
    }

    public void pararMusicaFundo() {
        if (playerMusicaFundo != null) {
            playerMusicaFundo.parar();
        }
    }

    public void pausarMusicaFundo(boolean pausar) {
        playerMusicaFundo.pausar(pausar);

        if (!pausar) {
            threadMusicaFundo = new Thread(playerMusicaFundo); // nova thread, com a mesma instancia para iniciar onde parou
            threadMusicaFundo.start();
        }
    }
}
