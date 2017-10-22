package me.vrekt.lunar.world;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import me.vrekt.lunar.entity.Entity;
import me.vrekt.lunar.entity.TestEntity;
import org.junit.jupiter.api.*;

/**
 * Created by Rick on 10/21/17.
 */
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
    @DisplayName("entity handling")
    class EntityHandling {
        @Nested
        @DisplayName("adding")
        class Adding {
            TestEntity ent;
            @BeforeEach
            void addEntity() {
                ent = new TestEntity();
                world.addEntity(ent);
            }
        }
        @Test
        @Tag("world_entity")
        @DisplayName("add entity")
        void TestAddEntity() {
            world.addEntity(new TestEntity());
            assertEquals(1, world.getWorldEntities().size(), "Expected one entity to be added to world entity list.");
        }

        @Test
        @Tag("world_entity")
        @DisplayName("remove entity")
        void TestRemoveEntity() {
            TestEntity ent = new TestEntity();
            world.addEntity(ent);
            world.removeEntity(ent);
            assertEquals(0, world.getWorldEntities().size(), "Expected entity to be removed.");
        }

        @Test
        @Tag("world_entity")
        @DisplayName("remove nonexistent entity")
        void TestRemoveNonexistentEntity() {
            world.addEntity(new TestEntity());
            TestEntity ent = new TestEntity();
            // don't add the entity, but try to remove it
            world.removeEntity(ent);
            assertEquals(1, world.getWorldEntities().size(), "Expected there to still be one entity.");
        }

        @Test
        @Tag("world_entity")
        @DisplayName("add queued entity on tick")
        void TestQueueEntityForAdd() {
            TestEntity ent = new TestEntity();
            world.queueEntityForAdd(ent);
            world.onTick();
            assertEquals(1, world.getWorldEntities().size(), "Expected one entity to be added from queue.");
            assertEquals(ent, world.getWorldEntities().get(0), "Expected the added entity to be our entity.");
        }

        @Test
        @Tag("world_entity")
        @DisplayName("remove queued entity on tick")
        void TestRemoveQueuedEntities() {
            TestEntity ent = new TestEntity();
            world.addEntity(ent);
            world.queueEntityForRemoval(ent);
            world.onTick();
            assertEquals(0, world.getWorldEntities().size(), "Expected queued entity to be removed after tick.");
        }

        @Test
        @Tag("world_entity")
        @DisplayName("get entity by id")
        void TestGetEntity() {
            TestEntity ent = new TestEntity();
            world.addEntity(ent);
            Entity got = world.getEntity(ent.getEntityID());
            assertTrue(got instanceof TestEntity);
            assertEquals(ent, got, "Expected entity returned to be the one we added.");
        }

        @Test
        @Tag("world_entity")
        @DisplayName("is entity at a location")
        void TestIsEntityAt() {
            TestEntity ent = new TestEntity();
            ent.setX(15);
            ent.setY(15);
            world.addEntity(ent);
            assertTrue(world.isEntityAt(15, 15), "Expected to find our entity at the given coordinats.");
        }

        @Test
        @Tag("world_entity")
        @DisplayName("get entity at a location")
        void TestGetEntityAt() {
            TestEntity ent = new TestEntity();
            ent.setX(15);
            ent.setY(15);
            world.addEntity(ent);

            assertEquals(ent, world.getEntityAt(15, 15));
        }

        @Test
        @Tag("world_entity")
        @DisplayName("get list of world entities")
        void TestGetWorldEntities() {
            world.addEntity(new TestEntity());
            assertEquals(world.worldEntities, world.getWorldEntities(), "Expected to get the correct list of entities");
        }
    }
}
