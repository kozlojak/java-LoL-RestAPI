package pl.jakubkozlowski.learning.firststeps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class FirststepsApplication
	//	implements CommandLineRunner
{

	//private ChampionMapper championMapper;

//	public FirststepsApplication(ChampionMapper championMapper) {
//		this.championMapper = championMapper;
//	}

	public static void main(String[] args) {
		SpringApplication.run(FirststepsApplication.class, args);
	}


//	@Override
//	public void run(String... args) throws Exception {
//		this.championMapper.deleteOne(136L);
//		this.championMapper.addOne(136L, "ZZic");
//		System.out.println(this.championMapper.findOne(136L));
//		this.championMapper.updateOne("Zultron", 136L);
//		System.out.println(this.championMapper.findAll());
//		this.championMapper.deleteOne(136L);
//	}
}

