package com.slawomirlasik.diet_plan_management.mp3.model.dynamic;

public class Defender extends SoccerPlayer {


    public enum DefenderPositions {
        MID_DEFENSE,
        RIGHT_DEFENSE,
        LEFT_DEFENSE;


    }

    private DefenderPositions defenderPosition;

    public Defender(String name, byte playerNumber, DefenderPositions defenderPosition) {
        super(name, playerNumber);
        this.defenderPosition = defenderPosition;
    }


    public Defender(SoccerPlayer soccerPlayer, DefenderPositions newPosition) throws Exception {
        super(soccerPlayer.getName(), soccerPlayer.getPlayerNumber());

        setDefenderPosition(newPosition);

        pointToANewPlayer(soccerPlayer);
    }

    public DefenderPositions getDefenderPosition() {
        return defenderPosition;
    }

    public void setDefenderPosition(DefenderPositions defenderPosition) {
        this.defenderPosition = defenderPosition;
    }

    @Override
    public String getPosition() {
        return getDefenderPosition().toString();
    }

    @Override
    public void play() {
        System.out.println("  " + getName() + " -->>> Playing in defense on the " + getPosition());
    }


}
