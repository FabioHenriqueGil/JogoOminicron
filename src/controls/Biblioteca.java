package controls;

public class Biblioteca {

    public enum Formatos {
        MP3, WAV, WMA, MID
    }

    public enum SonsCurtos {

        BUTTON_POSITIVE("Button positive - MP3", "arquivos/Portal2_music_button_positive.mp3", Formatos.MP3),
        BOAS_VINDAS("Boas Vindas - WAV", "arquivos/boasVindas.wav", Formatos.WAV),
        MENU_DE_OPCOES("Menu de Opcoes - WAV", "arquivos/menuDeOpcoes.wav", Formatos.WAV),
        START("Start - WAV", "arquivos/inicioDeJogo.wav", Formatos.WAV);

        private final String titulo; // title
        private final String arquivo; // file
        private final Formatos formato; // format

        SonsCurtos(String titulo, String arquivo, Formatos formato) {
            this.titulo = titulo;
            this.arquivo = arquivo;
            this.formato = formato;
        }

        public String getTitulo() {
            return this.titulo;
        }

        public String getArquivo() {
            return this.arquivo;
        }

        public Formatos getFormato() {
            return this.formato;
        }

        @Override
        public String toString() {
            return this.titulo;
        }
    }

    public enum SonsFundo {

        NENHUMA_MUSICA("- Nenhuma musica -", null, null),
        SCIENCE_IS_FUN("Science Is Fun - MP3", "arquivos/Portal2_music_Science_is_Fun.mp3", Formatos.MP3),
        BUTTON_POSITIVE("Button positive - MP3", "arquivos/Portal2_music_button_positive.mp3", Formatos.MP3);

        private final String titulo; // title
        private final String arquivo; // file
        private final Formatos formato; // format

        SonsFundo(String titulo, String arquivo, Formatos formato) {
            this.titulo = titulo;
            this.arquivo = arquivo;
            this.formato = formato;
        }

        public String getTitulo() {
            return this.titulo;
        }

        public String getArquivo() {
            return this.arquivo;
        }

        public Formatos getFormato() {
            return this.formato;
        }

        @Override
        public String toString() {
            return this.titulo;
        }
    }
}
