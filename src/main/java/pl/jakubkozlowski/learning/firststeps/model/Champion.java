package pl.jakubkozlowski.learning.firststeps.model;


import java.util.Objects;

public class Champion{
    private Long id;
    private String name;

    public Champion(){

    }

    public Champion(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Champion champion = (Champion) o;
        return Objects.equals(id, champion.id) &&
                Objects.equals(name, champion.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }


    @Override
    public String toString() {
        return "Champion{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public static ChampionBuilder builder() {
        return new ChampionBuilder();
    }

    public static class ChampionBuilder {
        private Champion champion;

        public ChampionBuilder() {
            this.champion = new Champion();
        }

        public ChampionBuilder id(Long id) {
            this.champion.setId(id);
            return this;
        }

        public ChampionBuilder name(String name) {
            this.champion.setName(name);
            return this;
        }

        public Champion build() {
            return this.champion;
        }
    }
}
