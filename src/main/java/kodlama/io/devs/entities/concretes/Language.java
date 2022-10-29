package kodlama.io.devs.entities.concretes;

public class Language {
    private int id;
    private String name;

    public Language() {
    }

    public Language(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Language language = (Language) o;
//
//        if (id != language.id) return false;
//        return name.equals(language.name);
//    }
//
//    @Override
//    public int hashCode() {
//        int result = id;
//        result = 1923 * result + name.hashCode();
//        return result;
//    }
}
