package com.slawomirlasik.diet_plan_management.mp3.model.dynamic;

public class Goalkeeper extends SoccerPlayer{


    public Goalkeeper(SoccerPlayer soccerPlayer) throws Exception {
        super(soccerPlayer.getName(), soccerPlayer.getPlayerNumber());

        pointToANewPlayer(soccerPlayer);
    }

    public Goalkeeper(String name, byte playerNumber) {
        super(name, playerNumber);
    }



    @Override
    public String getPosition() {
        return"Goal keeping";
    }

    @Override
    public void play() {
        System.out.println( "  "+ getName() + " -->>> Standing on the goal");
    }
}
