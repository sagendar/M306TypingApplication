package Model;

public class Word {
    private int id;

    private String word;

    private String language;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Word(int id, String word, String language) {
        this.id = id;
        this.word = word;
        this.language = language;
    }
}
