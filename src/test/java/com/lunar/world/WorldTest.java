package com.lunar.world;

import com.lunar.entity.Entity;
import com.lunar.entity.TestEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    /**
     * World Entity Tests
     **/
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
            assertEquals(world.worldEntities, world.getWorldEntities(), "Expected to get the correct list of entities");
        }
    }
}
