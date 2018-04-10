package pl.jakubkozlowski.learning.firststeps.model;

public class ChampionConverterImpl implements ChampionConverter{

    @Override
    public ChampionDTO convert (Champion champion){
        if (champion == null){
            return null;
        }
        return new ChampionDTO(champion.getId(), champion.getName());
    }

    @Override
    public Champion convert (ChampionDTO championDTO){

        return new Champion(championDTO.getId(), championDTO.getName());
    }



}
