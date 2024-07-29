package main;
import util.Util;
import entities.*;
public class Main
{
    public static void main (String[] args)
    {
        try {
            Garde[] gardes = Garde.getAvailableGarde(Util.stringToTimestamp("2024-07-18T12:00"), Util.stringToTimestamp("2024-07-18T16:00"));
        
            for (int i = 0; i < gardes.length; i++) {
                System.out.println(gardes[i].getNom());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}