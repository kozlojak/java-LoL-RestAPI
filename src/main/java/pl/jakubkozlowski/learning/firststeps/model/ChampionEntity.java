package pl.jakubkozlowski.learning.firststeps.model;


import java.util.Objects;

public class ChampionEntity {
    private Long id;
    private String name;

    public ChampionEntity() {

    }

    public ChampionEntity(Long id, String name) {
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
        ChampionEntity championEntity = (ChampionEntity) o;
        return Objects.equals(id, championEntity.id) &&
                Objects.equals(name, championEntity.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }


    @Override
    public String toString() {
        return "ChampionEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public static ChampionBuilder builder() {
        return new ChampionBuilder();
    }

    public static class ChampionBuilder {
        private ChampionEntity championEntity;

        public ChampionBuilder() {
            this.championEntity = new ChampionEntity();
        }

        public ChampionBuilder id(Long id) {
            this.championEntity.setId(id);
            return this;
        }

        public ChampionBuilder name(String name) {
            this.championEntity.setName(name);
            return this;
        }

        public ChampionEntity build() {
            return this.championEntity;
        }
    }
}
