// http://loon-simple.googlecode.com/svn/trunk/j2se/LGame-j2se-0.2.7/mediaframe/mpeg4/audio/Java2AudioDevice.java
package controls;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;

/**
 * Classe player de �udio.
 *
 * @author Aline Rigues
 */
public class PlayerMp3Wav implements Runnable {

    private String arquivo;
    private boolean tocando;
    private boolean pausado;
    private boolean repetir;
    private boolean mudo;
    private int volume;
    private SourceDataLine sourceDataLine;
    private AudioInputStream audioInputStream;

    public PlayerMp3Wav(String arquivo, int volume, boolean repetir) {
        this.arquivo = arquivo;
        this.tocando = true;
        this.pausado = false;
        this.repetir = repetir;
        this.mudo = false;
        this.volume = volume;

        abrirArquivo();
    }

    /**
     * Da interface Runnable.
     */
    @Override
    public void run() {
        tocar();
    }

    private void abrirArquivo() {
        try {
            // Abre o arquivo
            File file = new File(arquivo);
            AudioInputStream in = AudioSystem.getAudioInputStream(file);
            AudioFormat baseFormat = in.getFormat();
            AudioFormat decodedFormat = new AudioFormat(
                    AudioFormat.Encoding.PCM_SIGNED,
                    baseFormat.getSampleRate(), 16, baseFormat.getChannels(),
                    baseFormat.getChannels() * 2, baseFormat.getSampleRate(),
                    false);
            audioInputStream = AudioSystem.getAudioInputStream(decodedFormat, in);

            // Obtem linha
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, decodedFormat);
            SourceDataLine res = (SourceDataLine) AudioSystem.getLine(info);
            res.open(decodedFormat);
            sourceDataLine = res;

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro ao abrir arquivo", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void tocar() {
        tocando = true;
        pausado = false;

        try {
            if (sourceDataLine != null) {

                byte[] data = new byte[4096];
                int nBytesRead;

                do {
                    System.out.println("Executando " + arquivo);

                    // Start
                    sourceDataLine.start();
                    nBytesRead = audioInputStream.read(data, 0, data.length);
                    while (nBytesRead != -1 && tocando) {
                        sourceDataLine.write(data, 0, nBytesRead);
                        nBytesRead = audioInputStream.read(data, 0, data.length);
                    }
                    sourceDataLine.drain();

                    if (repetir && tocando) {
                        abrirArquivo();
                    }
                } while (repetir && tocando);

                if (!pausado) { // musica parou porque acabou: fechar streams
                    sourceDataLine.stop();
                    sourceDataLine.close();
                    audioInputStream.close();
                }

                System.out.println("Termino de execução " + arquivo);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro ao executar arquivo", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setRepetir(boolean repetir) {
        this.repetir = repetir;
    }

    public void parar() {
        tocando = false;
        pausado = false;
    }

    public void pausar(boolean pausar) {
        pausado = pausar;
        if (pausar && tocando) {
            tocando = false;
        }
    }

    public void setVolume(int volume) {
        this.volume = volume;
        if ((sourceDataLine != null) && !mudo) {
            FloatControl volumeCtrl = (FloatControl) sourceDataLine.getControl(FloatControl.Type.MASTER_GAIN);
            float GainDb = (float) (20.d * Math.log(volume == 0 ? Double.MIN_VALUE : ((double) volume / 100.d)) / Math.log(10));
            volumeCtrl.setValue(GainDb);
        }
    }

    public void setMudo(boolean mudo) {
        this.mudo = mudo;
        if (sourceDataLine != null) {
            BooleanControl muteCtrl = (BooleanControl) sourceDataLine.getControl(BooleanControl.Type.MUTE);
            muteCtrl.setValue(mudo);
            if (!mudo) {
                setVolume(volume);
            }
        }
    }

    public boolean isTocando() {
        return tocando;
    }

    public boolean isPausado() {
        return pausado;
    }
}
