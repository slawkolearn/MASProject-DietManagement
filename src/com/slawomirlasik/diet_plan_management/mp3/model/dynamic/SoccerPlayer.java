package com.slawomirlasik.diet_plan_management.mp3.model.dynamic;

import com.slawomirlasik.diet_plan_management.util.ExtensionAnnotationAssociationManager;
import com.slawomirlasik.diet_plan_management.util.ExtensionAssociationManager;

import java.io.Serializable;
import java.util.Objects;

public abstract class SoccerPlayer extends ExtensionAnnotationAssociationManager implements Serializable {

    public static final String soccerPlayerRole = "isManagedBy";

    private String name;

    private byte playerNumber;

    public SoccerPlayer(String name, byte playerNumber) {
        super();
        this.name = name;
        this.playerNumber = playerNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(byte playerNumber) {
        this.playerNumber = playerNumber;
    }

    public abstract String getPosition();

    public abstract void play();

    protected void pointToANewPlayer(SoccerPlayer soccerPlayer) throws Exception {
        for(ExtensionAssociationManager manager : soccerPlayer.getLinks(soccerPlayerRole)){
            manager.removeLink(soccerPlayer);
            manager.addLink(Coach.managesRoleNameComposition, soccerPlayerRole,  this);
        }
    }

    @Override
    public String toString() {
        return "SoccerPlayer{" +
                "name='" + name + '\'' +
                ", playerNumber=" + playerNumber +
                ", position : "+ getPosition() + " " +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SoccerPlayer that = (SoccerPlayer) o;
        return playerNumber == that.playerNumber &&
                name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, playerNumber);
    }
}
