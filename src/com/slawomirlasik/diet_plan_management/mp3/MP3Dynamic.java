package com.slawomirlasik.diet_plan_management.mp3;

import com.slawomirlasik.diet_plan_management.mp3.model.dynamic.*;

public class MP3Dynamic {

    public static void main(String[] args) {

        SoccerPlayer ronaldo = new Attacker("Cristiano Ronaldo", (byte) 10, Attacker.AttackerPositions.FORWARD_ATTACK);
        SoccerPlayer messi = new Attacker("Messi", (byte) 11, Attacker.AttackerPositions.FORWARD_ATTACK);
        SoccerPlayer buffon = new Goalkeeper("Luigi Buffon", (byte) 1);
        SoccerPlayer szczesny = new ReservePlayer("Wojtek Szczęsny", (byte) 2);


        SoccerPlayer[] players = {
                ronaldo, messi, buffon, szczesny
        };

        try {
            Coach bob = new Coach("Bob");

            bob.addPlayersToManage(players);

            System.out.println("Go on!! Let's play!!");
            bob.giveOrderToPlay();

            ronaldo = bob.changePosition(ronaldo, Coach.Position.RESERVE, null);

//            System.out.println(ronaldo);
            System.out.println("Go on!! Let's play COME ON!!");
            bob.giveOrderToPlay();

            messi = bob.changePosition(messi, Coach.Position.MID, Midfielder.MidfielderPositions.MID_HELP);
            ronaldo = bob.changePosition(ronaldo, Coach.Position.ATTACK, Attacker.AttackerPositions.LEFT_WING_ATTACK);

            System.out.println("Very good keep going!!!");
            bob.giveOrderToPlay();

            szczesny = bob.changePosition(szczesny, Coach.Position.GOAL, null);
            buffon = bob.changePosition(buffon, Coach.Position.RESERVE, null);

            System.out.println("Keep it on!! 40 minutes!!");
            bob.giveOrderToPlay();



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
