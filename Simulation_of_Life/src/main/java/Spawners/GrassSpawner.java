package Spawners;

import Entities.Grass;
import gui.render.World.Cell;
import Misc.Vector2D;
import com.google.gson.JsonElement;

import java.util.Random;

public class GrassSpawner extends Spawner {
    public final Config config;

    public GrassSpawner(Config config) {
        this.config = config;
    }

    @Override
    public boolean canSpawn(Cell cell) {
        Random random = new Random();
        double spawnProbability = random.nextInt(101) / 100.0;
        return Cell.isCellEmpty(cell) && spawnProbability <= cell.getSpawnProbability() && !Cell.containsObject(cell, Grass.class);
    }

    @Override
    protected boolean tryToSpawn(int attempts) {
        Vector2D position = findValidPosition(attempts);
        if(position == null) {
            return false;
        }
        Grass grass = new Grass(position, this.config.nutritionValue);
        this.world.addEntity(grass);
        return true;
    }

    @Override
    public void spawn() {
        for (int i = 0; i < this.config.spawnRate; i++) {
            if(!tryToSpawn(100)) {
                break;
            }
        }
    }

    @Override
    protected void spawnFirstPopulation() {
        for (int i = 0; i < this.config.firstPopulationSpawnRate; i++) {
            if(!tryToSpawn(100)) {
                break;
            }
        }
    }

    public static Spawner fromConfig(JsonElement configJson) {
        Config config = GSON.fromJson(configJson, Config.class);
        return new GrassSpawner(config);
    }

    public static class Config {
        protected int spawnRate = 10;
        protected int firstPopulationSpawnRate = 10;
        protected int nutritionValue = 10;
    }
}
