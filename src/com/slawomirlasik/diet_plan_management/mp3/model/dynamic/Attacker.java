package com.slawomirlasik.diet_plan_management.mp3.model.dynamic;

public class Attacker extends SoccerPlayer {




    public enum AttackerPositions {
        LEFT_WING_ATTACK,
        RIGHT_WING_ATTACK,
        FORWARD_ATTACK,
        RESERVE_ATTACK
    }



    private AttackerPositions attackerPosition;

    public Attacker(String name, byte playerNumber, AttackerPositions attackerPosition) {
        super(name, playerNumber);
        this.attackerPosition = attackerPosition;
    }

    public Attacker(SoccerPlayer soccerPlayer, AttackerPositions newPosition) throws Exception {
        super(soccerPlayer.getName(), soccerPlayer.getPlayerNumber());

        setAttackerPosition(newPosition);

        pointToANewPlayer(soccerPlayer);
    }

    public AttackerPositions getAttackerPosition() {
        return attackerPosition;
    }

    public void setAttackerPosition(AttackerPositions attackerPosition) {
        this.attackerPosition = attackerPosition;
    }

    @Override
    public String getPosition() {
        return this.attackerPosition.toString();
    }

    @Override
    public void play() {
        System.out.println("  "+ getName() + " -->>> Playing in attack on the " + getPosition());
    }



}
