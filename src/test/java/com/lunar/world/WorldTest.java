package com.lunar.world;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.lunar.entity.Entity;
import com.lunar.entity.TestEntity;
import com.lunar.tile.TestTile;
import com.lunar.tile.Tile;
import com.lunar.world.dir.Direction;
import org.junit.jupiter.api.*;

class WorldTest {
    private TestWorld world;

    @BeforeEach
    void setupTestWorld() {
        world = new TestWorld();
    }

    @AfterEach
    void destroyTestWorld() {
        world = null;
    }

    /** World Entity Tests **/
    @Nested
    @Tag("world_entity")
    @DisplayName("entity handling")
    class EntityHandling {
        @Nested
        @DisplayName("manipulate world entity list")
        class ManipulateWorldEntityList {
            TestEntity ent;
            @BeforeEach
            void addEntity() {
                ent = new TestEntity();
                world.addEntity(ent);
            }

            @Test
            @DisplayName("add entity")
            void TestAddEntity() {
                assertEquals(1, world.getWorldEntities().size(), "Expected one entity to be added to world entity list.");
            }

            @Test
            @DisplayName("remove entity")
            void TestRemoveEntity() {
                world.removeEntity(ent);
                assertEquals(0, world.getWorldEntities().size(), "Expected entity to be removed.");
            }

            @Test
            @DisplayName("remove nonexistent entity")
            void TestRemoveNonexistentEntity() {
                TestEntity nonent = new TestEntity();
                // don't add the entity, but try to remove it
                world.removeEntity(nonent);
                assertEquals(1, world.getWorldEntities().size(), "Expected there to still be one entity.");
            }

            @Test
            @DisplayName("get entity by id")
            void TestGetEntity() {
                Entity got = world.getEntity(ent.getEntityID());
                assertTrue(got instanceof TestEntity);
                assertEquals(ent, got, "Expected entity returned to be the one we added.");
            }

            @Test
            @DisplayName("remove queued entity on tick")
            void TestRemoveQueuedEntities() {
                world.queueEntityForRemoval(ent);
                world.onTick();
                assertEquals(0, world.getWorldEntities().size(), "Expected queued entity to be removed after tick.");
            }


            @Test
            @DisplayName("add queued entity on tick")
            void TestQueueEntityForAdd() {
                // remove the one that's automatically added
                world.removeEntity(ent);
                world.queueEntityForAdd(ent);
                world.onTick();
                assertEquals(1, world.getWorldEntities().size(), "Expected one entity to be added from queue.");
                assertEquals(ent, world.getWorldEntities().get(0), "Expected the added entity to be our entity.");
            }
        }

        @Test
        @DisplayName("is entity at a location")
        void TestIsEntityAt() {
            TestEntity ent = new TestEntity();
            ent.setX(15);
            ent.setY(15);
            world.addEntity(ent);
            assertTrue(world.isEntityAt(15, 15), "Expected to find our entity at the given coordinates.");
        }


        @Test
        @DisplayName("get entity at a location")
        void TestGetEntityAt() {
            TestEntity ent = new TestEntity();
            ent.setX(15);
            ent.setY(15);
            world.addEntity(ent);

            assertEquals(ent, world.getEntityAt(15, 15), "Expected to get back our entity from coordinates.");
        }

        @Test
        @DisplayName("get list of world entities")
        void TestGetWorldEntities() {
            assertEquals(world.WORLD_ENTITIES, world.getWorldEntities(), "Expected to get the correct list of entities");
        }
    }

    @Nested
    @Tag("world_tile")
    @DisplayName("tile handling")
    class TileHandling {
        @Test
        @DisplayName("add tile with coordinates")
        void TestAddTileToCoordinates() {
            TestTile tile = new TestTile();
            world.set(1, 2, tile);
            assertEquals(1, world.TILE_MAP.size(), "Expected one tile to be in the map after adding.");
            assertEquals(0, tile.getX(), "Expected the coordinates of the tile to not change");
            assertEquals(0, tile.getY(), "Expected the coordinates of the tile to not change");
        }

        @Test
        @DisplayName("add tile")
        void TestAddTile() {
            world.set(new TestTile());
            assertEquals(1, world.TILE_MAP.size());
        }

        @Test
        @DisplayName("add batch tiles")
        void TestAddBatchTiles() {
            int numTiles = 10;
            TestTile tile = new TestTile(10, 10);
            world.setMultiple(tile, 0, 0, Direction.RIGHT, numTiles, false);
            world.setMultiple(tile, 0, 10, Direction.LEFT, numTiles, false);
            world.setMultiple(tile, 0,120, Direction.UP, numTiles, false);
            world.setMultiple(tile, 10,20, Direction.DOWN, numTiles, false);
            assertEquals(numTiles * 4, world.TILE_MAP.size(), "Expected there to be the correct number of tiles after adding");
            // Test Direction.RIGHT
            for (int i = 0; i < numTiles; i++) {
                Tile got = world.get(i*10, 0);
                assertNotNull(got, String.format("Expected to find tile at (%d, %d)", i*10, 0));
            }
            // Test Direction.LEFT
            for (int i = 0; i < numTiles; i++) {
                assertNotNull(world.get(-i * 10, 10), String.format("Expected to find tile at (%d, %d)", i*10, 10));
            }
            // Test Direction.UP
            for (int i = 0; i < numTiles; i++) {
                int x = 0;
                int y = 120 - i * 10;
                assertNotNull(world.get(x, y), String.format("Expected to find tile at (%d, %d)", x, y));
            }
            // Test Direction.DOWN
            for (int i = 0; i < numTiles; i++) {
                int x = 10;
                int y = 20 + i*10;
                assertNotNull(world.get(x, y), String.format("Expected to find tile at (%d, %d)", x, y));
            }
        }
    }
}
