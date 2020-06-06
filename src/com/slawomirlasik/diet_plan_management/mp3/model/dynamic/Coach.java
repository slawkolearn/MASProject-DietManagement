package com.slawomirlasik.diet_plan_management.mp3.model.dynamic;

import com.slawomirlasik.diet_plan_management.util.ExtensionAnnotationAssociationManager;
import com.slawomirlasik.diet_plan_management.util.ExtensionAssociationManager;

import java.io.Serializable;

public class Coach extends ExtensionAnnotationAssociationManager implements Serializable {

    public static final String managesRoleNameComposition = "managesTeam";



    public enum Position{
        ATTACK,
        DEFENSE,
        MID,
        GOAL,
        RESERVE
    }

    private String name;

    public Coach(String name){
        super();
        this.name = name;
    }

    public void addPlayersToManage(SoccerPlayer[] players) {

        for(SoccerPlayer soccerPlayer : players){
            addLink(managesRoleNameComposition, SoccerPlayer.soccerPlayerRole , soccerPlayer);
        }
    }

    public void giveOrderToPlay() throws Exception {

        for(ExtensionAssociationManager soccerPlayer : getLinks (managesRoleNameComposition)){
            ((SoccerPlayer) soccerPlayer).play();
        }
    }

    public SoccerPlayer changePosition(SoccerPlayer soccerPlayer, Position newPosition, Enum positionEnum) throws Exception {

        System.out.println("\nChanging possition of " + soccerPlayer.getName() + " from " + soccerPlayer.getPosition());
        System.out.println();

        switch (newPosition){
            case ATTACK:
                return  new Attacker(soccerPlayer, (Attacker.AttackerPositions) positionEnum);
            case DEFENSE:
                return new Defender(soccerPlayer, (Defender.DefenderPositions) positionEnum);
            case MID:
                return new Midfielder(soccerPlayer, (Midfielder.MidfielderPositions) positionEnum);
            case GOAL:
                return new Goalkeeper(soccerPlayer);
            case RESERVE:
                return new ReservePlayer(soccerPlayer);
            default:
                throw new Exception(String.format("No such position : " + newPosition.toString()));

        }

    }

    @Override
    public String toString() {
        return "Coach{" +
                "name='" + name + '\'' +
                '}';
    }
}
