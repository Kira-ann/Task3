
import org.example.Cats;
import org.example.Chess;
import org.example.MatchingSquare;
import org.junit.jupiter.api.Test;

import static java.lang.Math.abs;
import static org.junit.jupiter.api.Assertions.*;

class Tests {
    @Test
    public void greatChess(){
        Chess example = new Chess();
        for (int i = 0; i < 4; i++){
            example.position[i][0] = 80;
            example.position[i][1] = 120;
        }
        for (int i = 4; i < 8; i++){
            example.position[i][0] = 80;
            example.position[i][1] = 508;
        }
        example.comparisonChess();
        boolean now = true;
        for (int i = 0; i < 4; i++){
            if (example.position[i][0] <= 80 && example.position[i][0] >= 720) now = false;
            if (example.position[i][1] >= 390 && example.position[i][1] <= 100) now = false;
        }
        for (int i = 4; i < 8; i++){
            if (example.position[i][0] <= 80 && example.position[i][0] >= 720) now = false;
            if (example.position[i][1] >= 778 && example.position[i][1] <= 488) now = false;
        }
        assertEquals (now, example.getGoodJob() == 1);
    }
    @Test
    public void greatCats(){
        Cats example = new Cats();
        example.position[0][0] = 400;
        example.position[0][1] = 250;
        example.position[1][0] = 900;
        example.position[1][1] = 250;
        example.comparisonCats();
        boolean now = true;
        if (abs(example.position[0][0] - example.position[1][0]) > 6) now = false;
        if (abs(example.position[0][1] - example.position[1][1]) > 6) now = false;
        assertEquals (now, example.getGoodJob() == 1);
    }
    @Test
    public void greatSqyare(){
        MatchingSquare example = new MatchingSquare();
        example.position[0] = 248;
        example.position[1] = 300;
        example.comparisonSquare();
        boolean now = true;
        if (example.position[0] < 248 && example.position[0] > 448) now = false;
        if (example.position[1] < 300 && example.position[1] > 500) now = false;
        System.out.println(example.getGoodJob());
        assertEquals (now, example.getGoodJob() == 1);
    }
}