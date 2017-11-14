package com.lunar.world.entity;

import com.lunar.entity.Entity;

/**
 * Created by Vrekt on 11/13/2017.
 */
public class MutableEntity {
    public enum EntityAction {
        ADD, REMOVE
    }

    private Entity thisEntity;
    private EntityAction action;

    public MutableEntity(Entity entity, EntityAction action) {
        this.thisEntity = entity;
        this.action = action;
    }

    /**
     * Get the entity.
     *
     * @return the entity.
     */
    public Entity getThisEntity() {
        return thisEntity;
    }

    /**
     * Get the EntityAction.
     *
     * @return the EntityAction.
     */
    public EntityAction getAction() {
        return action;
    }
}
