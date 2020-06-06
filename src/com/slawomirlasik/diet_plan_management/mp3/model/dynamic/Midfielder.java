package com.slawomirlasik.diet_plan_management.mp3.model.dynamic;

public class Midfielder extends SoccerPlayer {




    public enum MidfielderPositions {
        MID_HELP,
        RIGHT_HELP,
        LEFT_HELP;

    }

    private MidfielderPositions midfielderPosition;

    public Midfielder(String name, byte playerNumber, MidfielderPositions midfielderPosition) {
        super(name, playerNumber);
        this.midfielderPosition = midfielderPosition;
    }


    public Midfielder(SoccerPlayer soccerPlayer, MidfielderPositions positionEnum) throws Exception {
        super(soccerPlayer.getName(), soccerPlayer.getPlayerNumber());

        setMidfielderPosition(positionEnum);

        pointToANewPlayer(soccerPlayer);
    }

    public MidfielderPositions getMidfielderPosition() {
        return midfielderPosition;
    }

    public void setMidfielderPosition(MidfielderPositions midfielderPosition) {
        this.midfielderPosition = midfielderPosition;
    }

    @Override
    public String getPosition() {
        return getMidfielderPosition().toString();
    }

    @Override
    public void play() {
        System.out.println("  "+ getName() + " -->>> Playing in the middle on the " + getPosition());
    }
}
