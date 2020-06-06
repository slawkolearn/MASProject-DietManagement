package com.slawomirlasik.diet_plan_management.mp3.model.dynamic;

public class ReservePlayer extends SoccerPlayer {


    public ReservePlayer(String name, byte playerNumber) {
        super(name, playerNumber);
    }

    public ReservePlayer(SoccerPlayer soccerPlayer) throws Exception {
        super(soccerPlayer.getName(), soccerPlayer.getPlayerNumber());

        // make all links with the role isManagedBy change to show on THIS object
        pointToANewPlayer(soccerPlayer);

    }



    @Override
    public String getPosition() {
        return "Reserve";
    }

    @Override
    public void play() {
        System.out.println("  "+ getName() + " -->>> Waiting for my turn");
    }


}
