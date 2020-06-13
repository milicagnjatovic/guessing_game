import java.util.*;

public class GameLogic {
    private Colors[] colors;
    private Map<Colors, Integer> colorCount;

    public GameLogic(){
        colors = new Colors[4];
        colorCount = new TreeMap<>();
        Random rand = new Random();
        for(int i=0; i<4; i++){
            colors[i] = Colors.values()[rand.nextInt(6)];
            if(colorCount.containsKey(colors[i]))
                colorCount.put(colors[i], colorCount.get(colors[i])+1);
            else
                colorCount.put(colors[i], 1);
        }
        Arrays.stream(colors).forEach(c -> System.out.println(c));
    }

    public boolean CheckCombintion(Colors[] row, int[] ret){
        int rsp = 0;
        int wsp = 0;
        Map<Colors, Integer> rowCount = new TreeMap<>();
        for(int i=0; i<colors.length; i++){
            if(rowCount.containsKey(row[i]))
                rowCount.put(row[i], rowCount.get(row[i])+1);
            else
                rowCount.put(row[i], 1);
            if(colors[i] == row[i])
                rsp++;
        }

        ret[0] = rsp;
        if(rsp == 4){
            ret[1] = 0;
            return true;
        }


        for (Map.Entry el : rowCount.entrySet()) {
            int guessed = (int)el.getValue();
            int contain = 0;
            if(colorCount.containsKey(el.getKey()))
                contain = colorCount.get(el.getKey());
            if(guessed <= contain)
                wsp += guessed;
            else
                wsp += contain;
        }

        ret[1] = wsp -rsp;
         return false;
    }

}
